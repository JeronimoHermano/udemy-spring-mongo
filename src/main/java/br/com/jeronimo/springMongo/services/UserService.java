package br.com.jeronimo.springMongo.services;

import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

}
