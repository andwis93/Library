package com.library.library.mapper;

import com.library.library.domain.Title;
import com.library.library.domain.TitleDto;
import com.library.library.domain.TitleGetDto;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublishingYear()
        );
    }

    public TitleGetDto mapToTitleWithCopiesDto(final Title title) {
        return new TitleGetDto(
                title.getTitle(),
                title.getAuthor(),
                title.getPublishingYear(),
                title.getCopies().stream()
                        .map(bookCopy -> bookCopy.getCopyId())
                        .toList()
        );
    }

}
