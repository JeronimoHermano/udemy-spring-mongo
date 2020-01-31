package br.com.jeronimo.springMongo.resources;

import br.com.jeronimo.springMongo.domain.Post;
import br.com.jeronimo.springMongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

  @Autowired
  private PostService service;

/*
  @GetMapping
  public ResponseEntity<List<UserDTO>> listAll() {
    List<Post> list = service.findAll().stream().map(::new).collect(Collectors.toList());
    return ResponseEntity.ok().body(list);
  }
*/

  @GetMapping(value = "/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

/*
  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
    User obj = service.fromDTO(dto);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO dto) {
    User obj = service.fromDTO(dto);
    obj.setId(id);
    obj = service.update(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.noContent().build();
  }
*/

}
