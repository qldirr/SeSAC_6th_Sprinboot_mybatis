package codingon.spring_boot_mybatis.mapper;

import codingon.spring_boot_mybatis.domain.Board;
import codingon.spring_boot_mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE id = #{id}")
    Board findById(Long id);

    @Insert("INSERT INTO board (writer, title, content) VALUES (#{writer}, #{title}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Board board);

    @Update("UPDATE board SET writer = #{writer}, title = #{title}, content = #{content} WHERE id = #{id}")
    void update(Board board);

    @Delete("DELETE FROM board WHERE id = #{id}")
    void delete(Long id);
}
