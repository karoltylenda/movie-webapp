package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Address;
import com.ktdev.movie_webapp.domain.Postal;
import com.ktdev.movie_webapp.model.AddressDTO;
import com.ktdev.movie_webapp.repos.AddressRepository;
import com.ktdev.movie_webapp.repos.PostalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final PostalRepository postalRepository;

    public AddressService(final AddressRepository addressRepository,
            final PostalRepository postalRepository) {
        this.addressRepository = addressRepository;
        this.postalRepository = postalRepository;
    }

    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapToDTO(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    public AddressDTO get(final Long id) {
        return addressRepository.findById(id)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }

    public void update(final Long id, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setStreet2(address.getStreet2());
        addressDTO.setInfo(address.getInfo());
        addressDTO.setPostal(address.getPostal() == null ? null : address.getPostal().getId());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setStreet(addressDTO.getStreet());
        address.setStreet2(addressDTO.getStreet2());
        address.setInfo(addressDTO.getInfo());
        if (addressDTO.getPostal() != null && (address.getPostal() == null || !address.getPostal().getId().equals(addressDTO.getPostal()))) {
            final Postal postal = postalRepository.findById(addressDTO.getPostal())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "postal not found"));
            address.setPostal(postal);
        }
        return address;
    }

}
