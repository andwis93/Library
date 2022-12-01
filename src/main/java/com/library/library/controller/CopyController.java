package com.library.library.controller;

import com.library.library.domain.*;
import com.library.library.domain.support.Status;
import com.library.library.exceptions.EmptyFieldException;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.CopyMapper;
import com.library.library.repository.CopyRepository;
import com.library.library.service.CopyDbService;
import com.library.library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library/bookcopy")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CopyController {
    private final CopyDbService copyDbService;
    private final TitleDbService titleDbService;
    private final CopyMapper copyMapper;
    private final CopyRepository copyRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCopy(@RequestBody CopyDto copyDto)
            throws EmptyFieldException, RecordNotExistsException {
        long byTitleId = copyDto.getTitleId();
        if ((Status.getEnumFromString(copyDto.getStatus()) != null) & (byTitleId != 0)){
            Copy copy = copyMapper.mapToCopy(copyDto);
            Title title = titleDbService.findById(byTitleId);
            title.getCopies().add(copy);
            copy.setTitle(title);
            copyDbService.saveCopy(copy);
            return ResponseEntity.ok().build();
        } else {
            throw new RecordNotExistsException();
        }
    }

        @PutMapping("{id}")
        public ResponseEntity<Copy> updateCopyStatus(@RequestBody CopyDto copyDto, @PathVariable Long id)
                throws RecordNotExistsException {
                Copy copy = copyMapper.mapToCopy(copyDto);
            if (Status.getEnumFromString(copy.getStatus()) != null) {
                copyRepository.findById(id)
                        .map(bookCopy1 -> {
                            bookCopy1.setStatus(copy.getStatus());
                            copyRepository.save(bookCopy1);
                            return ResponseEntity.ok().build();
                        })
                        .orElseThrow(RecordNotExistsException::new);
                return ResponseEntity.ok().build();
            } else {
                throw new RecordNotExistsException();
            }
        }

    @GetMapping("{copyId}")
    public ResponseEntity<CopyGetDto> findById(@PathVariable Long copyId) throws RecordNotExistsException {
        return ResponseEntity.ok(copyMapper.mapToGetCopyDto(copyDbService.findById(copyId)));
    }

    @GetMapping("/FindByTitle")
    @ResponseBody
    public ResponseEntity<List<CopyGetListDto>> findCopiesByTitle(
            @RequestParam String title, @RequestParam String status) throws RecordNotExistsException {
            Long titleId = titleDbService.findByTitle(title).getTitleId();
            List<Copy> copyList = copyDbService.getAllCopies().stream()
                    .filter(copy -> copy.getTitle().getTitleId() == titleId)
                    .filter(copy -> copy.getStatus().equalsIgnoreCase(status))
                    .toList();
                        return ResponseEntity.ok(copyMapper.mapToCopyDtoList(copyList));
    }

}
