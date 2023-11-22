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
public class Member {

    @Id @GeneratedValue //생략하면 Auto전략
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member") // 연관관계의 주인은 Order의 member이기 때문에 mappedBy에 적어준다, Order에서 시작해서 끌어오면 되기 때문에 솔직히 얘는 필요 없다
    private List<Order> orders = new ArrayList<>();
}
