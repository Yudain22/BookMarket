package org.zerock.bookmarket.service;

import org.zerock.bookmarket.dto.BookDTO;

import java.util.List;

public interface BookService {
    Long register(BookDTO bookDTO);
    List<BookDTO> getAll();
    BookDTO readOne (String id);
    void remove(String id);
    void modify(BookDTO bookDTO);
   List<BookDTO> listALL();
}
