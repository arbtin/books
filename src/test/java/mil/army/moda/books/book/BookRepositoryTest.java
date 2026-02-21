package mil.army.moda.books.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveABook() {
        // Arrange
        Book book2 = new Book("Clean Code", "Bob", true, 200, "123-L3045", 10);
        Book book = new Book("Clean Code", "Bob", true, 200, "123-L3045", 10);
        // Act
        Book savedBook = bookRepository.save(book);
        Book savedBook2 = bookRepository.save(book2);
        Optional<Book> found = bookRepository.findById(savedBook.getId());
        // Assert
        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(savedBook);
    }

}