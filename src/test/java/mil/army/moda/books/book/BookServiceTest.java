package mil.army.moda.books.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void shouldSaveNewBook() {
        // Arrange
        Book book = new Book("Hobbit", "Tokin", true, 800, "123-L3045-34", 15);
        Book savedBook = new Book("Hobbit", "Tokin", true, 800, "123-L3045-34", 15);
        savedBook.setId(1L);
        // Act
        when(bookRepository.save(book)).thenReturn(savedBook);
        Book result = bookService.saveBook(book);
        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        verify(bookRepository).save(book);
    }

    @Test
    void shouldFindBookById() {
        // Arrange
        Book book = new Book("Lord of the Rings", "Tokin", true, 800, "123-L3045-55", 24);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        // Act
        Book result = bookService.findBookById(1L);
        // Assert
        assertThat(result.getTitle()).isEqualTo("Lord of the Rings");
        // more json path properties
        verify(bookRepository, only()).findById(result.getId());
    }

    @Test
    void shouldUpdateBook() {
        // Arrange
        Book existing = new Book("Lord of the Rings", "Tolkin", true, 800, "123-L3045-55", 24);
        existing.setId(1L);
        Book updated = new Book("Lord of the Rings", "JRR Tolkin", false, 1000, "123-L3045-55", 24);
        updated.setId(1L);
        // Act
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        Book updatedBook = bookService.updateBook(updated);
        // Assert
        assertThat(updatedBook.getAuthor()).isEqualTo("JRR Tolkin");

        verify(bookRepository, only()).findById(updatedBook.getId());
    }
    @Test
    void shouldUpdateTodoStatusSuccessfully() {
        Long testId = 1L;
        Book existingBook = new Book("Lord of the Rings", "Tolkin", true, 800, "123-L3045-55", 24);
        existingBook.setId(testId);


        Book updatedAuthorAndIsCheckedOut = new Book("Lord of the Rings", "JRR Tolkin", false, 1000, "123-L3045-55", 24);
        updatedAuthorAndIsCheckedOut.setId(testId);
        Book updatedIsCheckedOut = new Book("Lord of the Rings", "JRR Tolkin", false, 1000, "123-L3045-55", 24);
        updatedIsCheckedOut.setId(testId);

        when(bookRepository.findById(testId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedAuthorAndIsCheckedOut);

        Book updatedRequest = bookService.updateBook(updatedIsCheckedOut);

        assertNotNull(updatedRequest);
        assertEquals(testId, updatedRequest.getId());
        assertEquals("JRR Tolkin", updatedRequest.getAuthor());
        assertEquals(updatedAuthorAndIsCheckedOut.getIsCheckedOut(), updatedRequest.getIsCheckedOut());

        verify(bookRepository).findById(testId);
        verify(bookRepository).save(existingBook);
    }

}