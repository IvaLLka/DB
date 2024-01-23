package com.example.db.service.deliveryType;

import com.example.db.model.DeliveryType;

import java.util.List;

public interface DeliveryTypeService {
    public DeliveryType writeDeliveryType(DeliveryType deliveryType);
    void editDeliveryType(Long id, DeliveryType deliveryType);
    public String deleteDeliveryType(Long id);
    public List<DeliveryType> getAllDeliveryTypes();
    public DeliveryType getDeliveryTypeById(Long id);
}
