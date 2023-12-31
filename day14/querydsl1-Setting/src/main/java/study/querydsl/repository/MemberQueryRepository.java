package study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

}
