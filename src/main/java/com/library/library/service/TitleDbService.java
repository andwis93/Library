package com.library.library.service;

import com.library.library.domain.Title;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository titleRepository;

    public Title saveTitle(final Title title) throws EmptyFieldException {
        return titleRepository.save(title);
    }

    public Title findById(final long titleId) throws RecordNotExistsException {
        return titleRepository.findById(titleId).orElseThrow(RecordNotExistsException::new);
    }

    public Title findByTitle(final String title) throws RecordNotExistsException {
        return titleRepository.findByTitle(title).orElseThrow(RecordNotExistsException::new);
    }

}
