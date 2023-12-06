package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 구현체를 Spring Data JPA가 알아서 만들어 줌
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);
}
