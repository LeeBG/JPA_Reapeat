package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass // 진짜 상속 관계가 아니고 속성을 하위클래스로 내려셔 쓸 수 있도록 한다. JPA상속관계는 따로 있다.
@Getter
public class JpaBaseEntity {
    // 등록일 , 수정일을 제공하는 엔티티
    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist // 추가하기 전에 시간을 담는 이벤트를 발동시킨다.
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        updateDate = now;
    }

    @PreUpdate
    public void preUpdate() { // 수정하기 전에 수정일을 담는 이벤트를 발생시킨다.
        updateDate = LocalDateTime.now();
    }
    
}
