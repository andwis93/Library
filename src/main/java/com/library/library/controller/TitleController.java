package com.library.library.controller;

import com.library.library.domain.TitleDto;
import com.library.library.domain.TitleGetDto;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.TitleMapper;
import com.library.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/booktitle")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TitleController {
    private final TitleDbService titleDbService;
    private final TitleMapper titleMapper;

    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleGetDto> getTitle(@PathVariable Long titleId) throws RecordNotExistsException {
        return ResponseEntity.ok(titleMapper.mapToTitleWithCopiesDto(titleDbService.findById(titleId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTitle(@RequestBody TitleDto titleDto) throws EmptyFieldException {
            titleDbService.saveTitle(titleDto);
            return ResponseEntity.ok().build();
    }
}
