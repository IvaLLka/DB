package com.example.db.controller;

import com.example.db.model.DeliveryType;
import com.example.db.model.PayType;
import com.example.db.model.Sale;
import com.example.db.service.deliveryType.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/delivery_type")
public class DeliveryTypeController {

    private final DeliveryTypeService deliveryTypeService;

    public DeliveryTypeController(DeliveryTypeService deliveryTypeService){
        this.deliveryTypeService = deliveryTypeService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<com.example.db.model.DeliveryType> deliveryTypes = deliveryTypeService.getAllDeliveryTypes();
        model.addAttribute("deliveryTypes", deliveryTypes);
        return "/deliveryType/delivery_type_list";
    }
    @PostMapping("/delete/{id}")
    public String DeleteDeliveryType(@PathVariable Long id){
        deliveryTypeService.deleteDeliveryType(id);
        return "redirect:/delivery_type";
    }

    @GetMapping("/{id}")
    public String getPayTypeById(@PathVariable Long id,
                                 Model model){
        DeliveryType deliveryType = deliveryTypeService.getDeliveryTypeById(id);
        model.addAttribute("deliveryType", deliveryType);
        return "/deliveryType/edit_delivery_type";
    }

    @PostMapping("/{id}")
    public String editDeliveryTypeById(@PathVariable Long id,
                                  @ModelAttribute DeliveryType payType) {

        DeliveryType existingDeliveryType = deliveryTypeService.getDeliveryTypeById(id);

        existingDeliveryType.setName(payType.getName());


        for (Sale sale : existingDeliveryType.getSales()) {
            sale.setDeliveryType(existingDeliveryType);
        }

        deliveryTypeService.editDeliveryType(id, existingDeliveryType);

        return "redirect:/delivery_type";
    }

    @GetMapping("/add")
    public String addDeliveryType(){
        return "/deliveryType/add_delivery_type";
    }

    @PostMapping("/add")
    public String addDeliveryType(@ModelAttribute DeliveryType deliveryType){
        deliveryTypeService.writeDeliveryType(deliveryType);
        return "redirect:/delivery_type";
    }

}
