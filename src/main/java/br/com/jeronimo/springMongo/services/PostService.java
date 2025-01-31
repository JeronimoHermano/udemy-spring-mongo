package br.com.jeronimo.springMongo.services;

import br.com.jeronimo.springMongo.domain.Post;
import br.com.jeronimo.springMongo.repository.PostRepository;
import br.com.jeronimo.springMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository repository;

  public List<Post> findAll() {
    return repository.findAll();
  }

  public Post findById(String id) {
    Optional<Post> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Postagem inexistente"));
  }

  public List<Post> findByTitle(String text) {
    Optional<List<Post>> obj = repository.findByTitleContainingIgnoreCase(text);
    return obj.orElse(null);
  }

  public List<Post> fullSearch(String text, Date min, Date max) {
    max.setTime(max.getTime() + 24 * 60 * 60 * 1000);
    Optional<List<Post>> obj = repository.fullSearch(text, min, max);
    return obj.orElse(null);
  }

  /*
  public Post insert(Post obj) {
    obj.setId(null);
    return repository.save(obj);
  }

  public Post fromDTO(PostDTO dto) {
    return new Post(
    );
  }

  public void delete(String id) {
    findById(id);
    repository.deleteById(id);
  }

  public Post update(Post obj) {
    Post newObj = findById(obj.getId());
    updateData(newObj, obj);
    return repository.save(newObj);
  }

  private void updateData(Post newObj, Post obj) {
    newObj.setAuthor(obj.getAuthor());
    newObj.setDate(obj.getDate());
    newObj.setTitle(obj.getTitle());
    newObj.setBody(obj.getBody());
  }
  */
}
