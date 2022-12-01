package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class TitleGetDto {
    private String title;
    private String author;
    private int publishingYear;
    private List<Long> bookCopies;
}
