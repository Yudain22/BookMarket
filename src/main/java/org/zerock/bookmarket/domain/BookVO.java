package org.zerock.bookmarket.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVO {
    private String id;
    private String name;
    private String description;
    private String category;
    private String author;
    private String publisher;
    private String releaseDate;
    private Integer pages;
    private Integer unitPrice;
    private Integer unitInStock;
    private String imgFileName;
    private LocalDate createDate;
}
