package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.domain.PostTag;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    
}
