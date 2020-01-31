package br.com.jeronimo.springMongo.services;

import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.repository.UserRepository;
import br.com.jeronimo.springMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não cadastrado"));
  }

}
