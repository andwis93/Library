package com.library.library.service;

import com.library.library.domain.Reader;
import com.library.library.domain.ReaderDto;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.ReaderMapper;
import com.library.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReaderDbService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public void saveReader(final ReaderDto readerDto) throws EmptyFieldException {
        Reader reader = readerMapper.mapToReader(readerDto);
        if (reader.getLastName() != null) {
            reader.setAccountCreated(new Date());
            readerRepository.save(reader);
        } else {
            throw new EmptyFieldException();
        }
        readerRepository.save(reader);
    }

    public Reader findById(final Long readerId) throws RecordNotExistsException {
        return readerRepository.findById(readerId).orElseThrow(RecordNotExistsException::new);
    }

}
