package study.datajpa.service2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.datajpa.domain.Board;
import study.datajpa.domain.Tag;
import study.datajpa.repository2.TagDTO;
import study.datajpa.repository2.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    // Tag 생성
    public TagDTO createTag(String tag) {
        Tag newTag = new Tag(tag);
        Tag savedTag = tagRepository.save(newTag);
        return convertToDTO(savedTag);
    }

    // Tag 조회
    public TagDTO getTag(Long tagNo) {
        Tag tag = tagRepository.findById(tagNo)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDTO(tag);
    }

    // Tag 수정
    public TagDTO updateTag(Long tagNo, String newTag) {
        Tag tag = tagRepository.findById(tagNo)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setTag(newTag);
        Tag savedTag = tagRepository.save(tag);
        return convertToDTO(savedTag);
    }

    // Tag 삭제
    public void deleteTag(Long tagNo) {
        Tag tag = tagRepository.findById(tagNo)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.delete(tag);
    }

    // 게시판 변경
    public void changeBoard(Long tagNo, Board board) {
        Tag tag = tagRepository.findById(tagNo)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.changeBoard(board);
        tagRepository.save(tag);
    }

    // Tag 엔티티를 TagDTO로 변환
    private TagDTO convertToDTO(Tag tag) {
        return new TagDTO(tag.getTagNo(), tag.getTag(), tag.getRegDt(), tag.getBoard());
    }
}
