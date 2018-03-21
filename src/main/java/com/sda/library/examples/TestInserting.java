package com.sda.library.examples;

import com.sda.library.model.Author;
import com.sda.library.model.Book;
import com.sda.library.model.Edition;
import com.sda.library.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestInserting {

    public static void main(String[] args) {
        Publisher publisher = new Publisher("Oxford University Press", "Oxford");
        Author nielsen = new Author("Michael", "Nielsen", null);
        Author chuang = new Author("Isaac", "Chuang", null);
        Set<Author> authors = new HashSet<Author>();
        authors.add(nielsen);
        authors.add(chuang);
        Book book = new Book("Quantum information and quantum computation", authors);
        Edition edition = new Edition(1, book, publisher);
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try(Session session = factory.openSession()) {
                Transaction trans = null;
                try {
                    trans = session.beginTransaction();
                    System.out.println("====== Insert start: ");
                    session.save(edition);
                    System.out.println(book.getEditions().size());
                    trans.commit();
                } catch(RuntimeException e) {
                    if(trans != null) {
                        trans.rollback();
                    }
                    e.printStackTrace();
                }
            }

            List<Author> isaacs = findByFirstName(factory, "Isaac");
            isaacs.forEach(author -> System.out.println(author.getFirstName() + " " +
                    author.getLastName()));
        }

    }

    private static List<Author> findByFirstName(SessionFactory factory, String firstName) {
        try(Session session = factory.openSession()) {
            Query<Author> q = session.createQuery("FROM Author author " +
                    "WHERE author.firstName = :firstName");
            q.setParameter("firstName", firstName);
            return q.getResultList();
        }
    }
}
