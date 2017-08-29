package com.sutha.id.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_book")
public class Book2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String author;
	private Date releaseDate;
	private Float price;
	private Integer stock;
	private Boolean del;
	private Date delTime;
	private Date createTime;
	private Date updateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 11)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "author", nullable = true, length = 150)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date", nullable = false)
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "price", nullable = false, length = 11)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "stock", length = 11, nullable = false)
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "status_delete", nullable = true)
	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
	
	@Column(name = "delete_time", nullable = true)
	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	
	@Column(name = "create_time", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	@Column(name = "update_time", nullable = true)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}
