package com.icia.board.service;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.BoardFileDTO;
import com.icia.board.dto.PageDTO;
import com.icia.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 있음, 없음 확인하기
        if(boardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일 없음
            System.out.println("파일없음");
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        }else {
            // 파일 있음
   /*
   1. 파일첨부 여부 값 1로 세팅하고 DB에 제목 등 내용 board_table에 저장처리
     2. 파일의 이름을 가져오고 DTO 필드값에 세팅
     3. 저장용 파일 이름 만들고 DTO 필드값에 세팅
     4. 로컬에 파일 저장
     5. board_file_table 에 저장 처리
     */
            System.out.println("파일있음");
            boardDTO.setFileAttached(1);
            BoardDTO dto = boardRepository.save(boardDTO);
            for(MultipartFile boardFile: boardDTO.getBoardFile()) {
                // 원본 파일 이름 가져오기
                String originalFilename = boardFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);
                //저장용 이름 만들기
                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());
                String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
                System.out.println("storedFileName = " + storedFileName);
                // 저장을 위한 BoardFileDTO 세팅
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFilename);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(dto.getId());
                // 로컬에 파일 저장
                // 저장할 경로 설정 (저장할 폴더 + 저장할이름) >> \ 백슬래시 2개로 감싸기 필수
                String savePath = "D:\\springframework_img\\" + storedFileName;
                // 저장처리 > 경로를 통해 저장하겠다
                boardFile.transferTo(new File(savePath));
//    transferTo 오류 발생 시 >  throws IOException 설정하기
                boardRepository.saveFile(boardFileDTO);
            }

        }

//        insert 전 > id = null 이므로, 후의 dto 로 진행할것


    }



    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }

    public void delete(Long id) {
     boardRepository.delete(id);
    }

    public List<BoardDTO> pagingList(int page) {
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        int pagingStart = (page-1) * pageLimit;
        Map<String, Integer> PagingParams = new HashMap<>();
        PagingParams.put("start", pagingStart);
        PagingParams.put("limit", pageLimit);
        List<BoardDTO> boardDTOList = boardRepository.pagingList(PagingParams);
        return boardDTOList;
    }
    public PageDTO pagingParam(int page) {
        int pageLimit = 3; // 한페이지에 보여줄 글 갯수
        int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수(하단 목록 갯수)
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산 > math.ceil 올림처리
        int maxPage = (int) (Math.ceil((double)boardCount /pageLimit));
        // 시작 페이지 값 계산 ( 1,4,7,10 ~~~ )
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 마지막 페이지 값 계산 ( 3,6,9,12 ~~ )
        int endPage = startPage + blockLimit -1;
        // 전체 페이지 갯수가 계산한 endPage 보다 작을 때는 endPage 값을 maxPage 값과 같게 세팅
        if(endPage > maxPage) {
            endPage = maxPage;
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);
        return pageDTO;

    }

    //    사용자가 요청한 검색어 결과에 대한 데이터를 가져오기 > search
    public List<BoardDTO> searchList(int page, String type, String q) {
        int pageLimit = 3; // 한페이지에 보여줄 글 갯수
        int pagingStart = (page-1) * pageLimit;
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("q", q);
        pagingParams.put("type", type);

        List<BoardDTO> boardDTOList = boardRepository.searchList(pagingParams);
        return boardDTOList;
    }

    public PageDTO pagingSearchParam(int page, String type, String q) {
        int pageLimit = 3; // 한페이지에 보여줄 글 갯수
        int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("q", q);
        pagingParams.put("type", type);
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardSearchCount(pagingParams);
        // 전체 페이지 갯수 계산
        int maxPage = (int) (Math.ceil((double)boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10 ~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 마지막 페이지 값 계산(3, 6, 9, 12 ~~)
        int endPage = startPage + blockLimit - 1;
        // 전체 페이지 갯수가 계산한 endPage 보다 작을 때는 endPage 값을 maxPage 값과 같게 세팅
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);
        return pageDTO;
    }


}
