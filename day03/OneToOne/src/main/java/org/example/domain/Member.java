package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    // 일대일 관계가 끝난다.
    // FK가 있는 곳이 연관관계의 주인이다.
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToOne                  //Many : 멤버  One : 팀
    @JoinColumn(name = "TEAM_ID") // readonly가 되어버림
    Team team;

}
