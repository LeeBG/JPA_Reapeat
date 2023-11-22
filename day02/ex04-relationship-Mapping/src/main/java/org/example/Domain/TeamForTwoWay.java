package org.example.Domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TeamForTwoWay {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "name")
    private String username;

    // 양방향 연관관계를 위한 List
    //Arraylist로 초기화하는것을 관례로 많이 쓴다.
    // team으로 맵핑되어있다는 것을 명시(Member에 Team타입의 team 변수명을 쓰면된다.)
    // mapped by갑 붙어있는 곳에서는 조회가 되는데 주인은 아니다.
    // 주인의 반대편 - 가짜 매핑이다.
    @OneToMany(mappedBy = "team")
    private List<MemberForOneWaySolveProblem> members = new ArrayList<>();

}
