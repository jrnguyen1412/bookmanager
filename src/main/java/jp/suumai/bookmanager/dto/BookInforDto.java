package jp.suumai.bookmanager.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BookInforDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bookId;
	private String bookName;
	private String bookIsbn;
	private Date regDate;
	private String regUserId;
	private String regId;
	private Date updDate;
	private String updUserId;
	private String updId;
	private Date delDate;
	private String delUserId;
	private String delId;
	private boolean delFlg;
	private String bookImage;
}
