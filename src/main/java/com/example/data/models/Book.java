package com.example.data.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
	@SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=50)
	private int id;

	private String isbn;
	private String title;

	@ManyToOne
	private Publisher publisher;

	private List<Author> authors = new ArrayList<>();

	public Book() {
	}
	public Book(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "author_book", //TODO: uncomment & move upto declaration
//			joinColumns = @JoinColumn(name = "book_id"),
//			inverseJoinColumns = @JoinColumn(name = "author_id"))
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	@ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", isbn='" + isbn + '\'' +
				", authors=" + authors +
				'}';
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Book book = (Book) o;
//
//		return Objects.equals(id, book.id);
//	}
//
//	@Override
//	public int hashCode() {
//		return id != null ? id.hashCode() : 0;
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		return id == book.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
