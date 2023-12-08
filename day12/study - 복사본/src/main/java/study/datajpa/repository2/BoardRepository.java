package study.datajpa.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.domain.Board;

public interface BoardRepository extends JpaRepository<Board, String> {

}
