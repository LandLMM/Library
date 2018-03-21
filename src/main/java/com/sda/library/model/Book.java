package com.sda.library.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "books")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")// Edycja jest wlascicielem relacji na grafie w odniesieniu do book
    private Set<Edition> editions = new HashSet<>();

    public Book() {
    }

    public Book(String title, Set<Author> authors) {
        this.title = title;
        //this.authors = authors;
        authors.forEach(this::addAuthor);
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Edition> getEditions() {
        return editions;
    }

    public void setEditions(Set<Edition> editions) {
        this.editions = editions;
    }


    public void addAuthor(Author author) {
        authors.add(author); // dodaje autora
        author.getBooks().add(this); //do ksiazek autora dodaje jeszcze siebie
    }

    public static Book firstEdition(String title, Set<Author> authors, Publisher publisher) {
        Book book = new Book(title, authors);
        book.getEditions().add(new Edition());
    }
}

