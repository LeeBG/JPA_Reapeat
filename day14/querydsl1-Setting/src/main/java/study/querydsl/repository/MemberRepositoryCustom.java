package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom{
    public List<MemberTeamDto> search(MemberSearchCondition condition);
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    public Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable);
}
