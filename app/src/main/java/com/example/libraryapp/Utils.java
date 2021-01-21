package com.example.libraryapp;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> favoriteBooks;
    private static ArrayList<Book> reservedBooks;
    private static ArrayList<Book> borrowedBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
        if (null == reservedBooks) {
            reservedBooks = new ArrayList<>();
        }
        if (null == borrowedBooks) {
            borrowedBooks = new ArrayList<>();
        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add(new Book(1, "Carrie", "Stephen King", 300,
                "https://images-na.ssl-images-amazon.com/images/I/51Tfl0+Bn2L._SX324_BO1,204,203,200_.jpg",
                "Carrie is an epistolary horror novel by American author Stephen King",
                "Carrie was his first published novel, released on April 5, 1974, with a " +
                        "first print-run of 30,000 copies.[1] Set primarily in the then-future year of 1979, " +
                        "it revolves around the eponymous Carrie White, an unpopular friendless misfit and bullied " +
                        "high-school girl from an abusive religious household."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
        allBooks.add(new Book(2, "Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 350,
                "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg",
                "Harry Potter and the Philosopher's Stone is a fantasy novel" +
                        " written by British author J. K. Rowling.",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage " +
                        "on his eleventh birthday, when he receives a letter of acceptance to " +
                        "Hogwarts School of Witchcraft and Wizardry."));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public static ArrayList<Book> getReservedBooks() {
        return reservedBooks;
    }

    public static ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public Book getBookById(int id) {
        for (Book b : allBooks){
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToReserved(Book book) {
        return reservedBooks.add(book);
    }

    public boolean addToWishlist(Book book) {
        return wantToReadBooks.add(book);
    }


}
