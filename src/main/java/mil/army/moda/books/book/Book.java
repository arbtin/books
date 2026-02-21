package mil.army.moda.books.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private Boolean isCheckedOut;
    private Integer pageCount;
    private String isbn;
    private Integer chapterCount;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getChapterCount() {
        return chapterCount;
    }

    public Book() {
    }

    public Book(String title, String author, Boolean isCheckedOut, Integer pageCount, String isbn, Integer chapterCount) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = isCheckedOut;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.chapterCount = chapterCount;
    }

//    public Book setId(Long id) {
//        this.id = id;
//        return this;
//    }
    // Or
    public void setId(Long id) {
        this.id = id;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book setIsCheckedOut(Boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
        return this;
    }

    public Book setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Book setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
        return this;
    }
}
