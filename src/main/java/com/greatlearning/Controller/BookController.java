package com.greatlearning.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.Entity.Book;
import com.greatlearning.Service.BookServiceImpl;


@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
	
		
		@GetMapping("/welcome")
		public String greet()
		{
			return "Welcome";
		}
		
		@RequestMapping("/list")
		public String listBooks(Model theModel) {
			

			// get Books from db
			List<Book> theBooks = bookservice.findAll();

			// add to the spring model
			theModel.addAttribute("Books", theBooks);
			

			return "list-Books";
		}

		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {

			// create model attribute to bind form data
			Book theBook = new Book();

			theModel.addAttribute("Book", theBook);

			return "Book-form";
		}

		
		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("bookId") int theId,
				Model theModel) {

			// get the Book from the service
			Book theBook = bookservice.findById(theId);


			// set Book as a model attribute to pre-populate the form
			theModel.addAttribute("Book", theBook);

			// send over to our form
			return "Book-form";			
		}


		@PostMapping("/save")
		public String saveBook(@RequestParam("id") int id,
				@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("author") String author) {

			System.out.println(id);
			Book theBook;
			if(id!=0)
			{
				theBook=bookservice.findById(id);
				theBook.setName(name);
				theBook.setCategory(category);
				theBook.setAuthor(author);
			}
			else
				theBook=new Book(name, category, author);
			// save the Book
			bookservice.save(theBook);


			// use a redirect to prevent duplicate submissions
			return "redirect:/books/list";

		}

		

		@RequestMapping("/delete")
		public String delete(@RequestParam("bookId") int theId) {

			// delete the Book
			bookservice.deleteById(theId);

			// redirect to /Books/list
			return "redirect:/books/list";

		}


		@RequestMapping("/search")
		public String search(@RequestParam("name") String name,
				@RequestParam("author") String author,
				Model theModel) {

			// check names, if both are empty then just give list of all Books

			if (name.trim().isEmpty() && author.trim().isEmpty()) {
				return "redirect:/books/list";
			}
			else {
				// else, search by first name and last name
				List<Book> theBooks =
						bookservice.searchBy(name, author);

				// add to the spring model
				theModel.addAttribute("Books", theBooks);

				// send to list-Books
				return "list-Books";
			}

		}
		
		@RequestMapping(value = "/403")
		public ModelAndView accesssDenied(Principal user) {

			ModelAndView model = new ModelAndView();

			if (user != null) {
				model.addObject("msg", "Hi " + user.getName() 
				+ ", you do not have permission to access this page!");
			} else {
				model.addObject("msg", 
				    "you do not have permission to access this page!");
			}

			model.setViewName("403");
			return model;

		}

}
