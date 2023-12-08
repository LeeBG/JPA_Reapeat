package study.datajpa.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private Long tagNo;

    private String tag;

    @CreatedDate
    private LocalDateTime regDt;

    @ManyToOne(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_cd")
    private Board board;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTags = new ArrayList<>();


    public Tag(String tag) {
        this.tag = tag;
    }

    public void changeBoard(Board board) { // 게시판 변경
        this.board = board;
        board.getTags().add(this);
    }
}
