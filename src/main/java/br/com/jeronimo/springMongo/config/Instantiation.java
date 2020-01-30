package br.com.jeronimo.springMongo.config;

import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) {
    User u1 = new User(null, "Maria Brown", "maria@gmail.com");
    User u2 = new User(null, "Alex Gray", "alex@gmail.com");
    User u3 = new User(null, "Bob Blue", "bob@gmail.com");

    userRepository.deleteAll();
    userRepository.saveAll(Arrays.asList(u1, u2, u3));
  }

}
