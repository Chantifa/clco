package ch.ffhs.clco.entity;

import jakarta.persistence.*;

@Table (name= "quote", schema = "clco")
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "quote_generator", sequenceName = "quote_seq", allocationSize = 1)
    private Integer id;

    @Column(length = 400)
    private String quote;


    private String author;

    // Default constructor (required by JPA)
    public Quote() {
    }

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
