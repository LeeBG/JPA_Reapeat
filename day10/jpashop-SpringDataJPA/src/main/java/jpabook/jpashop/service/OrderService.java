package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepositoryOld;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepositoryOld memberRepositoryOld;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepositoryOld.findOne(memberId);
        
        // 아이템 조회
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        
        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 // 하나만 저장하는 이유 -> CascadeType.ALL
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 취소
     */

    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 하나 찾아서
        Order order = orderRepository.findOne(orderId);
        // 주문취소
        order.cancel();
        // JPA의 강점 - 일반적으로는 update를 엄청 많이 해야하는데 Entity안의 데이터들을 변경하면
        // 더티체킹(변경내역을 감지해서 update쿼리를 막 날린다.)
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

    /**
     * 검색
     */
//
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch)
//    }
}
