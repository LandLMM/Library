package com.sda.library.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors", uniqueConstraints = {@UniqueConstraint(columnNames = {
        "first_name", "last_name", "nick"
})
})
public class Author { // jest wlascicielem relacji

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nick")
    private String nick;

    //@ManyToMany
    // Ksiazka ma autora, autor ma ksiazke, a jesli ksiazka ma autora,
    // a autor nie ma ksiazki to moze wystapic problem z niespojoscia w grafie

    @ManyToMany
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "authors_books", //Autor jest wlascicielem, wiec posiada adnotacje 'JoinTable', a nie 'JoinColumn'
            joinColumns = {@JoinColumn(name = "author_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id", nullable = false)})

    private Set<Book> books = new HashSet();

    public Author() {
    }

    public Author(String firstName, String lastName, String nick) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
