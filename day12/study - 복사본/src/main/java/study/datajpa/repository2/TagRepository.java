package study.datajpa.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.domain.Board;
import study.datajpa.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
