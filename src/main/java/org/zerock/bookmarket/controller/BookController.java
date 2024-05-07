package org.zerock.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.bookmarket.dto.BookDTO;
import org.zerock.bookmarket.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

        @GetMapping("/books")
        public String list(Model model) {
            try {
                List<BookDTO> dtoList = bookService.listALL();
                model.addAttribute("book", dtoList);
                return "/book/books"; // Assuming bookList.jsp is your JSP page
            } catch(Exception e) {
                log.error("Error occurred while listing books", e);
                try {
                    throw new ServletException("Error occurred while listing books", e);
                } catch (ServletException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

//    @RequestMapping("/books")
//    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//        try{
//            List<BookDTO> dtoList = bookService.listALL();
//            req.setAttribute("dtoList", dtoList);
//            req.getRequestDispatcher("/books").forward(req, resp);
//        }catch(Exception e){
//            log.error(e);
//            throw new ServletException("list error");
//        }
//    }

    @GetMapping("/addBook")
    public void addBookGET() {
        log.info("todo register.......");
    }


    @PostMapping("/addBook")
    public String addBookPost(MultipartFile file,
                               @Valid BookDTO bookDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) throws IOException {
        log.info("POST todo register.......");
        //실제 파일 이름 출력
        log.info(file.getOriginalFilename());
        //파일의 크기
        log.info(file.getSize());
        //파일의 확장자
        log.info(file.getContentType());
        //파일을 저장하는 메서드 new File("파일을 저장할 경로//파일이름.확장자")
        file.transferTo(new File("C://files//" + file.getOriginalFilename()));
        bookDTO.setImgFileName(file.getOriginalFilename());
        if (bindingResult.hasErrors()) {
            log.info("has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "/book/addBook?error=fail";
        }
        Long no = bookService.register(bookDTO);
//        redirectAttributes.addFlashAttribute("result",no);
        return "redirect:/book/books";
    }

    @GetMapping("/book")
    public void read(String id, Model model) {

        model.addAttribute("book", bookService.readOne(id));
    }

    //기존에 URL 파라미터를 사용하는 메서드 방식은 get 방식이였고
    //post는 폼에 히든으로 숨겨서 전달합니다.
    @GetMapping("/remove")
    public String remove(String id) {
        log.info("REMOVE todo register.......");
        log.info("id : " + id);
        bookService.remove(id);
        return "redirect:/book/editBook";
    }

    @GetMapping("/editBook")
    public void edit(BookDTO bookDTO, Model model) {
            model.addAttribute("book", bookService.listALL());
    }

    @GetMapping("/modify")
    public void modify(String id, Model model) {
            model.addAttribute("book", bookService.readOne(id));
    }
    @PostMapping("/modify")
    public String modify(@Valid BookDTO bookDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         MultipartFile file
                        ) throws IOException {
        log.info("POST todo register.......");
        //실제 파일 이름 출력
        log.info(file.getOriginalFilename());
        //파일의 크기
        log.info(file.getSize());
        //파일의 확장자
        log.info(file.getContentType());
        //파일을 저장하는 메서드 new File("파일을 저장할 경로//파일이름.확장자")
        file.transferTo(new File("C://files//" + file.getOriginalFilename()));
        bookDTO.setImgFileName(file.getOriginalFilename());
        if (bindingResult.hasErrors()) {
            log.info("has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", bookDTO.getId());
            return "redirect:/book/modify?error=fail";
        }
        log.info(bookDTO);
        bookService.modify(bookDTO);
        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("book",bookDTO.getId());
        return "redirect:/book/editBook";
    }



}
