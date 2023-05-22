package com.productrank.api.config.security.custom;

import com.productrank.api.domain.entity.User;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;

@ToString
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private User user;
    public SecurityUser(String email, String role){
        super(email, "",
                AuthorityUtils.createAuthorityList(role));
        this.user = user;
    }
    public SecurityUser(User user) {
        super(String.format("%s", user.getEmail(), user.getSnsType()), "",
                AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}
