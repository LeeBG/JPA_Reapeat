package com.itbank.simpleboard.service;

import com.itbank.simpleboard.domain.Board;
import com.itbank.simpleboard.domain.Member;
import com.itbank.simpleboard.domain.MemberDto;
import com.itbank.simpleboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void join(MemberDto dto) {
        Member member = new Member();
        member.setAddress(dto.getAddress());
        member.setAge(dto.getAge());
        member.setEmail(dto.getEmail());
        member.setUsername(dto.getUsername());
        member.setPhoneNum(dto.getPhoneNum());
        member.setUserId(dto.getUserId());
        member.setUserPw(dto.getUserPw());

        // 회원 가입(저장)
        Member savedMemeber = memberRepository.save(member);
        System.err.println(savedMemeber);
    }

    public Member login(MemberDto dto) {
        Optional<Member> member = memberRepository.findByUserIdAndUserPw(dto.getUserId(), dto.getUserPw());
        return member.get();
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();

        return member;
    }

}
