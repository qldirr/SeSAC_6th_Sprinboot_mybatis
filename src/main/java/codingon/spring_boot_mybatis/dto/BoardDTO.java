package codingon.spring_boot_mybatis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private int no;
}
