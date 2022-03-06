package com.ktdev.movie_webapp.rest;

import com.ktdev.movie_webapp.model.PostalDTO;
import com.ktdev.movie_webapp.service.PostalService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/postals", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostalController {

    private final PostalService postalService;

    public PostalController(final PostalService postalService) {
        this.postalService = postalService;
    }

    @GetMapping
    public ResponseEntity<List<PostalDTO>> getAllPostals() {
        return ResponseEntity.ok(postalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostalDTO> getPostal(@PathVariable final Long id) {
        return ResponseEntity.ok(postalService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createPostal(@RequestBody @Valid final PostalDTO postalDTO) {
        return new ResponseEntity<>(postalService.create(postalDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePostal(@PathVariable final Long id,
            @RequestBody @Valid final PostalDTO postalDTO) {
        postalService.update(id, postalDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostal(@PathVariable final Long id) {
        postalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
