package org.example.Domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
public class MemberForOneWaySolveProblem { // Member
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //오류가 나는 이유 : 테이블과의 연관관계를 설정해줘야 한다.
    @ManyToOne //Many : 멤버  One : 팀
    @JoinColumn(name = "TEAM_ID")
    TeamForTwoWay team ;

    public void changeTeam(TeamForTwoWay team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
