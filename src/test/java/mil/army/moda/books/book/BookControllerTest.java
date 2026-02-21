package mil.army.moda.books.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BookService bookService;

    @Test
    void shouldSaveNewBook() throws Exception {
        // Arrange
        Book book = new Book("Hobbit", "Tokin", true, 800, "123-L3045-34", 15);
        book.setId(1L);
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/book/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andDo(print());
                //.andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldFindBookById() throws Exception {
        // Arrange
        Book book = new Book("Hobbit", "Tolkin", true, 800, "123-L3045-34", 15);
        book.setId(1L);
        when(bookService.findBookById(book.getId())).thenReturn(book);

        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1));

    }

}