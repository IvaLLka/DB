package com.example.db.service.deliveryType;

import com.example.db.model.DeliveryType;
import com.example.db.model.PayType;
import com.example.db.repository.DeliveryTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService{

    private final DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeServiceImpl(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public DeliveryType writeDeliveryType(DeliveryType deliveryType) {
        return deliveryTypeRepository.save(deliveryType);
    }

    @Override
    public void editDeliveryType(Long id, DeliveryType deliveryType) {
        DeliveryType newDeliveryType = deliveryTypeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("DeliveryType is not found"));
        newDeliveryType.setName(deliveryType.getName());
        newDeliveryType.setSales(deliveryType.getSales());

        deliveryTypeRepository.save(newDeliveryType);
    }

    @Override
    public String deleteDeliveryType(Long id) {
        deliveryTypeRepository.deleteById(id);
        return null;
    }

    @Override
    public List<DeliveryType> getAllDeliveryTypes() {
        return deliveryTypeRepository.findAll();
    }

    @Override
    public DeliveryType getDeliveryTypeById(Long id) {
        return deliveryTypeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("DeliveryType is not found"));
    }
}
