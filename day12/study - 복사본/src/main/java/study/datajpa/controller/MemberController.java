package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberDto;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @PostConstruct // 생성자
    public void init() {
        for (int i=0; i<100;i++){
            System.out.println("===============");
            Member member = memberRepository.save(new Member("user"+i, 10+i));
            System.out.println("i : "+ i +"\t member.getId() : " + member.getId());
            System.out.println("===============");
        }
    }

    @GetMapping("/members") // 엔티티로 반환하지 말고 DTO로 반환하는 것이 좋다 api스펙이 바뀌면 안되기 떄문에
    public Page<MemberDto> list(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) { // Page는 결과정보 = 인터페이스  // Pagable은 페이징 로직 정보
//        Page<Member> page = memberRepository.findAll(pageable);
//        PageRequest pageRequest = PageRequest.of(1,2);

        Page<MemberDto> pageDto =memberRepository.findAll(pageable).map(MemberDto::new);

        return pageDto;
    }

}
