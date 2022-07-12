package com.example.mongoclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingTests {

    public static void main(String[] args) {

        List<Book> books = Book.getBooks();
        List<String> recentBookSequence = Book.getRecentBookSequence();
        LinkedHashMap<Integer, List<String>> booksGroupByPages = books.stream().collect(Collectors.groupingBy
                (Book::getNoOfPages, LinkedHashMap::new, Collectors.mapping(Book::getBookName, Collectors.toList())));
        System.out.println("booksGroupByPages = " + booksGroupByPages);

        List<String> NoPageAndRecentBookNames = new ArrayList<>();

        booksGroupByPages.forEach((pageCount, bookNames) -> {
//            if (bookNames.size() == 1) {
//                NoPageAndRecentBookNames.addAll(bookNames);
//            } else {
//                bookNames.sort(Comparator.comparing(recentBookSequence::indexOf));
//                NoPageAndRecentBookNames.addAll(bookNames);
//            }
            if (bookNames.size() > 1) {
                bookNames.sort(Comparator.comparing(recentBookSequence::indexOf));
            }
            NoPageAndRecentBookNames.addAll(bookNames);

        });

        System.out.println("NoPageAndRecentBookNames = " + NoPageAndRecentBookNames);
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Book {
    public static final String BOOK_101_RECENT_1 = "book101_recent1";
    public static final String BOOK_80_RECENT_3 = "book80_recent3";
    public static final String BOOK_80_RECENT_1 = "book80_recent1";
    public static final String BOOK_80_RECENT_2 = "book80_recent2";
    public static final String BOOK_70_RECENT_1 = "book70_recent1";
    private String bookName;
    private int noOfPages;

    public static List<Book> getBooks() {
        Book book1 = new Book(BOOK_101_RECENT_1, 101);
        Book book2 = new Book(BOOK_80_RECENT_3, 80);
        Book book3 = new Book(BOOK_80_RECENT_1, 80);
        Book book4 = new Book(BOOK_80_RECENT_2, 80);
        Book book5 = new Book(BOOK_70_RECENT_1, 70);

        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5);
        return books;
    }

    public static List<String> getRecentBookSequence() {
        return Arrays.asList(BOOK_80_RECENT_1, BOOK_80_RECENT_2, BOOK_80_RECENT_3);
    }
}
