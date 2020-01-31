package br.com.jeronimo.springMongo.resources;

import br.com.jeronimo.springMongo.domain.Post;
import br.com.jeronimo.springMongo.services.PostService;
import br.com.jeronimo.springMongo.services.utils.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

  @GetMapping(value = "/titlesearch")
  public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
    text = URL.decodeParam(text);
    List<Post> list = service.findByTitle(text);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/fullsearch")
  public ResponseEntity<List<Post>> findByTitle(
      @RequestParam(value = "text", defaultValue = "") String text,
      @RequestParam(value = "minDate", defaultValue = "") String minDate,
      @RequestParam(value = "maxDate", defaultValue = "") String maxDate
  ) {
    text = URL.decodeParam(text);
    Date min = URL.convertDate(minDate, new Date(0L));
    Date max = URL.convertDate(maxDate, new Date());
    List<Post> list = service.fullSearch(text, min, max);
    return ResponseEntity.ok().body(list);
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
