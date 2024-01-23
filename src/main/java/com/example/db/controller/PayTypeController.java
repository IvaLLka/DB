package com.example.db.controller;

import com.example.db.model.PayType;
import com.example.db.model.Sale;
import com.example.db.model.Seller;
import com.example.db.service.payType.PayTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pay_type")
public class PayTypeController {

    private final PayTypeService payTypeService;

    public PayTypeController(PayTypeService payTypeService) {
        this.payTypeService = payTypeService;
    }

    @GetMapping
    public String getAll(Model model){
        List<PayType> payTypes = payTypeService.getAllPayTypes();
        model.addAttribute("payTypes", payTypes);
        return "payType/pay_type_list";
    }

    @PostMapping("/delete/{id}")
    public String DeletePayType(@PathVariable Long id){
        payTypeService.deletePayType(id);
        return "redirect:/pay_type";
    }

    @GetMapping("/{id}")
    public String getPayTypeById(@PathVariable Long id,
                                Model model){
        PayType payType = payTypeService.getPayTypeById(id);
        model.addAttribute("payType", payType);
        return "/payType/edit_pay_type";
    }

    @PostMapping("/{id}")
    public String editPayTypeById(@PathVariable Long id,
                                 @ModelAttribute PayType payType) {

        PayType existingPayType = payTypeService.getPayTypeById(id);

        existingPayType.setName(payType.getName());


        for (Sale sale : existingPayType.getSales()) {
            sale.setPayType(existingPayType);
        }

        payTypeService.editPayType(id, existingPayType);

        return "redirect:/pay_type";
    }

    @GetMapping("/add")
    public String addPayType(){
        return "/payType/add_pay_type";
    }

    @PostMapping("/add")
    public String addPayType(@ModelAttribute PayType payType){
        payTypeService.writePayType(payType);
        return "redirect:/pay_type";
    }
}
