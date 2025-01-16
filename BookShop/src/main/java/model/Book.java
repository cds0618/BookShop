package model;

import java.sql.Date;

public class Book {
	private int bookID;          //도서번호
	private String bookName;     //도서명
	private double price;        //가격
	private String author;       //저자명
	private String description;  //설명
	private String publisher;    //출판사
	private String category;     //도서분류
	private long unitsInStock;   //재고수
	private long totalPages;     //총 페이지수
	private Date releaseDate;  //출판일
	private String imageUrl;     //이미지 경로
	
	//디폴트 생성자 
	//원래 안써도 되는데 생성자를 오버로딩하려고 씀, 오버로딩 할때 디폴트 생성자 안쓰면 에러남
	public Book() {}
	
	//이름에 DTO를 안썻을뿐! 매개변수 9개인 생성자!
	public Book(int bookID, String bookName, double price, String author, String description, String publisher, String category, long unitsInStock, long totalPages, Date releaseDate, String imageUrl) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.price = price;
		this.author = author;
		this.description = description;
		this.publisher = publisher;
		this.category = category;
		this.unitsInStock = unitsInStock;
		this.totalPages = totalPages;
		this.releaseDate = releaseDate;
		this.imageUrl = imageUrl;
	}

	public Book(String bookName, double price, String author, String description, String publisher, String category, long unitsInStock, long totalPages, Date releaseDate, String imageUrl) {
		this.bookName = bookName;
		this.price = price;
		this.author = author;
		this.description = description;
		this.publisher = publisher;
		this.category = category;
		this.unitsInStock = unitsInStock;
		this.totalPages = totalPages;
		this.releaseDate = releaseDate;
		this.imageUrl = imageUrl;
	}
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
