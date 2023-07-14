package org.garkavaya.project2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "Book's name shouldn't be empty")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Author's name shouldn't be empty")
    @Size(min = 2, max = 70, message = "Author's name should be between 2 and 70 characters")
    @Column(name = "author")
    private String author;
    @Min(value = 1800, message = "Year of book should be greater then 1800")
    @Max(value = 2023, message = "Year of book should be lower then 2023")
    @Column(name = "year")
    private int year;

    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    public Book() {
    }

    public Book(int bookId, String name, String author, int year, Date takenAt, Person owner) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.year = year;
        this.takenAt = takenAt;
        this.owner = owner;

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        long milliseconds = System.currentTimeMillis() - takenAt.getTime();
        return (milliseconds / (24 * 60 * 60 * 1000)) >= 10;
    }
}
