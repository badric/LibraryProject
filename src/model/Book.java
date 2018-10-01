package model;

public class Book {
	String title, autor, year, isbn, age, price;
	// int id;

	public Book() {

	}

//	Book(String title, String autor) {
//		this.title = title;
//
//		this.autor = autor;
//	}

	public String getTitle() {
		return title;
	}
	
	public String getAutor() {
		return autor;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}
