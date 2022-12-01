package com.library.library.repository;

import com.library.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Override
     Reader save(Reader reader) ;

    @Override
    Optional<Reader> findById(Long readerId);

}
