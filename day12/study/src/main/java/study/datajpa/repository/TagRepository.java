package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
