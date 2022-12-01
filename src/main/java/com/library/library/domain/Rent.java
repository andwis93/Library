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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Rent")
public class Rent {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name ="RentId", unique = true)
    private long rentId;

    @NotNull
    @Column(name ="RentDate")
    private Date rentDate = new Date();

    @Column(name = "ReturnDate")
    private Date returnDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ReaderId")
    private Reader reader;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "rents")
    private List<Copy> copies = new ArrayList<>();

}
