package com.example.db.service.sale;

import com.example.db.model.Sale;
import com.example.db.model.Seller;
import com.example.db.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale writeSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void editSale(Long id, Sale sale) {
        Sale newSale = saleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sale is not found"));
        newSale.setSeller(sale.getSeller());
        newSale.setPayType(sale.getPayType());
        newSale.setDate(sale.getDate());
        newSale.setDeliveryType(sale.getDeliveryType());
        newSale.setSum(sale.getSum());
        newSale.setAutomobile(sale.getAutomobile());
        newSale.setClient(sale.getClient());

        saleRepository.save(newSale);
    }

    @Override
    public String deleteSale(Long id) {
        saleRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sale is not found"));
    }
}
