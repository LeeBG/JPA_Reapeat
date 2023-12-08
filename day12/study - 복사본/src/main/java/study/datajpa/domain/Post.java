package study.datajpa.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long postNo;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_cd")
    private Board board;

    @Column(name="post_sj")
    private String postSj;

    private String postCn;
    private String regstrId;

    @CreatedDate
    private LocalDateTime regDt;

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    public Post(Board board, String postSj, String postCn, String regstrId) {
        if(board != null) {
            this.board = board;
        }
        this.postSj = postSj;
        this.postCn = postCn;
        this.regstrId = regstrId;
    }

    public void changeBoard(Board board) { // 게시판 변경
        this.board = board;
        board.getPosts().add(this);
    }
    
}
