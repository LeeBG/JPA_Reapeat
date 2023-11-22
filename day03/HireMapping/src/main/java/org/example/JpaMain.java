package org.example;


import org.example.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 상속 관계 매핑
// - 관계형 데이터베이스는 상속관계 X
// - 슈퍼타입 서브 타입 관계라는 모델링 기법이 객체 상속과 유사
// - 상속관계 매핑 : 객체의 상속 구조와 DB의 슈퍼타이브 상속관계를 매핑
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // @Ma
        try {
            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("admin");

            em.persist(member);

            // @MappedSuperclass는 조회가 안된다.
            // 추상 클래스로 만드는 것을 추천한다.
//          Member member = em.find(Member.class, member.getId());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}