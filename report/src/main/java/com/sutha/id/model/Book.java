package com.sutha.id.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOOK")
	@TableGenerator(name = "BOOK", table = "T_SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "BOOK", valueColumnName = "SEQ_VAL", allocationSize = 1, initialValue = 1)
	@Column(name = "id", nullable = false, length = 11)
	private Long id;

	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "author", nullable = true, length = 150)
	private String author;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date", nullable = false)
	private Date releaseDate;

	@Column(name = "price", nullable = false, length = 11)
	private Float price;

	@Column(name = "stock", length = 11, nullable = false)
	private Integer stock;

	@Column(name = "status_delete", nullable = true)
	private Boolean del;

	@Column(name = "delete_time", nullable = true)
	private Date delTime;

	@Column(name = "create_time", nullable = true)
	private Date createTime;

	@Column(name = "update_time", nullable = true)
	private Date updateTime;

}
