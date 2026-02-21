package mil.army.moda.books.book;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(updatedBook.getId());
        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("Book with id " + updatedBook.getId() + " not found.");
        }

        Book book = optionalBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsCheckedOut(updatedBook.getIsCheckedOut());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

}
