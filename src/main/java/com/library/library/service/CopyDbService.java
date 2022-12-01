package com.library.library.service;

import com.library.library.domain.Copy;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyDbService {

    private final CopyRepository copyRepository;

    public Copy saveCopy(final Copy copy) throws EmptyFieldException, RecordNotExistsException {
        return copyRepository.save(copy);
    }

    public Copy findById(final Long copyId) throws RecordNotExistsException {
        return copyRepository.findById(copyId).orElseThrow(RecordNotExistsException::new);
    }

    public List<Copy> getAllCopies(){
        return copyRepository.findAll();
    }
}
