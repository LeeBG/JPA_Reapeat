package study.datajpa.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "board_def")
public class Board {
    @Id
    @Column(name = "board_cd")
    private String boardCd;

    @Column(name = "board_nm")
    private String boardNm;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<PostTag> postTags = new ArrayList<>();

}
