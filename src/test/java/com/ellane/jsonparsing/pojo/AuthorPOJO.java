package com.ellane.jsonparsing.pojo;

import java.util.List;

public class AuthorPOJO {

    private String authorName;
    private List<BooksPOJO> books;


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<BooksPOJO> getBooks() {
        return books;
    }

    public void setBooks(List<BooksPOJO> books) {
        this.books = books;
    }
}