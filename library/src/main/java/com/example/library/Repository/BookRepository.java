package com.example.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
}
