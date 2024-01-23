package com.example.db.service.sale;

import com.example.db.model.Automobile;
import com.example.db.model.Sale;

import java.util.List;

public interface SaleService {

    public Sale writeSale(Sale sale);
    void editSale(Long id, Sale sale);
    public String deleteSale(Long id);
    public List<Sale> getAllSale();
    public Sale getSaleById(Long id);
}
