package com.library.library.repository;

import com.library.library.domain.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CopyRepository extends CrudRepository<Copy,Long> {

    @Override
    Copy save(Copy bookCopy);

    @Override
    Optional<Copy> findById(Long copyId);

    @Override
    List<Copy> findAll();
}
