package com.example.db.controller;

import com.example.db.model.*;
import com.example.db.service.auto.AutomobileService;
import com.example.db.service.client.ClientService;
import com.example.db.service.deliveryType.DeliveryTypeService;
import com.example.db.service.payType.PayTypeService;
import com.example.db.service.sale.SaleService;
import com.example.db.service.seller.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    private final SellerService sellerService;

    private final ClientService clientService;

    private final AutomobileService automobileService;

    private final PayTypeService payTypeService;

    private  final DeliveryTypeService deliveryTypeService;

    public SaleController(SaleService saleService, SellerService sellerService, ClientService clientService, AutomobileService automobileService, PayTypeService payTypeService, DeliveryTypeService deliveryTypeService) {
        this.saleService = saleService;
        this.sellerService = sellerService;
        this.clientService = clientService;
        this.automobileService = automobileService;
        this.payTypeService = payTypeService;
        this.deliveryTypeService = deliveryTypeService;
    }

    @GetMapping
    public String getAll(Model model){
        List<Sale> sales = saleService.getAllSale();
        model.addAttribute("sales", sales);
        return "/sale/sale_list";
    }
    @PostMapping("/delete/{id}")
    public String DeleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return "redirect:/sale";
    }

    @GetMapping("/{id}")
    public String getSaleById(@PathVariable Long id, Model model){
        Sale sale = saleService.getSaleById(id);

        List<Seller> sellers = sellerService.getAllSellers();
        List<Client> clients = clientService.getAllClients();
        List<PayType> payTypes = payTypeService.getAllPayTypes();
        List<DeliveryType> deliveryTypes = deliveryTypeService.getAllDeliveryTypes();

        // Получите все автомобили без продаж
        List<Automobile> automobilesWithoutSales = automobileService.getAutomobilesWithoutSales();

        // Создайте новый список, объединив автомобили без продаж и текущий автомобиль
        List<Automobile> allAutomobiles = new ArrayList<>(automobilesWithoutSales);
        allAutomobiles.add(sale.getAutomobile());

        model.addAttribute("sellers", sellers);
        model.addAttribute("clients", clients);
        model.addAttribute("payTypes", payTypes);
        model.addAttribute("deliveryTypes", deliveryTypes);
        model.addAttribute("allAutomobiles", allAutomobiles);
        model.addAttribute("sale", sale);

        return "/sale/edit_sale";
    }
    @PostMapping("/{id}")
    public String editSaleById(@PathVariable Long id,
                               @ModelAttribute Sale sale){
        saleService.editSale(id, sale);
        return "redirect:/sale";
    }

    @GetMapping("/add")
    public String addSale(Model model){
        List<Seller> sellers = sellerService.getAllSellers();
        List<Client> clients = clientService.getAllClients();
        List<PayType> payTypes = payTypeService.getAllPayTypes();
        List<DeliveryType>deliveryTypes = deliveryTypeService.getAllDeliveryTypes();
        List<Automobile> automobiles = automobileService.getAutomobilesWithoutSales();

        model.addAttribute("sellers", sellers);
        model.addAttribute("clients", clients);
        model.addAttribute("payTypes", payTypes);
        model.addAttribute("deliveryTypes", deliveryTypes);
        model.addAttribute("automobiles", automobiles);
        return "/sale/add_sale";
    }

    @PostMapping("/add")
    public String addSale(@ModelAttribute Sale sale,
                          Model model){
        if (sale.getDate() == null) {
            model.addAttribute("error", "Дата не может быть пустой");
            return "/sale/add_sale";
        }
        if (sale.getDeliveryType() == null) {
            model.addAttribute("error", "Тип доставки не может быть пустым");
            return "/sale/add_sale";
        }
        saleService.writeSale(sale);
        return "redirect:/sale";
    }

    @GetMapping("/sql")
    public String getTopSellingSeller(Model model) {
        List<Object[]> sellers = sellerService.findTopSellingSeller();
        model.addAttribute("sellers", sellers);
        return "sale/get_sql_sale";
    }
}
