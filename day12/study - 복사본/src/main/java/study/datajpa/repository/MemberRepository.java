package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom { // 여기 선언한 Member 도메인 클래스
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username= :username and m.age = :age") // JPA NamedQuery
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.repository.MemberDto(m.id, m.username, t.name) " +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username = :name")
    Member findMembers(@Param("name") String username);


    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    /*
        스프링 부트 3 이상을 사용하면 하이버네이트 6이 적용된다.
        이 경우 하이버네이트 6에서 의미없는 left join을 최적화 해버린다. 따라서
        다음을 실행하면 SQL이 LEFT JOIN을 하지 않는 것으로 보인다.
     */
    @Query(value = "select m from Member m left join m.team t")
    Page<Member> findByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findMemberAllCountBy(Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberByFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"}) // Member도 조회하고 team도 같이 조회하고 싶어
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"}) // Member도 조회하고 team도 같이 조회하고 싶어
    @Query("select m from Member m")
    List<Member> findByMemberEntityGraph();

    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적은 읽기,쓰기를 둘 다 막아버리지만, 낙관적은 일기 만 허용
    List<Member> findLockByUsername(String username);

}
