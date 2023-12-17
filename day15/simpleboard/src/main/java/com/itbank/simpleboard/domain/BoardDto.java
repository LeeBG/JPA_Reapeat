package com.itbank.simpleboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long memberId;

    // 글 id
    private Long boardId;
    // 제목
    private String title;
    private String content;

    // 작성자 이름만
    private Member writer;

    // 작성일자
    private LocalDateTime createDate;
}
