package com.example.data.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	@SequenceGenerator(name="author_generator", sequenceName = "author_seq", allocationSize=50)
	private int id;
	private String firstName;
	private String lastName;
	private List<Book> books = new ArrayList<>();

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "authors")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Author() {
	}

	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		//        this.books = books;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	//    public Set<Book> getBooks() {
	//        return books;
	//    }
	//
	//    public void setBooks(Set<Book> books) {
	//        this.books = books;
	//    }

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
//				", books=" + books +
				'}';
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Author author = (Author) o;
//
//		return Objects.equals(id, author.id);
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

		Author author = (Author) o;

		return id == author.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
