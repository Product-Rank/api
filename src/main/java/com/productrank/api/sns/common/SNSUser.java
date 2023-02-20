package com.productrank.api.sns.common;

import com.productrank.api.domain.entity.enums.SNSType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SNSUser {
    private String name;
    private String email;
    private String picture;
    public SNSType snsType;
}
