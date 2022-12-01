package com.library.library.service;

import com.library.library.domain.Rent;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository rentRepository;

    public Rent save(final Rent rent) throws EmptyFieldException {
        return rentRepository.save(rent);
    }

    public Rent findById(final long rentId) throws RecordNotExistsException {
        return rentRepository.findById(rentId).orElseThrow(RecordNotExistsException::new);
    }
}