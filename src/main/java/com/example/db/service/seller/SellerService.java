package com.example.db.service.seller;

import com.example.db.model.Client;
import com.example.db.model.Equipment;
import com.example.db.model.Seller;
import org.hibernate.mapping.Selectable;

import java.util.List;

public interface SellerService {
    public List<Object[]> findTopSellingSeller();
    public Seller writeSeller(Seller seller);
    void editSeller(Long id, Seller seller);
    public String deleteSeller(Long id);
    public List<Seller> getAllSellers();

    public Seller getSellerById(Long id);
}
