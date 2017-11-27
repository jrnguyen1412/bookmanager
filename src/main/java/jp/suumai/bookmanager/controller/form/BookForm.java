package jp.suumai.bookmanager.controller.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bookId;
	private String bookName;
	private String bookIsbn;
	private String regDate;
	private String regUserId;
	private String regId;
	private String updDate;
	private String updUserId;
	private String updId;
	private String delDate;
	private String delUserId;
	private String delId;
	private String delFlg;
}
