package jp.suumai.bookmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.suumai.bookmanager.entity.table.BookInfo;

public interface BookInfoRepository extends CrudRepository<BookInfo, Integer> {
	List<BookInfo> findByBookNameContaining(String query);
}
