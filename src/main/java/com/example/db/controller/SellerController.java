package com.example.db.controller;

import com.example.db.model.Sale;
import com.example.db.model.Seller;
import com.example.db.service.seller.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @GetMapping
    public String getAll(Model model){
        List<Seller> sellers = sellerService.getAllSellers();
        model.addAttribute("sellers", sellers);
        return "/seller/seller_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteSeller(@PathVariable Long id){
        sellerService.deleteSeller(id);
        return "redirect:/seller";
    }

    @GetMapping("/{id}")
    public String getSellerById(@PathVariable Long id,
                                Model model){
        Seller seller = sellerService.getSellerById(id);
        model.addAttribute("seller", seller);
        return "/seller/edit_seller";
    }

    @PostMapping("/{id}")
    public String editSellerById(@PathVariable Long id,
                                 @ModelAttribute Seller seller) {

        Seller existingSeller = sellerService.getSellerById(id);

        existingSeller.setFullName(seller.getFullName());
        existingSeller.setEmail(seller.getEmail());
        existingSeller.setPhoneNumber(seller.getPhoneNumber());

        for (Sale sale : existingSeller.getSales()) {
            sale.setSeller(existingSeller);
        }

        sellerService.editSeller(id, existingSeller);

        return "redirect:/seller";
    }

    @GetMapping("/add")
    public String addSeller(){
        return "/seller/add_seller";
    }

    @PostMapping("/add")
    public String addSeller(@ModelAttribute Seller seller){
        sellerService.writeSeller(seller);
        return "redirect:/seller";
    }
}
