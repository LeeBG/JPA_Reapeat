package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
