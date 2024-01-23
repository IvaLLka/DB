package com.example.db.service.payType;

import com.example.db.model.PayType;

import java.util.List;

public interface PayTypeService {
    public PayType writePayType(PayType payType);
    void editPayType(Long id, PayType payType);
    public String deletePayType(Long id);
    public List<PayType> getAllPayTypes();
    public PayType getPayTypeById(Long id);
}
