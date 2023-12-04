package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor // final 만 걸려있는 필드를 생성자 argument에 포함시켜줌
public class MemberService {

    // 의존성 주입 (필드 주입)
    private final MemberRepository memberRepository;

    // 의존성 주입 (생성자 주입)
//    @Autowired -> 스프링이 알아서 주입
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 의존성 주입 (수정자 주입)
    // - 단점 : application 로딩 시점에 조립이 끝나버린다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    /*
        회원가입
     */

    @Transactional
    public Long join(Member member) {
        validationDuplicateMember(member); // 중복 확인
        memberRepository.save(member);
        return member.getId();
    }

    private void validationDuplicateMember(Member member) {
        // Exception을 던지거나 ....
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    // 전체 회원 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    // 하나의 회원 조회
    @Transactional(readOnly = true) // 성능이 증가한다.
    public Member findOne(Long memberId) {
        Member findMember = memberRepository.findOne(memberId);
        return findMember;
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name); // JPA가 영속성 컨텍스트에서 id로 긁어와서 영속상태의 member의 이름을 바꿔주면
        // 트랜잭션 AOP가 끝나는 시점에서 em.flush 되고 데이터베이스 트랜잭션을 커밋한다.
    }
}
