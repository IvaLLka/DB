package com.example.db.controller;

import com.example.db.model.Client;
import com.example.db.model.Sale;
import com.example.db.model.Seller;
import com.example.db.service.client.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getAll(Model model){
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "/client/client_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return "redirect:/client";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable Long id,
                                Model model){
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "/client/edit_client";
    }

    @PostMapping("/{id}")
    public String editClientById(@PathVariable Long id, @ModelAttribute Client client) {
        Client existingClient = clientService.getClientById(id);

        existingClient.setFullName(client.getFullName());
        existingClient.setDob(client.getDob());
        existingClient.setAdress(client.getAdress());
        existingClient.setRegistrationDate(client.getRegistrationDate());

        for (Sale sale : client.getSales()) {
            sale.setClient(existingClient);
        }

        clientService.editClient(id, existingClient);

        return "redirect:/client";
    }


    @GetMapping("/add")
    public String addClient(){
        return "/client/add_client";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client){
        clientService.writeClient(client);
        return "redirect:/client";
    }

    @GetMapping("/sql/{id}")
    public String getClientCard(@PathVariable Long id,
                                Model model){
        Client client = clientService.getClientByID(id);
        model.addAttribute("client", client);
        return "/client/get_sql_client";
    }

}
