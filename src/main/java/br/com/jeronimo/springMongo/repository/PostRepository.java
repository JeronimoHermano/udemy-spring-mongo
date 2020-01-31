package br.com.jeronimo.springMongo.repository;

import br.com.jeronimo.springMongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

  Optional<List<Post>> findByTitleContainingIgnoreCase(String text);

}
