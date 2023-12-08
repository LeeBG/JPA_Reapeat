package study.datajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.datajpa.domain.Post;
import study.datajpa.repository.PostDTO;
import study.datajpa.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // 게시글 생성
    public PostDTO createPost(Post post) {
        Post post1 = postRepository.save(post);
        PostDTO dto = new PostDTO();
        dto.setPostCn(post1.getPostCn());
        dto.setPostNo(post1.getPostNo());
        dto.setPostSj(post1.getPostSj());
        dto.setRegDt(post1.getRegDt());
        dto.setRegstrId(post1.getRegstrId());
        return dto;
    }

    // 게시글 조회 (특정 아이디)
    public PostDTO getPost(Long id) {
        Post post1 = postRepository.findById(id).get();
        PostDTO dto = new PostDTO();
        dto.setPostCn(post1.getPostCn());
        dto.setPostNo(post1.getPostNo());
        dto.setPostSj(post1.getPostSj());
        dto.setRegDt(post1.getRegDt());
        dto.setRegstrId(post1.getRegstrId());
        return dto;
    }

    // 전체 게시글 조회
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> dtos = new ArrayList<>();

        for(Post post : posts) {
            dtos.add(new PostDTO(post.getPostNo(),post.getPostSj(),post.getPostCn(),post.getRegstrId(),post.getRegDt()));
        }
        return dtos;
    }

    // 게시글 수정
    public PostDTO updatePost(Post post) {
        Post post1 = postRepository.save(post);
        PostDTO dto = new PostDTO();
        dto.setPostCn(post1.getPostCn());
        dto.setPostNo(post1.getPostNo());
        dto.setPostSj(post1.getPostSj());
        dto.setRegDt(post1.getRegDt());
        dto.setRegstrId(post1.getRegstrId());
        return dto;
    }

    // 게시글 삭제 (특정 아이디)
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
