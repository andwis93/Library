package com.library.library.controller;

import com.library.library.domain.Reader;
import com.library.library.domain.ReaderDto;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.mapper.ReaderMapper;
import com.library.library.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/library/reader")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReaderController {
    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto) throws EmptyFieldException {
    Reader reader = readerMapper.mapToReader(readerDto);
          if (reader.getLastName() != null) {
              reader.setAccountCreated(new Date());
              readerDbService.saveReader(reader);
              return ResponseEntity.ok().build();
       } else {
        throw new EmptyFieldException();
        }
    }

}
