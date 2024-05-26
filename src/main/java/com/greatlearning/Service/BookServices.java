package com.greatlearning.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.Entity.Book;



public interface BookServices {
	public List<Book> findAll();

	public Book findById(int theId);

	public void save(Book theBook);

	public void deleteById(int theId);

	public List<Book> searchBy(String name, String author);

}
