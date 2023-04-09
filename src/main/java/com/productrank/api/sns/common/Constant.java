package com.productrank.api.sns.common;

import java.util.ArrayList;
import java.util.List;

public class Constant {
        public enum SocialLoginType{
            GOOGLE,
            KAKAO,
            NAVER
        }


        static{
            List<String> s = new ArrayList<>();
            s.add("q");
            s.add("a");
            s.add("b");
            s.add(1,"d");
            s.subList(0,0);
            System.out.println(s);
            System.out.println(s.subList(1,2));
        }

    public static void main(String[] args) {

    }
}
