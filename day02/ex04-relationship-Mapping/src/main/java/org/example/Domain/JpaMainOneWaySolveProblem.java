package org.example.Domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainOneWaySolveProblem {
    public static void main(String[] args) {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//    EntityManager em = emf.createEntityManager();
//    EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//            TeamForOneWay team = new TeamForOneWay();
//
//
//            team.setUsername("TeamA");
//            em.persist(team);
//
//            MemberForOneWaySolveProblem member = new MemberForOneWaySolveProblem();
//            member.setUsername("member1");
//            member.setTeam(team); //팀과 멤버의 연관관계 맵핑 , JPA가 알아서 연관관계를 알아먹고 pk의 값을 가져온다
//            em.persist(member);
//
//
////          영속성 컨텍스트에 flush
////          영속성 매니저에서 clear
//            em.flush();
//            em.clear();
//
//
//            // 조회할 때 id가 아닌 객체로 조회를 할 수 있다. 연관관계 매핑이 됐기 때문이다.
//            //영속성 컨텍스트에 이미 있기 때문에 select구문이 실행되지 않는다.
//            //만약 쿼리문을 보고 싶다면 em.flush, em.clear를 사용한다.
//            MemberForOneWaySolveProblem findMember = em.find(MemberForOneWaySolveProblem.class, member.getId());
////            TeamForOneWay team1 = findMember.getTeam();
////            System.out.println(team1.getUsername());
//
//            //만약 팀을 바꾸고싶다면? 1차 캐시에서 가져왔기 때문이다.
//            TeamForOneWay teamForOneWay = em.find(TeamForOneWay.class, 100L);
////            findMember.setTeam(teamForOneWay);
//
//            //객체를 테이블에 맞춰서 데이터 중심으로 모델링하면, 협력관게를 만들 수 없다.
//            // 외래 키 조인을 사용해서 연관된 테이블을 찾는다.
//            // 객체는 참조를 사용해서 연관된 객체를 찾는다.
//            // 테이블과 객체 사이에는 이런 큰 차이점이 있다.
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//        emf.close();

    }
}
