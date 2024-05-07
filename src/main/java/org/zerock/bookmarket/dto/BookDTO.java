package org.zerock.bookmarket.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String category;
    @NotEmpty
    private String author;
    @NotEmpty
    private String publisher;
    @NotEmpty
    private String releaseDate;
    @NotNull
    private Integer pages;
    @NotNull
    private Integer unitPrice;
    private Integer unitInStock;
    private String imgFileName;
    private LocalDate createDate;
}
