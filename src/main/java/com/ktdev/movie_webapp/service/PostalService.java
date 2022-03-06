package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.City;
import com.ktdev.movie_webapp.domain.Postal;
import com.ktdev.movie_webapp.model.PostalDTO;
import com.ktdev.movie_webapp.repos.CityRepository;
import com.ktdev.movie_webapp.repos.PostalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PostalService {

    private final PostalRepository postalRepository;
    private final CityRepository cityRepository;

    public PostalService(final PostalRepository postalRepository,
            final CityRepository cityRepository) {
        this.postalRepository = postalRepository;
        this.cityRepository = cityRepository;
    }

    public List<PostalDTO> findAll() {
        return postalRepository.findAll()
                .stream()
                .map(postal -> mapToDTO(postal, new PostalDTO()))
                .collect(Collectors.toList());
    }

    public PostalDTO get(final Long id) {
        return postalRepository.findById(id)
                .map(postal -> mapToDTO(postal, new PostalDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PostalDTO postalDTO) {
        final Postal postal = new Postal();
        mapToEntity(postalDTO, postal);
        return postalRepository.save(postal).getId();
    }

    public void update(final Long id, final PostalDTO postalDTO) {
        final Postal postal = postalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(postalDTO, postal);
        postalRepository.save(postal);
    }

    public void delete(final Long id) {
        postalRepository.deleteById(id);
    }

    private PostalDTO mapToDTO(final Postal postal, final PostalDTO postalDTO) {
        postalDTO.setId(postal.getId());
        postalDTO.setCode(postal.getCode());
        postalDTO.setCity(postal.getCity() == null ? null : postal.getCity().getId());
        return postalDTO;
    }

    private Postal mapToEntity(final PostalDTO postalDTO, final Postal postal) {
        postal.setCode(postalDTO.getCode());
        if (postalDTO.getCity() != null && (postal.getCity() == null || !postal.getCity().getId().equals(postalDTO.getCity()))) {
            final City city = cityRepository.findById(postalDTO.getCity())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "city not found"));
            postal.setCity(city);
        }
        return postal;
    }

}
