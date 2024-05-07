package org.zerock.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.bookmarket.dto.BookMemberDTO;
import org.zerock.bookmarket.mapper.MemberMapper;
import org.zerock.bookmarket.service.BookService;
import org.zerock.bookmarket.service.MemberService;
import org.zerock.bookmarket.service.MemberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor

public class BookMemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/join")
    public String join(BookMemberDTO memberDTO) {
        return "/member/join";
    }

    @PostMapping("/join")
    public String addJoin(BookMemberDTO memberDTO) {
                memberService.register(memberDTO);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpServletRequest req, BookMemberDTO memberDTO) {
        try {

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberService.login(memberDTO));

        }
        catch (Exception e) {

            return "redirect:/member/login?error=fail";
        }
        return "redirect:/";


    }

    @GetMapping("/login")
    public void getLogin() {
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        //session 을 변수로 설정했기 때문에 중복 사용 가능
        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();

        return "redirect:/";
    }


}
