package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 조인 방식 : Item이 가지고 있는 컬럼에 정보가 들어가고 그 ID를 가지는 Movie테이블에 데이터가 따로 들어감
// 싱글 테이블   : Item 하나의 테이블에 정보를 몰아서 담는다.
// 구현 클래스마다 테이블 전략 : Item 테이블을 없애 버리고 Movie 테이블에 모든 필요한 정보를 담는다.
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;

}
