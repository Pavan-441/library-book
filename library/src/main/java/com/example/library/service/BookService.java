




package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        try {
            return repository.save(book);
        } catch (Exception e) {
            System.err.println("Failed to add book: " + e.getMessage());
            return null;
        }
    }

    public List<Book> getAllBooks() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            System.err.println("Failed to retrieve books: " + e.getMessage());
            return null;
        }
    }

    public Book getBookById(int id) {
        try {
            Optional<Book> book = repository.findById(id);
            if (book.isPresent()) {
                return book.get();
            } else {
                System.out.println("Book with ID " + id + " not found.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error fetching book by ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    public void deleteBook(int id) {
        try {
            Optional<Book> book = repository.findById(id);
            if (book.isPresent()) {
                repository.delete(book.get());
                System.out.println("Book with ID " + id + " has been deleted successfully.");
            } else {
                System.out.println("Cannot delete. Book with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Failed to delete book with ID " + id + ": " + e.getMessage());
        }
    }

    public Book updateAvailability(int id, boolean available) {
        try {
            Optional<Book> book = repository.findById(id);
            if (book.isPresent()) {
                Book existingBook = book.get();
                existingBook.setAvailable(available);
                return repository.save(existingBook);
            } else {
                System.out.println("Cannot update availability. Book with ID " + id + " not found.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Failed to update availability for book ID " + id + ": " + e.getMessage());
            return null;
        }
    }
}
