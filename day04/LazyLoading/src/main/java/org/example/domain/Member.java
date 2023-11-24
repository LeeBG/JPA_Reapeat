package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends BaseEntity{

    @Id @GeneratedValue //생략하면 Auto전략
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToMany(mappedBy = "member") // 연관관계의 주인은 Order의 member이기 때문에 mappedBy에 적어준다, Order에서 시작해서 끌어오면 되기 때문에 솔직히 얘는 필요 없다
    private List<Order> orders = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩 - 연관된 멤버 필요할 때 조인해서 가져오는 것
    @ManyToOne(fetch = FetchType.LAZY)   // 즉시로딩 - 연관된 엔티티 항상 다 조인해서 가져오는 것(한방쿼리)
    @JoinColumn(name = "team_id")
    private Team team;

    // 실무에서는 항상 지연로딩만 사용한다. (특히 실무에서)
}
