package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testModify() {

        //변경에 필요한 데이터
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated....101")
                .content("Updated content 101...")
                .build();

        // 첨부파일을 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));

        boardService.modify(boardDTO);

    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list((pageRequestDTO));

        log.info(responseDTO);
    }

    // 하나의 게시물에 3개의 이미지 파일이 추가된 경우를 테스트 (board 테이블과 board_image 테이블에 insert문이 실행되는지 확인)
    @Test
    public void testRegisterWithImages() {

        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("File...Sample Tilte...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_ccc.jpg"
                ));

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    // 첨부파일이 있는 게시물의 번호를 이용해서 게시물(Board)과 첨부파일(BoardImage)의 정보가 한번에 처리되는지 확인
    @Test
    public void tsetReadAll() {

        Long bno = 101L;

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        for (String fileName : boardDTO.getFileNames()) {
            log.info(fileName);
        }  // end for
    }

}
