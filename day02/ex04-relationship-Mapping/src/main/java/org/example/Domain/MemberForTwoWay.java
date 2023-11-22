package org.example.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MemberForTwoWay {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //오류가 나는 이유 : 테이블과의 연관관계를 설정해줘야 한다.
    // mapped by 가 없는 곳이 연관관계가 주인이다.
    // 외래키가 있는 곳을 주인으로 두는 것이 보통이다.
    @ManyToOne //Many : 멤버  One : 팀
    @JoinColumn(name = "TEAM_ID")
    TeamForOneWay team ;

    }
