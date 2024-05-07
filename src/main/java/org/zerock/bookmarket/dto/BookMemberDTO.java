package org.zerock.bookmarket.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMemberDTO {

    @NotEmpty
    private String memberID;
    @NotEmpty
    private String memberPW;
    @NotEmpty
    private String memberName;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;
    @NotEmpty
    private String email;
    private LocalDate createDate;

}
