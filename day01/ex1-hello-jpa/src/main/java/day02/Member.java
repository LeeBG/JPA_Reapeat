package day02;

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

    // 기본키 생성을 데이터베이스에 위임하는 것이다.
    // MySQL에 Auto Increment

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

}
