package com.library.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "BookCopy")
public class Copy {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BookCopyId",unique = true)
    private long copyId;

    @Column(name = "Status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BookTitleId")
    private Title title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CopyAndRent",
            joinColumns = {@JoinColumn(name = "BookCopyId", referencedColumnName = "BookCopyId")},
            inverseJoinColumns = {@JoinColumn(name = "RentId", referencedColumnName = "RentId")}
    )
    private List<Rent> rents = new ArrayList<>();

    public Copy(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Copy bookCopy)) return false;
        return getCopyId() == bookCopy.getCopyId() && Objects.equals(getStatus(), bookCopy.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCopyId(), getStatus());
    }

}
