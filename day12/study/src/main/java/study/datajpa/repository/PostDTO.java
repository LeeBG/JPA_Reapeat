package study.datajpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postNo;
    private String postSj;
    private String postCn;
    private String regstrId;
    private LocalDateTime regDt;
}
