package org.zerock.bookmarket.mapper;

import org.zerock.bookmarket.domain.BookMemberVO;

public interface MemberMapper {

    String getTime();
    BookMemberVO login(BookMemberVO bookMemberVO);

    void register(BookMemberVO bookMemberVO);

}
