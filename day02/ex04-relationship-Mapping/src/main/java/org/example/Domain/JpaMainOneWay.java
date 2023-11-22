package org.example.Domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// Day02
public class JpaMainOneWay {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();


    TeamForOneWay team = new TeamForOneWay();


    team.setUsername("TeamA");
        em.persist(team);

        MemberForOneWay member = new MemberForOneWay();
        member.setUsername("member1");
        member.setTeamId(team.getId()); //애매한 부분 외래키 식별자를 직접 다루는 상황
        em.persist(member);     // PK값이 세팅이 되고 영속 상태가 된다.

        //조회시 이슈
        //객체지향스럽지 못하게 계속 JPA에 질의를 해야 한다.

        MemberForOneWay findMember = em.find(MemberForOneWay.class, member.getId());
        Long findTeamId = findMember.getTeamId();

        TeamForOneWay findTeam = em.find(TeamForOneWay.class, findTeamId);

        //객체를 테이블에 맞춰서 데이터 중심으로 모델링하면, 협력관게를 만들 수 없다.
        // 외래 키 조인을 사용해서 연관된 테이블을 찾는다.
        // 객체는 참조를 사용해서 연관된 객체를 찾는다.
        // 테이블과 객체 사이에는 이런 큰 차이점이 있다.


        tx.begin();

        try {

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
