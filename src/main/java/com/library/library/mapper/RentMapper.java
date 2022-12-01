package com.library.library.mapper;

import com.library.library.domain.Rent;
import com.library.library.domain.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentMapper {

    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(

        );
    }

    public RentDto mapToRentDto(final Rent rent) {
       RentDto rentDto = new RentDto();
       rentDto.setRentDate(rent.getRentDate());
       rentDto.setReturnDate(rent.getReturnDate());
       rentDto.setReaderId(rent.getReader().getReaderId());
       List<Long> copyIdList = rent.getCopies().stream()
                       .map(copy -> copy.getCopyId())
                               .toList();
       rentDto.setCopyId(copyIdList);
       return rentDto;
    }
}
