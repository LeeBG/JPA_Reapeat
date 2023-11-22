package org.example.Domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainNewTeamProblem {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TeamForTwoWay team = new TeamForTwoWay();
            team.setUsername("TeamA");
//            team.getMembers().add(member)
            em.persist(team);

            MemberForOneWaySolveProblem member = new MemberForOneWaySolveProblem();
            member.setUsername("member1");
            // 연관관계 주인에서만 값을 세팅하면 된다.
            member.setTeam(team);       // **
            em.persist(member);

            // 양쪽으로 값을 세팅해주는것이 좋다.
            team.getMembers().add(member); //**

            // 이것을 지우면 1차 캐시에 그대로 있어서 쿼리문이 실행이 안된다.
            em.flush();
            em.clear();
            
            // 팀 조회해서 이 팀에 속한 멤버 리스트를 뽑기.
            TeamForTwoWay findTeam = em.find(TeamForTwoWay.class, team.getId()); // 1차 캐시 조회된게 그대로 튀어나옴
            List<MemberForOneWaySolveProblem> members = findTeam.getMembers();   // 컬


            System.out.println("==============================");
            // 리스트 출력 - 연관관계 조회
            for(MemberForOneWaySolveProblem m : members) {
                System.out.println(m.toString());
                System.out.println(m.getTeam().toString());
                System.out.println("m : "+ m.getUsername());
            }
            System.out.println("==============================");









            // 조회
            TeamForTwoWay findTeam2 = em.find(TeamForTwoWay.class, team.getId());
            int memberSize = findTeam2.getMembers().size(); // 역방향 조회




            System.out.println("==============================");
            System.out.println("memberSize : " + memberSize);
            System.out.println("==============================");

//          영속성 컨텍스트에 flush
//          영속성 매니저에서 clear
//            em.flush();
//            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
