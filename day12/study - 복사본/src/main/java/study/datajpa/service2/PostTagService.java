package study.datajpa.service2;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.datajpa.domain.PostTag;
import study.datajpa.repository2.PostTagDTO;
import study.datajpa.repository2.PostTagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTagService {
    @Autowired
    private final PostTagRepository postTagRepository;

    public PostTagDTO save(PostTag postTag) {
        PostTag postTag1 = postTagRepository.save(postTag);
        PostTagDTO dto =  new PostTagDTO(postTag1.getBoardTagId(),postTag1.getPost(),postTag1.getBoard(),postTag1.getTag());
        return dto;
    }

    public PostTagDTO update(PostTag postTag) {
        PostTag postTag1 = postTagRepository.save(postTag);
        PostTagDTO dto =  new PostTagDTO(postTag1.getBoardTagId(),postTag1.getPost(),postTag1.getBoard(),postTag1.getTag());
        return dto;
    }

    public List<PostTagDTO> findAll() {
        List<PostTag> postTags = postTagRepository.findAll();
        List<PostTagDTO> dtos = new ArrayList<>();
        
        for(PostTag postTag : postTags) {
            dtos.add(new PostTagDTO(postTag.getBoardTagId(),postTag.getPost(),postTag.getBoard(),postTag.getTag()));
        }
        
        return dtos;
    }

    public PostTagDTO findById(Long id) {
        PostTag postTag = postTagRepository.findById(id).get();
        PostTagDTO dto =  new PostTagDTO(postTag.getBoardTagId(),postTag.getPost(),postTag.getBoard(),postTag.getTag());
        return dto;
    }

    
    // 삭제
    public void deleteById(Long id) {
        postTagRepository.deleteById(id);
    }

}
