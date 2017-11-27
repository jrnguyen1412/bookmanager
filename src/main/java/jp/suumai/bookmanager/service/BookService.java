package jp.suumai.bookmanager.service;

import java.util.List;

import jp.suumai.bookmanager.dto.BookInforDto;

/****************************************************************************************/
/****************************************************************************************/
/***** View --xxxForm--> Controller --xxxDto--> Service --Entity--> Repository(DAO) ******/
/*** View <--xxxViewModel-- Controller <--xxxDto-- Service <--Entity-- Repository(DAO) ***/
/****************************************************************************************/
/****************************************************************************************/
public interface BookService {
	List<BookInforDto> findAll();
	
	BookInforDto findOne(int bookId);
	
	void save(BookInforDto bookDto);
	
	void delete(int bookId);
	
	List<BookInforDto> search(String query); 
}
