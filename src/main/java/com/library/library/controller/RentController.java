package com.library.library.controller;


import com.library.library.controller.support.CheckStatus;
import com.library.library.domain.*;
import com.library.library.domain.support.Status;
import com.library.library.exceptions.CopyNotAvailableException;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.RentMapper;
import com.library.library.service.CopyDbService;
import com.library.library.service.ReaderDbService;
import com.library.library.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/library/rent")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentController {

    private final RentMapper rentMapper;
    private final RentDbService rentDbService;
    private final CopyDbService copyDbService;
    private final ReaderDbService readerDbService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRent(@RequestParam long readerId,
                                          @RequestParam List<Long> copies)
            throws EmptyFieldException, RecordNotExistsException, CopyNotAvailableException {

        if ((readerId != 0) & (!copies.isEmpty())) {
            Reader reader = readerDbService.findById(readerId);
            CheckStatus checkStatus = new CheckStatus();
            if (!checkStatus.checkCopyStatus(copies,copyDbService)) {
                for(Long copyId:copies) {
                    Rent rent = new Rent();
                    Copy copy = copyDbService.findById(copyId);
                    copy.setStatus(Status.RENTED.getStatus().toUpperCase());
                    reader.getRents().add(rent);
                    rent.setReader(reader);
                    copy.getRents().add(rent);
                    rent.getCopies().add(copy);
                    rentDbService.save(rent);
                }
                return ResponseEntity.ok().build();
            } else {
                throw new CopyNotAvailableException();
            }
        } else {
                throw new RecordNotExistsException();
        }
    }

    @PutMapping("return/{rentId}")
    public ResponseEntity<Copy> updateCopyStatus(@PathVariable Long rentId)
            throws RecordNotExistsException, EmptyFieldException {
        Rent rent = rentDbService.findById(rentId);
        if (rent != null) {
            rent.setReturnDate(new Date());
            rent.getCopies()
                    .forEach(copy -> copy.setStatus(Status.AVAILABLE.getStatus().toUpperCase()));
            rentDbService.save(rent);
            return ResponseEntity.ok().build();
        } else {
            throw new RecordNotExistsException();
        }
    }

    @GetMapping("{rentId}")
    public ResponseEntity<RentDto> findById(@PathVariable Long rentId) throws RecordNotExistsException {
        return ResponseEntity.ok(rentMapper.mapToRentDto(rentDbService.findById(rentId)));
    }

}
