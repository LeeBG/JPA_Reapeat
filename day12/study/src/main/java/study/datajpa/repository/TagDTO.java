package study.datajpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.datajpa.domain.Board;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Long tagNo;
    private String tag;
    private LocalDateTime regDt;
    private Board board;
}
