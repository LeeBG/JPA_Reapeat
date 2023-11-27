package hellojpa;

import hellojpa.domain.*;

import javax.persistence.*;
import java.util.Collection;
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
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("관리자1");;
            member1.setTeam(teamA);
            member1.setAge(10);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(teamB);
            member2.setAge(20);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("관리자3");
            member3.setTeam(teamB);
            member3.setAge(30);
            em.persist(member3);

            em.flush();
            em.clear();

            // from절에 서브 쿼리 안된다.
            String query = "select distinct t From Team t join fetch t.members";

            List<Team> result = em.createQuery(query,Team.class).getResultList();

            System.out.println("result " + result.size());

            // 프록시가 아니다.
            for(Team team : result) {
                System.out.println("team = " + team.getName() + "[MEMBERS : " + team.getMembers().size()+"]");
                for(Member m : team.getMembers()) {
                    System.out.println("이름 : "+m.getUsername());
                    System.out.println("나이 : " + m.getAge());
                }
            }

            tx.commit();
        } catch (Exception e) {
            System.err.println("버그!!! : " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}