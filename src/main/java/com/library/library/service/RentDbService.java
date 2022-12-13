package com.library.library.service;

import com.library.library.controller.support.CheckStatus;
import com.library.library.domain.Copy;
import com.library.library.domain.Reader;
import com.library.library.domain.Rent;
import com.library.library.domain.support.Status;
import com.library.library.exceptions.CopyNotAvailableException;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository rentRepository;
    private final CopyDbService copyDbService;
    private final ReaderDbService readerDbService;

    public Rent save(final Rent rent, final long readerId, final List<Long> copies) throws EmptyFieldException, RecordNotExistsException, CopyNotAvailableException {
        Reader reader = readerDbService.findById(readerId);
        if ((readerId != 0) & (!copies.isEmpty())) {
            CheckStatus checkStatus = new CheckStatus();
            if (!checkStatus.checkCopyStatus(copies,copyDbService)) {
                for(Long copyId:copies) {
                    Copy copy = copyDbService.findById(copyId);
                    copy.setStatus(Status.RENTED.getStatus().toUpperCase());
                    reader.getRents().add(rent);
                    rent.setReader(reader);
                    copy.getRents().add(rent);
                    rent.getCopies().add(copy);
                    rentRepository.save(rent);
                }
            } else {
                throw new CopyNotAvailableException();
            }
            return rentRepository.save(rent);
        } else {
            throw new RecordNotExistsException();
        }
    }

    public Rent findById(final long rentId) throws RecordNotExistsException {
        return rentRepository.findById(rentId).orElseThrow(RecordNotExistsException::new);
    }

    public void updateRent(final Long rentId) throws RecordNotExistsException {
        Rent rent = findById(rentId);
        if (rent != null) {
        rent.setReturnDate(new Date());
        rent.getCopies()
                .forEach(copy -> copy.setStatus(Status.AVAILABLE.getStatus().toUpperCase()));
            rentRepository.save(rent);
        } else {
            throw new RecordNotExistsException();
        }
    }
}