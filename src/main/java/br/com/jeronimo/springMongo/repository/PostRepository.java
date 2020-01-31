package br.com.jeronimo.springMongo.repository;

import br.com.jeronimo.springMongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  /*  // Consulta equivalente para usar com @Query
      {
        "title":{
          $regex: ?0,     // usa o primeiro parâmetro recebido no método
          $options: 'i'   // busca de forma insensitive
        }
      }
  */
  Optional<List<Post>> findByTitleContainingIgnoreCase(String text);


  @Query(
      "{ $and: [ {date: {$gte:?1} }, {date: {$lte:?2} }, {" +
          "$or : [{'title':{$regex: ?0, $options:'i'}}, {'body':{$regex: ?0, $options:'i'}}, {'comments.text':{$regex: ?0, $options:'i'}}]" +
          "} ] }"
  )
  Optional<List<Post>> fullSearch(String text, Date min, Date max);

}
