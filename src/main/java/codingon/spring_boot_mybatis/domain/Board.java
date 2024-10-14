package codingon.spring_boot_mybatis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private String registered;
}
