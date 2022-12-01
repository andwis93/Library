package com.library.library.mapper;

import com.library.library.domain.Reader;
import com.library.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getFirstName(),
                readerDto.getLastName()
        );
    }

}
