package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CopyGetListDto {
    private long copyId;
    private String status;
    private String title;
}
