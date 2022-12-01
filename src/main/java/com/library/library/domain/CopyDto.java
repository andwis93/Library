package com.library.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CopyDto {
    private String status;
    private long titleId;

    public CopyDto(String status) {
        this.status = status;
    }

}
