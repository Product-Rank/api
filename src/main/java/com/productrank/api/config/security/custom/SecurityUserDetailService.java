package com.productrank.api.config.security.custom;

import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.UserRepository;
import com.productrank.api.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Cacheable("users")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(username);
        if(!optional.isPresent()) {
            throw new AuthenticationServiceException(ErrorCode.NOT_EXIST_USER.toString());
        } else {
            User user = optional.get();
            return new SecurityUser(user);
        }

    }
}
