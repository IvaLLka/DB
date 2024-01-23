package com.example.db.service.payType;

import com.example.db.model.PayType;
import com.example.db.repository.PayTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayTypeServiceImpl implements PayTypeService{

    private final PayTypeRepository payTypeRepository;

    public PayTypeServiceImpl(PayTypeRepository payTypeRepository) {
        this.payTypeRepository = payTypeRepository;
    }

    @Override
    public PayType writePayType(PayType payType) {
        return payTypeRepository.save(payType);
    }

    @Override
    public void editPayType(Long id, PayType payType) {
    PayType newPayType = payTypeRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("PayType is not found"));
        newPayType.setName(payType.getName());
        newPayType.setSales(payType.getSales());

        payTypeRepository.save(newPayType);
    }

    @Override
    public String deletePayType(Long id) {
        payTypeRepository.deleteById(id);
        return null;
    }

    @Override
    public List<PayType> getAllPayTypes() {
        return payTypeRepository.findAll();
    }

    @Override
    public PayType getPayTypeById(Long id) {
        return payTypeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("PayType is not found"));
    }
}
