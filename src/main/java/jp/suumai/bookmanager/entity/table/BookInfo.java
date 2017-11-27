package jp.suumai.bookmanager.entity.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BOOK_INFO")
public class BookInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	private int bookId;

	@Column(name = "BOOK_NAME")
	private String bookName;

	@Column(name = "BOOK_ISBN")
	private String bookIsbn;

	@Column(name = "REG_DATE")
	private Date regDate;

	@Column(name = "REG_USER_ID")
	private String regUserId;

	@Column(name = "UPD_DATE")
	private Date updDate;

	@Column(name = "UPD_USER_ID")
	private String updUserId;

	@Column(name = "DEL_DATE")
	private Date delDate;

	@Column(name = "DEL_USER_ID")
	private String delUserId;

	@Column(name = "DEL_FLG")
	private boolean delFlg;
}
