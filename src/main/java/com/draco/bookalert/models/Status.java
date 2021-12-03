package com.draco.bookalert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "tinyint(3)", nullable = false)
    private long id;

    @OneToMany(mappedBy = "status")
    private List<BookUser> bookUserList;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BookUser> getStatus() {
        return bookUserList;
    }

    public void setStatus(List<BookUser> bookUserList) {
        this.bookUserList = bookUserList;
    }
}
