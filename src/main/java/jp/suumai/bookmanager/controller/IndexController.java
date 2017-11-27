package jp.suumai.bookmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.suumai.bookmanager.controller.viewmodel.BookInforViewModel;
import jp.suumai.bookmanager.dto.BookInforDto;
import jp.suumai.bookmanager.service.BookService;

/****************************************************************************************/
/****************************************************************************************/
/***** View --xxxForm--> Controller --xxxDto--> Service --Entity--> Repository(DAO) ******/
/*** View <--xxxViewModel-- Controller <--xxxDto-- Service <--Entity-- Repository(DAO) ***/
/****************************************************************************************/
/****************************************************************************************/
@Controller
public class IndexController {	
	@Autowired
	BookService bookService;
	
	/**
	 * Display a index screen
	 * @return index.html
	 */
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	/**
	 * Display a list books
	 * @return list books screen
	 */
	@GetMapping(value="/book")
	public ModelAndView showBooks() {					
		List<BookInforDto> booksDto = bookService.findAll();
		List<BookInforViewModel> booksViewModel = new ArrayList<>();
		
		for (BookInforDto bookDto : booksDto) {
			BookInforViewModel bookViewMdl = new BookInforViewModel();
			BeanUtils.copyProperties(bookDto, bookViewMdl);
			booksViewModel.add(bookViewMdl);
		}
		
		ModelAndView modelAndView = new ModelAndView("/book/books", "books", booksViewModel);
		return modelAndView;
	}
}
