package br.com.jeronimo.springMongo.resources;

import br.com.jeronimo.springMongo.domain.User;
import br.com.jeronimo.springMongo.dto.UserDTO;
import br.com.jeronimo.springMongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UseResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> listAll() {
    List<UserDTO> list = service.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User obj = service.findById(id);
    return ResponseEntity.ok().body(new UserDTO(obj));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
    User obj = service.fromDTO(dto);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

}
