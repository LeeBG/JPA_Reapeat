package hellojpa;

import hellojpa.domain.*;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {


//            // 반환타입이 명확할 때
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//
//            // 반환 타입이 명확하지 않을 때
//            Query query3 = em.createQuery("select m.username from Member m");

        // 안나올떄에는 빈 컬렉션이 반환된다.
//            query.getResultList();

//            Member result = query.getSingleResult();
//
//            // 안 나올떄나 많이 나올 때에는..
//            // Exception이 발생한다.
//            // SingleResult가 결과가 정확하게 하나만 나와야한다.
//            System.out.println("result : " + result);


//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            List<MemberDTO> result = em.createQuery("select new hellojpa.domain.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO username = " + result.get(0));


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);


            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            em.persist(member);

            // from절에 서브 쿼리 안된다.
            String query = "select mm.age from (select m.age from Member m) as mm";
            List<Member> result =  em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("result = " + result.size());

            tx.commit();
        } catch (Exception e) {
            System.err.println("버그!!!" + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}