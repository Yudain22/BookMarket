package org.zerock.bookmarket.mapper;

import org.zerock.bookmarket.domain.BookVO;

import java.util.List;

public interface BookMapper {
    String getTime();

    Long insert(BookVO bookVO);

    List<BookVO> selectAll();

    BookVO selectOne(String id);

    void delete(String id);

    void update(BookVO bookVO);
}
