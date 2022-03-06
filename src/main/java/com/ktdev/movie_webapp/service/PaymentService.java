package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Customer;
import com.ktdev.movie_webapp.domain.Payment;
import com.ktdev.movie_webapp.domain.Rental;
import com.ktdev.movie_webapp.model.PaymentDTO;
import com.ktdev.movie_webapp.repos.CustomerRepository;
import com.ktdev.movie_webapp.repos.PaymentRepository;
import com.ktdev.movie_webapp.repos.RentalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;

    public PaymentService(final PaymentRepository paymentRepository,
            final RentalRepository rentalRepository, final CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
    }

    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> mapToDTO(payment, new PaymentDTO()))
                .collect(Collectors.toList());
    }

    public PaymentDTO get(final Long id) {
        return paymentRepository.findById(id)
                .map(payment -> mapToDTO(payment, new PaymentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PaymentDTO paymentDTO) {
        final Payment payment = new Payment();
        mapToEntity(paymentDTO, payment);
        return paymentRepository.save(payment).getId();
    }

    public void update(final Long id, final PaymentDTO paymentDTO) {
        final Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(paymentDTO, payment);
        paymentRepository.save(payment);
    }

    public void delete(final Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDTO mapToDTO(final Payment payment, final PaymentDTO paymentDTO) {
        paymentDTO.setId(payment.getId());
        paymentDTO.setDate(payment.getDate());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setRental(payment.getRental() == null ? null : payment.getRental().getId());
        paymentDTO.setCustomer(payment.getCustomer() == null ? null : payment.getCustomer().getId());
        return paymentDTO;
    }

    private Payment mapToEntity(final PaymentDTO paymentDTO, final Payment payment) {
        payment.setDate(paymentDTO.getDate());
        payment.setAmount(paymentDTO.getAmount());
        if (paymentDTO.getRental() != null && (payment.getRental() == null || !payment.getRental().getId().equals(paymentDTO.getRental()))) {
            final Rental rental = rentalRepository.findById(paymentDTO.getRental())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "rental not found"));
            payment.setRental(rental);
        }
        if (paymentDTO.getCustomer() != null && (payment.getCustomer() == null || !payment.getCustomer().getId().equals(paymentDTO.getCustomer()))) {
            final Customer customer = customerRepository.findById(paymentDTO.getCustomer())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
            payment.setCustomer(customer);
        }
        return payment;
    }

}
