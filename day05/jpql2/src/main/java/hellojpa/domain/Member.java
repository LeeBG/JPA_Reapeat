package hellojpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void changeTeam(Team team) {
        this.team = team;
        team.setMembers(this.team.getMembers());
    }

}
