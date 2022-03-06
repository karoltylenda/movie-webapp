package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.City;
import com.ktdev.movie_webapp.model.CityDTO;
import com.ktdev.movie_webapp.repos.CityRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDTO> findAll() {
        return cityRepository.findAll()
                .stream()
                .map(city -> mapToDTO(city, new CityDTO()))
                .collect(Collectors.toList());
    }

    public CityDTO get(final Long id) {
        return cityRepository.findById(id)
                .map(city -> mapToDTO(city, new CityDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CityDTO cityDTO) {
        final City city = new City();
        mapToEntity(cityDTO, city);
        return cityRepository.save(city).getId();
    }

    public void update(final Long id, final CityDTO cityDTO) {
        final City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(cityDTO, city);
        cityRepository.save(city);
    }

    public void delete(final Long id) {
        cityRepository.deleteById(id);
    }

    private CityDTO mapToDTO(final City city, final CityDTO cityDTO) {
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        return cityDTO;
    }

    private City mapToEntity(final CityDTO cityDTO, final City city) {
        city.setName(cityDTO.getName());
        return city;
    }

}
