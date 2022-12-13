package com.library.library.controller;

import com.library.library.domain.ReaderDto;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/reader")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReaderController {
    private final ReaderDbService readerDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto) throws EmptyFieldException {
        readerDbService.saveReader(readerDto);
        return ResponseEntity.ok().build();
    }

}
