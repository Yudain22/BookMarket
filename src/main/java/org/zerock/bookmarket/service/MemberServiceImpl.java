package org.zerock.bookmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.bookmarket.domain.BookMemberVO;
import org.zerock.bookmarket.dto.BookMemberDTO;
import org.zerock.bookmarket.mapper.MemberMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public BookMemberDTO login(BookMemberDTO memberDTO){
        BookMemberVO bookMemberVO = memberMapper.login(modelMapper.map(memberDTO, BookMemberVO.class));

        return modelMapper.map(bookMemberVO, BookMemberDTO.class);
    };
    @Override
    public void register(BookMemberDTO bookMemberDTO){
        BookMemberVO bookMemberVO = modelMapper.map(bookMemberDTO, BookMemberVO.class);
        memberMapper.register(bookMemberVO);
    };
}
