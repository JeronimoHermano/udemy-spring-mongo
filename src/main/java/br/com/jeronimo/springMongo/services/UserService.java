package br.com.jeronimo.springMongo.services;

import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.dto.UserDTO;
import br.com.jeronimo.springMongo.repository.UserRepository;
import br.com.jeronimo.springMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = repository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não cadastrado"));
  }

  public User insert(User obj) {
    obj.setId(null);
    return repository.save(obj);
  }

  public User fromDTO(UserDTO dto) {
    return new User(
        dto.getId(),
        dto.getName(),
        dto.getEmail()
    );
  }

  public void delete(String id) {
    findById(id);
    repository.deleteById(id);
  }

  public User update(User obj) {
    User newObj = findById(obj.getId());
    updateData(newObj, obj);
    return repository.save(newObj);
  }

  private void updateData(User newObj, User obj) {
    newObj.setEmail(obj.getEmail());
    newObj.setName(obj.getName());
  }
}
