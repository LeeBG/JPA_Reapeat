package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() throws Exception{
        System.out.println("memberRepository = " + memberRepository.getClass()); // Proxy - SpringDataJpa가 Appliocation 로딩 시점에
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);
        Member findMember =
                memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회
        Member findMemeber1 = memberRepository.findById(member1.getId()).get();
        Member findMemeber2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMemeber1).isEqualTo(member1);
        assertThat(findMemeber2).isEqualTo(member2);

        // 리스트 조회 검증

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);

    }


    @Test
    public void page() throws Exception{
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        //when
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(10, pageRequest);

        page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        //then
        List<Member> content = page.getContent(); //조회된 데이터

        assertThat(content.size()).isEqualTo(3); //조회된 데이터 수
        assertThat(page.getTotalElements()).isEqualTo(5); //전체 데이터 수
        assertThat(page.getNumber()).isEqualTo(0); //페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); //전체 페이지 번호
        assertThat(page.isFirst()).isTrue(); //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue(); //다음 페이지가 있는가?
    }


    @Test
    public void bulkUpdate() throws Exception{
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        // when ( 벌크 연산 )
        int resultCount = memberRepository.bulkAgePlus(20);
//        em.clear();
        // Spring Data JPA는 이것을 지원해준다.

        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);

        //then
        assertThat(resultCount).isEqualTo(3);
    }


    @Test
    public void findMemberLazy() {
        // given
        // member1 -> teamA
        // member2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member("member1", 10, teamA));
        memberRepository.save(new Member("member2", 20, teamB));

        em.flush();
        em.clear(); // 영속성 컨텍스트를 다 날린

        //when
        // N + 1 문제가 발생한다. (쿼리가 N + 1 나가는 문제는 fetch조인으로 해결 가능하다)
        // JPA에서는 fetch조인으로 해결할 수 있다.
        // 그냥 순수하게 멤버 객체만 뽑아오는것
//        List<Member> members = memberRepository.findMemberByFetchJoin();
        List<Member> members = memberRepository.findAll();

        //then
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.teamClass = " + member.getTeam().getClass());
            // Lazy 로딩이기 때문에 Team이 필요한 시점에서 가짜 객체(프록시)를 가지고 온다.
            // = 프록시를 초기화한다.
            System.out.println("member.team = " + member.getTeam().getName());
        }

    }

    @Test
    public void queryHint() throws Exception{
        // given
        Member member1 =new Member("member1", 10);
        memberRepository.save(member1);
        em.flush(); // JPA안에 있는 결과를 DB에 공개하고 남아있고
        em.clear(); // 영속성 컨텍스트 날아감

        // when // readOnly는 변경 감지 체크를 하지 않는다.
        Member findMemeber = memberRepository.findReadOnlyByUsername(member1.getUsername());
        findMemeber.setUsername("member2");

        em.flush();// flush를 하면 상태가 바뀐것을 인지한다. JPA가 변경감지(더티체킹)
        // 이러한 변경감지의 치명적인 단점 - 내부적으로 성능최적화를 하겠지만 비효율적이다.
    }

    @Test
    public void lock() throws Exception{
        // given
        Member member1 =new Member("member1", 10);
        memberRepository.save(member1);
        // when
        em.flush();
        em.clear();
        //then
        List<Member> result = memberRepository.findLockByUsername("member1");
    }
    
    @Test
    public void custom() throws Exception{
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        // given
        List<Member> result = memberRepository.findMemberCustom();
        // when
        for(Member member : result) {
            System.out.println("===============");
            System.out.println(member.toString());
            System.out.println("===============");
        }
        //then
    }
    @Test
    public void projections() throws Exception{
        // given
        
        // when
        
        //then
    }

}