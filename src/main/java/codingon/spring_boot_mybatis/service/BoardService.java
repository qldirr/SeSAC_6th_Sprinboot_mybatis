package codingon.spring_boot_mybatis.service;

import codingon.spring_boot_mybatis.domain.Board;
import codingon.spring_boot_mybatis.domain.User;
import codingon.spring_boot_mybatis.dto.BoardDTO;
import codingon.spring_boot_mybatis.dto.UserDTO;
import codingon.spring_boot_mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    // 모든 사용자의 정보를 BoardDTO 리스트로 반환
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardMapper.findAll(); // 서비스 계층 -> 매퍼 계층

        List<BoardDTO> boardDTOS = new ArrayList<>();

        for (Board board : boards) {
            BoardDTO boardDTO = convertToDto(board);
            boardDTOS.add(boardDTO);
        }

        return boardDTOS;
    }

    // 새 사용자 생성
    public void createBoard(BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        boardMapper.insert(board);
    }

    // 특정 id 의 사용자 정보를 UserDTO로 반환
    public BoardDTO getBoardById(Long id) throws AccessDeniedException {
        Board board = boardMapper.findById(id);
        if (board == null || !"a".equals(board.getWriter())){
            throw new AccessDeniedException("Access Denied!!");
        }
        return convertToDto(board);
    }

    // 사용자 정보 업데이트
    public void updateBoard(BoardDTO boardDTO){
        Board board = convertToEntity(boardDTO);
        boardMapper.update(board);
    }

    // 특정 id 사용자 삭제
    public void deleteBoard(Long id){
        boardMapper.delete(id);
    }

    // domain to dto
    private BoardDTO convertToDto(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setWriter(board.getWriter());
        dto.setNo((int) (board.getId() + 100));

        return dto;
    }

    // dto to domain
    private Board convertToEntity(BoardDTO dto) {
        Board board = new Board();
        board.setId(dto.getId());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(dto.getWriter());

        return board;
    }
}
