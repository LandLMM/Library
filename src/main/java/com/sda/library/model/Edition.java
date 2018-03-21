package com.sda.library.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "editions")
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edition_id")
    private Integer editionID;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn (name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn (name = "publisher_id")
    private Publisher publisher;

    public Edition() {
    }

    public Edition(Integer number, Book book, Publisher publisher) {
        this.number = number;
        this.book = book;
        this.publisher = publisher;
    }

    public Integer getEditionID() {
        return editionID;
    }

    public void setEditionID(Integer editionID) {
        this.editionID = editionID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
