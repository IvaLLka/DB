package com.example.db.service.auto;

import com.example.db.model.Automobile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutomobileService {



    List<Automobile> getAutomobilesWithoutSales();
    public Automobile writeAuto(Automobile automobile);
    void editAuto(Long id, Automobile automobile);
    public String deleteAuto(Long id);
    public List<Automobile> getAllAuto();

    public Automobile getAutoById(Long id);

}
