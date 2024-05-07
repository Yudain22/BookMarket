package org.zerock.bookmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.bookmarket.domain.BookVO;
import org.zerock.bookmarket.dto.BookDTO;
import org.zerock.bookmarket.mapper.BookMapper;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final ModelMapper modelMapper;

    @Override
    public Long register(BookDTO bookDTO){
        log.info(modelMapper);
        BookVO bookVO = modelMapper.map(bookDTO, BookVO.class);
        Long no = bookMapper.insert(bookVO);
        return no;
    }
    @Override
    public List<BookDTO> getAll(){

        List<BookDTO> dtoList = bookMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,BookDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public BookDTO readOne(String id){
        BookVO bookVO = bookMapper.selectOne(id);
        BookDTO bookDTO = modelMapper.map(bookVO, BookDTO.class);
        return bookDTO;
    }

    @Override
    public void remove(String id){
        bookMapper.delete(id);
    }

    @Override
    public void modify(BookDTO bookDTO){
        BookVO bookVO = modelMapper.map(bookDTO, BookVO.class);
        bookMapper.update(bookVO);


        }

    @Override
    public List<BookDTO> listALL(){
        List<BookVO> voList = bookMapper.selectAll();
        log.info("voList..................");
        List<BookDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,BookDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}

