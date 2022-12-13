package com.library.library.controller;

import com.library.library.domain.*;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.mapper.CopyMapper;
import com.library.library.service.CopyDbService;
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
    private final CopyMapper copyMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCopy(@RequestBody CopyDto copyDto) throws RecordNotExistsException {
        copyDbService.saveCopy(copyDto);
        return ResponseEntity.ok().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Copy> updateCopyStatus(@RequestBody CopyDto copyDto, @PathVariable Long id)
            throws RecordNotExistsException {
        copyDbService.updateCopyStatus(copyDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{copyId}")
    public ResponseEntity<CopyGetDto> findById(@PathVariable Long copyId) throws RecordNotExistsException {
        return ResponseEntity.ok(copyMapper.mapToGetCopyDto(copyDbService.findById(copyId)));
    }

    @GetMapping("/FindByTitle")
    @ResponseBody
    public ResponseEntity<List<CopyGetListDto>> findCopiesByTitle(
            @RequestParam String title, @RequestParam String status) throws RecordNotExistsException {
        return ResponseEntity.ok(copyMapper.mapToCopyDtoList(copyDbService.getAllCopiesByTitle(title, status)));
    }

}
