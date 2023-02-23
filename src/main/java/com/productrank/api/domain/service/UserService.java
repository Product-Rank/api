package com.productrank.api.domain.service;

import com.productrank.api.config.security.JwtTokenProvider;
import com.productrank.api.domain.dto.UserDto;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider provider;
    public UserDto saveUser(UserDto userdto) {
        User user = User.builder()
                .userName(userdto.userName())
                .nickName(userdto.nickName())
                .email(userdto.email())
                .snsType(userdto.snsType())
                .picture(userdto.picture())
                .accessToken(userdto.accessToken())
                .build();
        User entityUser = userRepository.save(user);
        return UserDto.from(entityUser);
    }

    public UserDto getUser(UserDto userDto) {
        return userRepository.findByEmailAndSnsType(userDto.email(), userDto.snsType())
                .map(UserDto::from)
                .orElseThrow(() -> new RuntimeException("IS NOT VALID USER"));
    }

    public Optional<User> isUserExisted(String email){
        return userRepository.findByEmail(email);
    }

}
