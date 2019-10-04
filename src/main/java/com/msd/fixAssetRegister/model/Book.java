package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@Table(name="book")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOOK_ID")
	private int bookId;

	@Column(name="ACCESSION_NO")
	private int accessionNo;

	@Column(name="AUTHOR_1")
	private String author1;

	@Column(name="AUTHOR_2")
	private String author2;

	@Column(name="AUTHOR_3")
	private String author3;

	@Column(name="AUTHOR_4")
	private String author4;

	@Column(name="CLASS_NO")
	private String classNo;

	@Column(name="CONTENTS")
	private String contents;

	@Column(name="CURRENCY_RATE")
	private String currencyRate;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="DATE_OF_PUBLICATION")
	private String dateOfPublication;

	@Column(name="EDITION")
	private String edition;

	@Column(name="EDITORS")
	private String editors;

	@Column(name="ISBN")
	private String isbn;

	@Column(name="LOCATION")
	private String location;

	@Column(name="MFN_NO")
	private int mfnNo;

	@Column(name="PGIM_ID")
	private int pgimId;

	@Column(name="PLACE_OF_PUBLICATION")
	private String placeOfPublication;

	@Column(name="PRICE")
	private double price;

	@Column(name="PRICE_RS")
	private double priceRs;

	@Column(name="PUBLISHER")
	private String publisher;

	@Column(name="PURCHASE_ORDER_NO")
	private String purchaseOrderNo;

	@Column(name="SERIES")
	private String series;

	@Column(name="SOURCE")
	private String source;

	@Column(name="SUPPLIER_NAME")
	private String supplierName;

	@Column(name="TITLE")
	private String title;

	public Book() {
	}

	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAccessionNo() {
		return this.accessionNo;
	}

	public void setAccessionNo(int accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getAuthor1() {
		return this.author1;
	}

	public void setAuthor1(String author1) {
		this.author1 = author1;
	}

	public String getAuthor2() {
		return this.author2;
	}

	public void setAuthor2(String author2) {
		this.author2 = author2;
	}

	public String getAuthor3() {
		return this.author3;
	}

	public void setAuthor3(String author3) {
		this.author3 = author3;
	}

	public String getAuthor4() {
		return this.author4;
	}

	public void setAuthor4(String author4) {
		this.author4 = author4;
	}

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCurrencyRate() {
		return this.currencyRate;
	}

	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = currencyRate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateOfPublication() {
		return this.dateOfPublication;
	}

	public void setDateOfPublication(String dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public String getEdition() {
		return this.edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getEditors() {
		return this.editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMfnNo() {
		return this.mfnNo;
	}

	public void setMfnNo(int mfnNo) {
		this.mfnNo = mfnNo;
	}

	public int getPgimId() {
		return this.pgimId;
	}

	public void setPgimId(int pgimId) {
		this.pgimId = pgimId;
	}

	public String getPlaceOfPublication() {
		return this.placeOfPublication;
	}

	public void setPlaceOfPublication(String placeOfPublication) {
		this.placeOfPublication = placeOfPublication;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPriceRs() {
		return this.priceRs;
	}

	public void setPriceRs(double priceRs) {
		this.priceRs = priceRs;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPurchaseOrderNo() {
		return this.purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}