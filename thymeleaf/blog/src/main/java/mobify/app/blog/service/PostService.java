package mobify.app.blog.service;

import mobify.app.blog.entity.Post;
import mobify.app.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findOne(Long id) {
        return repository.findById(id).get();
    }

    public Post save(Post post) {
        return repository.saveAndFlush(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
