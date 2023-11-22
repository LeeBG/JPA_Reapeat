package org.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 테이블과 관계 없고 단순히 엔티티가 공통으로 사용하는 부분을 만들 때
@Data
public abstract class BaseEntity {

    @Column(name = "INSERT_MEMBER")
    private String CreatedBy;
    private LocalDateTime createdDate;
    private String lastModifyBy;
    private LocalDateTime modifiedDate;
}
