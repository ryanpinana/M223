package ch.samt.blogs;

import ch.samt.blogs.data.PostRepository;
import ch.samt.blogs.models.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    // TEST LISTA POST
    @Test
    public void testLoadPosts() throws Exception {
        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("postsList"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", postRepository.findAll()));
    }

    // TEST PAGINA NUOVO POST
    @Test
    public void testLoadNewPostPage() throws Exception {
        mockMvc.perform(get("/blog/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("post"));
    }

    // TEST INSERIMENTO CORRETTO
    @Test
    public void testSavePost_Success() throws Exception {
        mockMvc.perform(post("/blog/new")
                        .param("title", "Test Post")
                        .param("publisheddate", "2026-04-01")
                        .param("author", "Marco")
                        .param("category", "Tech")
                        .param("likes", "0")
                        .param("content", "Contenuto test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog"));

        // verifica DB
        Post post = postRepository.findAll()
                .stream()
                .filter(p -> p.getTitle().equals("Test Post"))
                .findFirst()
                .orElse(null);

        assert post != null;
        assert post.getAuthor().equals("Marco");
    }

    // TEST VALIDAZIONE
    @Test
    public void testSavePost_WithErrors() throws Exception {
        mockMvc.perform(post("/blog/new")
                        .param("title", "") // ERRORE
                        .param("author", "Marco"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeHasFieldErrors("post", "title"));

        // non deve essere salvato
        assert postRepository.findAll()
                .stream()
                .noneMatch(p -> p.getAuthor().equals("Marco") && p.getTitle().equals(""));
    }

    // TEST DETTAGLIO POST
    @Test
    public void testGetPostDetail() throws Exception {
        Post post = new Post();
        post.setTitle("Dettaglio");
        post.setAuthor("Luca");
        post.setCategory("News");
        post.setPublisheddate(LocalDate.now());
        post.setLikes(5);
        post.setContent("Contenuto");

        post = postRepository.save(post);

        mockMvc.perform(get("/blog/post")
                        .param("postid", post.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("postDetail"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", hasProperty("title", is("Dettaglio"))));
    }

    // TEST FILTRO AUTORE
    @Test
    public void testFilterByAuthor() throws Exception {
        mockMvc.perform(get("/blog/Mario"))
                .andExpect(status().isOk())
                .andExpect(view().name("postsList"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", postRepository.findByAuthor("Mario")));
    }

    // TEST BEST POSTS
    @Test
    public void testBestPosts() throws Exception {
        mockMvc.perform(get("/blog/best"))
                .andExpect(status().isOk())
                .andExpect(view().name("postsList"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", hasSize(lessThanOrEqualTo(2))));
    }

    // TEST LIKE
    @Test
    public void testAddLike() throws Exception {
        Post post = new Post();
        post.setTitle("Like Test");
        post.setAuthor("Anna");
        post.setCategory("Test");
        post.setPublisheddate(LocalDate.now());
        post.setLikes(0);
        post.setContent("Test");

        post = postRepository.save(post);

        mockMvc.perform(get("/blog/like")
                        .param("postid", post.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog"));

        Post updated = postRepository.findById(post.getId()).orElseThrow();
        assert updated.getLikes() == 1;
    }
}