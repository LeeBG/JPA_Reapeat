package org.example;

import org.example.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();


        try {
            Address address = new Address("city", "street", "1234");

            // Embeded 타입을 공유하면 위험하다 못잡는 버그가 발생할 수 있다.
            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address newAddress = new Address("newCity",address.getStreet(),address.getZipCode());
            member1.setHomeAddress(newAddress);


            tx.commit();
        } catch (Exception e) {
            System.err.println("ㅈ버그 발견!!!" + e.getMessage());
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