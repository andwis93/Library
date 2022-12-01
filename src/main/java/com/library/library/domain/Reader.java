package com.library.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Reader")
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ReaderId", unique = true)
    private long readerId;

    @NotNull
    @Column(name = "FirstName")
    private String firstName;

    @NotNull
    @Column(name = "LastName")
    private String lastName;

    @NotNull
    @Column(name = "AccountCreationDate")
    private Date accountCreated = new Date();

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
