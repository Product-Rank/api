package com.productrank.api.config.security.custom;

import com.productrank.api.domain.user.entity.User;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;

@ToString
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public SecurityUser(User user) {
        super(user.getEmail(), "",
                AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}
