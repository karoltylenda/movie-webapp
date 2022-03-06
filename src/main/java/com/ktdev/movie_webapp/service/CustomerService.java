package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Address;
import com.ktdev.movie_webapp.domain.Customer;
import com.ktdev.movie_webapp.model.CustomerDTO;
import com.ktdev.movie_webapp.repos.AddressRepository;
import com.ktdev.movie_webapp.repos.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(final CustomerRepository customerRepository,
            final AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .collect(Collectors.toList());
    }

    public CustomerDTO get(final Long id) {
        return customerRepository.findById(id)
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        mapToEntity(customerDTO, customer);
        return customerRepository.save(customer).getId();
    }

    public void update(final Long id, final CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(customerDTO, customer);
        customerRepository.save(customer);
    }

    public void delete(final Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapToDTO(final Customer customer, final CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNo(customer.getPhoneNo());
        customerDTO.setAddress(customer.getAddress() == null ? null : customer.getAddress().getId());
        return customerDTO;
    }

    private Customer mapToEntity(final CustomerDTO customerDTO, final Customer customer) {
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        if (customerDTO.getAddress() != null && (customer.getAddress() == null || !customer.getAddress().getId().equals(customerDTO.getAddress()))) {
            final Address address = addressRepository.findById(customerDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            customer.setAddress(address);
        }
        return customer;
    }

}
