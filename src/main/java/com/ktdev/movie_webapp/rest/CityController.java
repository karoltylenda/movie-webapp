package com.ktdev.movie_webapp.rest;

import com.ktdev.movie_webapp.model.CityDTO;
import com.ktdev.movie_webapp.service.CityService;
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
@RequestMapping(value = "/api/citys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

    private final CityService cityService;

    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCitys() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable final Long id) {
        return ResponseEntity.ok(cityService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCity(@RequestBody @Valid final CityDTO cityDTO) {
        return new ResponseEntity<>(cityService.create(cityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable final Long id,
            @RequestBody @Valid final CityDTO cityDTO) {
        cityService.update(id, cityDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable final Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
