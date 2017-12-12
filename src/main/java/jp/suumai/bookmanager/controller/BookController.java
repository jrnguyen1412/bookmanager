package jp.suumai.bookmanager.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.suumai.bookmanager.controller.form.BookForm;
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
public class BookController {
	@Autowired
	private BookService bookService;
	
	//Save the uploaded file to this folder
    private final Path rootFolder = Paths.get("src/main/resources/static/img");
    
	/**
	 * Add a new book
	 * @return
	 */
	@GetMapping(value = "/book/addBook")
	public ModelAndView showAddBookForm() {
		return new ModelAndView("/book/bookForm", "book", new BookInforViewModel());
	}
	
	/**
	 * Insert or Update a book
	 * @param book
	 * @return
	 */
	@PostMapping(value = "/book/save")
	public ModelAndView saveBook(@ModelAttribute("book") BookForm book) {
		MultipartFile file = book.getImageFile();
		
		if (file == null || file.isEmpty()) {
			return new ModelAndView("/book/bookForm", "errMsg", "Please select a file to upload!");
		}
		
		try {

        	DateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_");
        	Calendar cal = Calendar.getInstance();
        	
        	Files.copy(file.getInputStream(), this.rootFolder.resolve(sdf.format(cal.getTime()) + file.getOriginalFilename()));
        	book.setBookImage(sdf.format(cal.getTime()) + file.getOriginalFilename());

    		BookInforDto bookInfoDto = new BookInforDto();
    		BeanUtils.copyProperties(book, bookInfoDto);
    		
    		bookService.save(bookInfoDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/book");
	}
	
	/**
	 * Show a update book screen
	 * @param bookId
	 * @return
	 */
	@GetMapping(value = "/book/{bookId}/editBook")
	public ModelAndView showEditBookForm(@PathVariable int bookId) {
		BookInforDto bookDto = bookService.findOne(bookId);
		BookInforViewModel bookViewMdl = new BookInforViewModel();
		
		BeanUtils.copyProperties(bookDto, bookViewMdl);
		return new ModelAndView("/book/bookForm", "book", bookViewMdl);
	}
	
	/**
	 * Delete a book
	 * @param bookId
	 * @return
	 */
	@GetMapping(value = "/book/{bookId}/deleteBook")
	public ModelAndView deleteBook(@PathVariable int bookId) {
		bookService.delete(bookId);
		
		return new ModelAndView("redirect:/book");
	}
	
	/**
	 * Search a book with a query
	 * @param query
	 * @return
	 */
	@PostMapping(value = "/book/search")
	public ModelAndView searchBook(@RequestParam("query") String query) {
		if (StringUtils.isEmpty(query)) {
			return new ModelAndView("redirect:/book");
		}
		
		List<BookInforDto> booksDto = bookService.search(query);
		List<BookInforViewModel> booksViewModel = new ArrayList<>();
		
		for (BookInforDto bookDto : booksDto) {
			BookInforViewModel bookViewMdl = new BookInforViewModel();
			BeanUtils.copyProperties(bookDto, bookViewMdl);
			booksViewModel.add(bookViewMdl);
		}
		
		return new ModelAndView("/book/books", "books", booksViewModel);
	}
}
