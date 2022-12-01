package com.library.library.repository;

import com.library.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    Rent save(Rent rent);

    @Override
    Optional<Rent> findById(Long copyId);
}
