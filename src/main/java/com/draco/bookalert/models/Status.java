package com.draco.bookalert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "status")
    private List<BookUser> bookUserList;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookUser> getBookUserList() {
        return bookUserList;
    }

    public void setBookUserList(List<BookUser> bookUserList) {
        this.bookUserList = bookUserList;
    }

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
