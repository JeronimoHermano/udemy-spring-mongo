package br.com.jeronimo.springMongo.config;

import br.com.jeronimo.springMongo.domain.Post;
import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.dto.AuthorDTO;
import br.com.jeronimo.springMongo.dto.CommentDTO;
import br.com.jeronimo.springMongo.repository.PostRepository;
import br.com.jeronimo.springMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postReposiroty;

  @Override
  public void run(String... args) throws ParseException {
    userRepository.deleteAll();
    postReposiroty.deleteAll();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

    User u1 = new User(null, "Maria Brown", "maria@gmail.com");
    User u2 = new User(null, "Alex Gray", "alex@gmail.com");
    User u3 = new User(null, "Bob Blue", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(u1, u2, u3));

    Post post1 = new Post(
        null,
        sdf.parse("21/03/2018"),
        "Partiu viagem",
        "Vou viajar para São Paulo. Abraços!",
        new AuthorDTO(u1)
    );
    Post post2 = new Post(
        null,
        sdf.parse("23/03/2018"),
        "Bom dia",
        "Acordei feliz hoje!",
        new AuthorDTO(u1)
    );


    CommentDTO c1 = new CommentDTO(
        "Boa viagem mano!",
        sdf.parse("21/03/2018"),
        new AuthorDTO(u3)
    );
    CommentDTO c2 = new CommentDTO(
        "Aproveite!",
        sdf.parse("22/03/2018"),
        new AuthorDTO(u1)
    );
    CommentDTO c3 = new CommentDTO(
        "Tenha um ótimo dia!",
        sdf.parse("23/03/2018"),
        new AuthorDTO(u3)
    );

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().add(c3);

    postReposiroty.saveAll(Arrays.asList(post1, post2));

    u1.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(u1);
  }

}
