package com.library.library.repository;

import com.library.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

    @Override
    Title save(Title title);

    @Override
    Optional<Title> findById(Long titleId);

    Optional<Title> findByTitle(String title);


}