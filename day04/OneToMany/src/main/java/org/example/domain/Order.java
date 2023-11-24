package org.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne  // 여러 개의 주문이(OrderDTO) 하나의 Member
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")  // 연관관계의 주인은 외래키가 들어가있는 OrderItem 테이블에 존재, mappedBy에 해당하는 ManyToOne을 찾아서 작성해주자
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; //SpringBoot에서는 네이밍 관례를 변경할 수 있다. orderDate -> ORDER_DATE

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}

