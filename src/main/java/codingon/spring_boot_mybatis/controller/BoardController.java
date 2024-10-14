package codingon.spring_boot_mybatis.controller;

import codingon.spring_boot_mybatis.dto.BoardDTO;
import codingon.spring_boot_mybatis.dto.UserDTO;
import codingon.spring_boot_mybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    BoardService boardService;

    // 모든 사용자 정보를 리스트로 반환
    @GetMapping
    public List<BoardDTO> listBoards() {
        return boardService.getAllBoards();
    }

    // 새 사용자를 생성하고 생성된 사용자 정보 반환
    @PostMapping
    public BoardDTO createBoard(@RequestBody BoardDTO boardDTO) {
        // @RequestBody
        // - HTTP 요청 본문을 자바 객체로 변환
        boardService.createBoard(boardDTO);
        return boardDTO;
    }

    // 특정 id 의 사용자 정보 반환
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id){
        try {
            BoardDTO board = boardService.getBoardById(id);
        return ResponseEntity.ok(board);

        } catch (AccessDeniedException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    // 특정 ID 의 사용자 정보를 업데이트하고 업데이트된 정보를 반환
    @PutMapping("/{id}")
    public BoardDTO updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setId(id);
        boardService.updateBoard(boardDTO);
        return boardDTO;
    }

    // 특정 id 사용자 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
    }
}
