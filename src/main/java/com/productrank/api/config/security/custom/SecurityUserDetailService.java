package com.productrank.api.config.security.custom;

import com.productrank.api.domain.user.entity.User;
import com.productrank.api.domain.user.repository.UserRepository;
import com.productrank.api.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(Long.valueOf(username));
        if(!optional.isPresent()) {
            throw new AuthenticationServiceException(ErrorCode.NOT_EXIST_USER.toString());
        } else {
            User user = optional.get();
            return new SecurityUser(user);
        }

    }
}
