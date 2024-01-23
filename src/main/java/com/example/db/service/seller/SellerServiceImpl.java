package com.example.db.service.seller;

import com.example.db.model.Seller;
import com.example.db.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @Override
    public List<Object[]> findTopSellingSeller() {
        return sellerRepository.findTopSellingSeller();

    }

    @Override
    public Seller writeSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void editSeller(Long id, Seller seller) {
        Seller newSeller = sellerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Seller is not found"));
        newSeller.setEmail(seller.getEmail());
        newSeller.setFullName(seller.getFullName());
        newSeller.setPhoneNumber(seller.getPhoneNumber());
        newSeller.setSales(seller.getSales());
        sellerRepository.save(newSeller);
    }

    @Override
    public String deleteSeller(Long id) {
        sellerRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Seller is not found"));
    }
}
