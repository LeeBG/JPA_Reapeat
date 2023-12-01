package jpabook.jpashop.service;


import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // findItem은 영속상태입니다.
        Item findItem = itemRepository.findOne(itemId);
//        findItem.change(price, name, stock) -> setter같은 것보다는 의미있는 메서드를 만들어서 사용하자
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        // @Transactional 때문에 commit이 일어나면서 em.flush가 일어난다.
        // 영속성태의 객체를 변경을 감지하는 방법이다.
    }

    // 병합은 준영속 상태의 엔티티를 영속상태로 변경하는 방법이다.

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
