package com.bookstore.javabean;

public class BookInfo {
    String bookName;
    String bookId;
    String version;
    String bookPrice;
    String copyrightId;
    String publishingId;
    String bookIntro;
    int id;

    public BookInfo(){

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(String copyrightId) {
        this.copyrightId = copyrightId;
    }

    public String getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(String publishingId) {
        this.publishingId = publishingId;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
