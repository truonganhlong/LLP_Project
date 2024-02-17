package com.llp.orderservice.services.impl;

import com.llp.orderservice.dtos.paymentMethod.PaymentMethodResponse;
import com.llp.orderservice.entities.PaymentMethod;
import com.llp.orderservice.mappers.PaymentMethodMapper;
import com.llp.orderservice.repositories.PaymentMethodRepository;
import com.llp.orderservice.services.PaymentMethodService;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;


    @Override
    public List<PaymentMethodResponse> getAll() {
        try {
            List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
            return paymentMethods.stream().map(PaymentMethodMapper::convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public PaymentMethodResponse getById(int id) {
        try {
            PaymentMethod paymentMethod = paymentMethodRepository.getById(id);
            if(Objects.isNull(paymentMethod)){
                throw new NotFoundException("Not found in database");
            }
            return PaymentMethodMapper.convertToResponse(paymentMethod);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}
