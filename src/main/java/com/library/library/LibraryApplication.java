package com.library.library;

import com.library.library.controller.CopyController;
import com.library.library.controller.ReaderController;
import com.library.library.controller.RentController;
import com.library.library.controller.TitleController;
import com.library.library.domain.CopyDto;
import com.library.library.domain.ReaderDto;
import com.library.library.domain.RentDto;
import com.library.library.domain.TitleDto;
import com.library.library.domain.support.Status;
import com.library.library.mapper.TitleMapper;
import com.library.library.repository.CopyRepository;
import com.library.library.repository.ReaderRepository;
import com.library.library.repository.RentRepository;
import com.library.library.repository.TitleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);
    }

}
