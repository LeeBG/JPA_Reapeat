package org.example;

import org.example.domain.Address;
import org.example.domain.AddressEntity;
import org.example.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();


        try {
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // flush가 호출되는 경우에는 커밋과 쿼리가 동작할 때 createQuery, createNativeQuery가 동작할 때
            // JPQL이 내부적으로 flush
            List<Member> resultList = em.createNativeQuery
                    ("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER",Member.class)
                    .getResultList();



            // em.flush;
            // 결과가 안뜬다.
            // dbconn.executeQuery("select * from member");


            for(Member member1 : resultList) {
                System.out.println("member1  : " + member1 );
            }

            System.out.println("members : " + resultList);
            tx.commit();
        } catch (Exception e) {
            System.err.println("버그 발견!!!" + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 >> " + (m1 instanceof Member));
        System.out.println("m1 == m2 >> " + (m2 instanceof Member));
    }
}