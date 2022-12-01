package com.library.library.service;

import com.library.library.domain.Reader;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderDbService {
    private final ReaderRepository readerRepository;

    public Reader saveReader(final Reader reader) throws EmptyFieldException {
                return readerRepository.save(reader);
    }

    public Reader findById(final Long readerId) throws RecordNotExistsException {
        return readerRepository.findById(readerId).orElseThrow(RecordNotExistsException::new);
    }

}
