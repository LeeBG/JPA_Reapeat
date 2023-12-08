package study.datajpa.repository2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.datajpa.domain.Board;
import study.datajpa.domain.Post;
import study.datajpa.domain.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTagDTO {
    private Long boardTagId;
    private Post post;
    private Board board;
    private Tag tag;
}
