package com.library.library.service;

import com.library.library.domain.Title;
import com.library.library.domain.TitleDto;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.TitleMapper;
import com.library.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper;

    public void saveTitle(final TitleDto titleDto) throws EmptyFieldException {
        if (titleDto.getTitle() != null) {
            Title title = titleMapper.mapToTitle(titleDto);
            titleRepository.save(title);
        } else {
            throw new EmptyFieldException();
        }
    }

    public Title findById(final long titleId) throws RecordNotExistsException {
        return titleRepository.findById(titleId).orElseThrow(RecordNotExistsException::new);
    }

    public Title findByTitle(final String title) throws RecordNotExistsException {
        return titleRepository.findByTitle(title.toUpperCase()).orElseThrow(RecordNotExistsException::new);
    }
}
