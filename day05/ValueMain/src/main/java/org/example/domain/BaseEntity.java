package org.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass //매핑정보만 받는 부모클래스, 변경이 안되는 기본적인 정보를 제공하는 운영에 필요한 필수적인 것들을 부모 클래스에 놔두고 모든 테이블에서 사용하도록 셋팅

@Data
public class BaseEntity {
    @Column(name = "INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    @Column(name = "UPDATE_MEMBER")
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
