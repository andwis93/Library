package com.library.library.service;

import com.library.library.domain.Copy;
import com.library.library.domain.CopyDto;
import com.library.library.domain.Title;
import com.library.library.domain.support.Status;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.CopyMapper;
import com.library.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyDbService {
    private final CopyRepository copyRepository;
    private final TitleDbService titleDbService;
    private final CopyMapper copyMapper;

    public void saveCopy(CopyDto copyDto) throws RecordNotExistsException {
        long byTitleId = copyDto.getTitleId();
        if ((Status.getEnumFromString(copyDto.getStatus()) != null) & (byTitleId != 0)){
            Copy copy = copyMapper.mapToCopy(copyDto);
            Title title = titleDbService.findById(byTitleId);
            title.getCopies().add(copy);
            copy.setTitle(title);
            copyRepository.save(copy);
        } else {
            throw new RecordNotExistsException();
        }
    }

    public Copy findById(final Long copyId) throws RecordNotExistsException {
        return copyRepository.findById(copyId).orElseThrow(RecordNotExistsException::new);
    }

    public List<Copy> getAllCopies(){
        return copyRepository.findAll();
    }

    public List<Copy> getAllCopiesByTitle(String title, String status) throws RecordNotExistsException {
        long titleId = titleDbService.findByTitle(title.toUpperCase()).getTitleId();
        return getAllCopies().stream()
                .filter(copy -> copy.getTitle().getTitleId() == titleId)
                .filter(copy -> copy.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public void updateCopyStatus(CopyDto copyDto, Long id) throws RecordNotExistsException {
        Copy copy = copyMapper.mapToCopy(copyDto);
        if (Status.getEnumFromString(copy.getStatus()) != null) {
            copyRepository.findById(id)
                    .map(bookCopy1 -> {
                        bookCopy1.setStatus(copy.getStatus());
                        copyRepository.save(bookCopy1);
                        return ResponseEntity.ok().build();
                    })
                    .orElseThrow(RecordNotExistsException::new);
        } else {
            throw new RecordNotExistsException();
        }
    }
}
