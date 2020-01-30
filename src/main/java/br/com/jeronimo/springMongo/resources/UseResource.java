package br.com.jeronimo.springMongo.resources;

import br.com.jeronimo.springMongo.dto.UserDTO;
import br.com.jeronimo.springMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UseResource {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> listAll() {
    List<UserDTO> list = userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok().body(list);
  }

}
