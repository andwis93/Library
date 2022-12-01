package com.library.library;

import com.library.library.domain.Copy;
import com.library.library.domain.Title;
import com.library.library.domain.Reader;
import com.library.library.domain.Rent;
import com.library.library.domain.support.Status;
import com.library.library.mapper.CopyMapper;
import com.library.library.repository.CopyRepository;
import com.library.library.repository.TitleRepository;
import com.library.library.repository.ReaderRepository;
import com.library.library.repository.RentRepository;
import com.library.library.service.CopyDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryTestSuite {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private RentRepository rentRepository;

    private CopyMapper copyMapper;

    private CopyDbService copyDbService;

    @Test
    void testReaderSave() {
        //Given
        Reader reader = new Reader("John", "Smith");

        //When
        readerRepository.save(reader);

        //Then
        long id = reader.getReaderId();
        Optional<Reader> readReader = readerRepository.findById(id);
        assertTrue(readReader.isPresent());

        //CleanUp
        try {
            readerRepository.deleteById(id);
        } catch (Exception err) {
            //do nothing
        }
    }

        @Test
        void testTitleSave () {
            //Given
            Copy copy = new Copy(Status.AVAILABLE.getStatus());
            Title title = new Title("Moby Dick", "Herman Melville", 1999);
            title.getCopies().add(copy);
            copy.setTitle(title);

            //When
            titleRepository.save(title);

            //Then
            long id = title.getTitleId();
            Optional<Title> readTitle = titleRepository.findById(id);
            assertTrue(readTitle.isPresent());

            //CleanUp
            try {
                titleRepository.deleteById(id);
            } catch (Exception err) {
                //do nothing
            }
        }

        @Test
        void testCopySave () {
            //Given
            Title title = new Title("Crime and Punishment", "Fyodor Dostoevsky", 1866);
            Copy copy = new Copy(Status.AVAILABLE.getStatus());
            title.getCopies().add(copy);
            copy.setTitle(title);

            //When
            copyRepository.save(copy);

            //Then
            long id = copy.getCopyId();
            Optional<Copy> readBookCopy = copyRepository.findById(id);
            assertTrue(readBookCopy.isPresent());

            //CleanUp
            try {
                copyRepository.deleteById(id);
            } catch (Exception err) {
                //do nothing
            }
        }

    @Test
    void testChangeCopyStatus() {
        //Given
        Title title = new Title("The Da Vinci Code", "Dan Brown",2007);
        Copy copy = new Copy(Status.AVAILABLE.getStatus());
        title.getCopies().add(copy);
        copy.setTitle(title);

        //When
        copyRepository.save(copy);
        long id = copy.getCopyId();
        System.out.println(id);
        Optional<Copy> theCopy = copyRepository.findById(id);
        theCopy.stream()
                .forEach(c ->{ c.setStatus(Status.LOST.getStatus().toUpperCase());
                    copyRepository.save(c);
                });

        String status = theCopy
                .map(c -> c.getStatus())
                .map(Object::toString)
                .orElse("");

        //Then
        assertEquals(Status.LOST.getStatus().toUpperCase(),status);

        //CleanUp
        try {
            copyRepository.deleteById(id);
        } catch (Exception err) {
            //do nothing
        }
    }

    @Test
    void testCopyGetByTitleAndStatus () {
        //Given
        Title title = new Title("Crime and Punishment", "Fyodor Dostoevsky", 1866);
        Copy copy = new Copy(Status.AVAILABLE.getStatus());
        title.getCopies().add(copy);
        copy.setTitle(title);
        copyRepository.save(copy);

        //When
        long copyId = copy.getCopyId();
        long titleId = title.getTitleId();
        List<Copy> copyList = copyRepository.findAll().stream()
                .filter(theCopy -> theCopy.getTitle().getTitleId() == titleId)
                .filter(theCopy -> theCopy.getStatus().equalsIgnoreCase(Status.AVAILABLE.getStatus()))
                .toList();

        //Then
        assertEquals(1,copyList.size());

        //CleanUp
        try {
            copyRepository.deleteById(copyId);
        } catch (Exception err) {
            //do nothing
        }
    }

    @Test
    void testRentSave() {
        //Given
        Title title = new Title("The Da Vinci Code", "Dan Brown",2007);
        Copy copy = new Copy(Status.AVAILABLE.getStatus());

        title.getCopies().add(copy);
        copy.setTitle(title);

        Reader reader = new Reader("John", "Smith");
        Rent rent = new Rent();

        copy.getRents().add(rent);
        rent.getCopies().add(copy);
        reader.getRents().add(rent);
        rent.setReader(reader);

        //When
        rentRepository.save(rent);
        long rentId = rent.getRentId();

        //Then
        assertNotEquals(0,rentId);

        //CleanUp
        try {
            rentRepository.deleteById(rent.getRentId());
        } catch (Exception err) {
            //do nothing
        }
    }


    @Test
    void testRentReturn() {
        //Given
        Title title = new Title("The Da Vinci Code", "Dan Brown",2007);
        Copy copy = new Copy(Status.AVAILABLE.getStatus());
        title.getCopies().add(copy);
        copy.setTitle(title);
        Reader reader = new Reader("John", "Smith");
        Rent rent = new Rent();

        copy.getRents().add(rent);
        rent.getCopies().add(copy);
        reader.getRents().add(rent);
        rent.setReader(reader);

        rentRepository.save(rent);
        long rentId = rent.getRentId();

        //When
            rent.setReturnDate(new Date());
            rent.getCopies()
                    .forEach(theCopy -> theCopy.setStatus(Status.AVAILABLE.getStatus().toUpperCase()));
            rentRepository.save(rent);
            Date returnDate = rent.getReturnDate();

        //Then
        assertNotNull(returnDate);

        //CleanUp
        try {
            rentRepository.deleteById(rent.getRentId());
        } catch (Exception err) {
            //do nothing
        }
    }
}
