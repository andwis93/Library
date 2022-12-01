package com.library.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "BookTitle")
public class Title {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TitleId",unique = true)
    private long titleId;

    @NotNull
    @Column(name = "Title")
    private String title;

    @Column(name = "Author")
    private String author;

    @Column(name = "PublishingYear")
    private int publishingYear;

    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Copy> copies = new ArrayList<>();

    public Title(String title, String author, int publishingYear) {
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
    }

}
