package com.productrank.api.domain.user.service;

import com.productrank.api.sns.kakao.client.UserDto;
import com.productrank.api.domain.user.entity.User;
import com.productrank.api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(UserDto userdto) {
        User user = User.builder()
                .userName(userdto.userName())
                .nickName(userdto.nickName())
                .email(userdto.email())
                .snsType(userdto.snsType())
                .ThumbnailUrl(userdto.ThumbnailUrl())
                .build();
        User entityUser = userRepository.save(user);
        return entityUser;
    }

    public User getUser(UserDto userDto) {
        return userRepository.findByEmailAndSnsType(userDto.email(), userDto.snsType())
                .orElseThrow(() -> new RuntimeException("IS NOT VALID USER"));
    }


}
