package org.example;

import org.example.domain.Member;
import org.example.domain.Order;
import org.example.domain.OrderItem;
import org.example.domain.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();


        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setTeam(teamA);
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setTeam(teamB);
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

//            Member m = em.find(Member.class, member1.getId());
//            System.out.println("m  = " + m.getTeam().getClass());

//            System.out.println("===============");
//            m.getTeam().getName();      // 초기화
//            System.out.println("===============");


//            Member m1 = em.getReference(Member.class, member1.getId());
//            System.out.println("m1 = " + m1.getClass());
//
//            Member reference = em.find(Member.class, member1.getId());
//            System.out.println("reference = " + reference.getClass());


//            System.out.println("refmember = " + refmember.getClass());
//            System.out.println("isLoaded = "  + emf.getPersistenceUnitUtil().isLoaded(refmember));
            // false


//            Hibernate.initialize(refmember);// 값을 안 불어오고 강제 프록시 초기화
//
//            System.out.println("isLoaded = "  + emf.getPersistenceUnitUtil().isLoaded(refmember));
            // true


            // proxy로 조회를 해버리면 뒤에것도 proxy로 반환
//            Member findmember = em.find(Member.class, member1.getId());
//            System.out.println("findmember = " + findmember.getClass());

//            System.out.println("refmember == findmember >> " + (refmember == findmember));


            // 영속성 컨텍스트 관리 안해
//            em.detach(refmember);
//            em.clear();
//            System.out.println("refMember = " + refmember.getUsername());


            // find : 데이터베이스를 통해서 실제 엔티티 객체를 조회한 것이다.
            // getReference : 진짜로 주는 것이 아니라 프록시(대체자(가짜) Entity)엔티티를 주는 것이다.
//            Member findMember = em.getReference(Member.class, member.getId());
//            System.out.println("findMember : "+ findMember.getClass()); // 하이버네이트가 만들어낸 가짜 클래스
//            System.out.println("findMember.id : " + findMember.getId());
//            System.out.println("findMember.username : " + findMember.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
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