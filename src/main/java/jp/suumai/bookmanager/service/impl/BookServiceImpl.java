package jp.suumai.bookmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import jp.suumai.bookmanager.dto.BookInforDto;
import jp.suumai.bookmanager.entity.table.BookInfo;
import jp.suumai.bookmanager.repository.BookInfoRepository;
import jp.suumai.bookmanager.service.BookService;

/****************************************************************************************/
/****************************************************************************************/
/***** View --xxxForm--> Controller --xxxDto--> Service --Entity--> Repository(DAO) ******/
/*** View <--xxxViewModel-- Controller <--xxxDto-- Service <--Entity-- Repository(DAO) ***/
/****************************************************************************************/
/****************************************************************************************/
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookInfoRepository repository;

	@Override
	public List<BookInforDto> findAll() {
		List<BookInfo> books = Lists.newArrayList(repository.findAll());
		List<BookInforDto> booksDto = new ArrayList<>();
		
		for (BookInfo book : books) {
			BookInforDto bookDto = new BookInforDto();
			BeanUtils.copyProperties(book, bookDto);
			
			booksDto.add(bookDto);
		}
		return booksDto;
	}

	@Override
	public BookInforDto findOne(int bookId) {
		BookInfo book = repository.findOne(bookId);
		BookInforDto bookDto = new BookInforDto();
		
		BeanUtils.copyProperties(book, bookDto);
		return bookDto;
	}

	@Override
	public void save(BookInforDto bookDto) {
		BookInfo book = new BookInfo();
		BeanUtils.copyProperties(bookDto, book);
		
		repository.save(book);
	}

	@Override
	public void delete(int bookId) {
		repository.delete(bookId);
	}

	@Override
	public List<BookInforDto> search(String query) {
		List<BookInfo> books = repository.findByBookNameContaining(query);
		List<BookInforDto> booksDto = new ArrayList<>();
		
		for (BookInfo book : books) {
			BookInforDto bookDto = new BookInforDto();
			BeanUtils.copyProperties(book, bookDto);
			
			booksDto.add(bookDto);
		}
		return booksDto;
	}
}
