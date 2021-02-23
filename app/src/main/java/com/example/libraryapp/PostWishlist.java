package com.example.libraryapp;

public class PostWishlist {
    private int userId;
    private int bookId;

    public PostWishlist(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }
}
