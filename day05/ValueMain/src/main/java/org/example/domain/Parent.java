package org.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 다른 엔티티에서도 child와 연관관계가 있다면 CascadeType.ALL을 쓰면 안된다.
    // 운영이 너무 힘들어진다. 단일 엔티티에 완전 종속적일 경우에는 사용해도 무방하다.
    // 고아객체 - 참조하는 곳이 하나일 떄 사용해야한다.
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
