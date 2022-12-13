package com.library.library.controller;

import com.library.library.domain.*;
import com.library.library.exceptions.CopyNotAvailableException;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.RentMapper;
import com.library.library.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library/rent")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentController {

    private final RentMapper rentMapper;
    private final RentDbService rentDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRent(@RequestParam long readerId, @RequestParam List<Long> copies)
            throws EmptyFieldException, RecordNotExistsException, CopyNotAvailableException {
        rentDbService.save(new Rent(), readerId,copies  );
        return ResponseEntity.ok().build();
    }

    @PutMapping("return/{rentId}")
    public ResponseEntity<Copy> updateCopyStatus(@PathVariable Long rentId) throws RecordNotExistsException{
            rentDbService.updateRent(rentId);
            return ResponseEntity.ok().build();
    }

    @GetMapping("{rentId}")
    public ResponseEntity<RentDto> findById(@PathVariable Long rentId) throws RecordNotExistsException {
        return ResponseEntity.ok(rentMapper.mapToRentDto(rentDbService.findById(rentId)));
    }
}
