package org.zerock.bookmarket.service;

import org.zerock.bookmarket.dto.BookDTO;
import org.zerock.bookmarket.dto.BookMemberDTO;

import java.util.List;

public interface MemberService {
    BookMemberDTO login(BookMemberDTO bookMemberDTO);
    void register(BookMemberDTO bookMemberDTO);
}
