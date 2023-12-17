package com.itbank.simpleboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDto {
    // 로그인
    private String userId;
    private String userPw;

    private String address;

    private Integer age;

    private String phoneNum;

    private String username;

    private String email;
}
