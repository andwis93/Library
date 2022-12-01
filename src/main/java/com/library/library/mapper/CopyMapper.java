package com.library.library.mapper;

import com.library.library.domain.Copy;
import com.library.library.domain.CopyDto;
import com.library.library.domain.CopyGetDto;
import com.library.library.domain.CopyGetListDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CopyMapper {

    public Copy mapToCopy(final CopyDto copyDto) {
        return new Copy(
                copyDto.getStatus().toUpperCase()
        );
    }

    public CopyGetDto mapToGetCopyDto(final Copy copy) {
        return new CopyGetDto(
                copy.getStatus().toUpperCase(),
                copy.getTitle().getTitle()
        );
    }

    public CopyGetListDto mapToGetCopyToListDto(final Copy copy) {
        return new CopyGetListDto(
                copy.getCopyId(),
                copy.getStatus().toUpperCase(),
                copy.getTitle().getTitle()
        );
    }

    public List<CopyGetListDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(this::mapToGetCopyToListDto)
                .toList();
    }

}
