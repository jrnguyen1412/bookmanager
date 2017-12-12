package jp.suumai.bookmanager.controller.viewmodel;

import lombok.Data;

@Data
public class BookInforViewModel {
	private int bookId;
	private String bookName;
	private String bookIsbn;
	private String bookImage;
}
