package com.example.db.service.auto;

import com.example.db.model.Automobile;
import com.example.db.repository.AutomobileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileServiceImpl implements AutomobileService {
    private final AutomobileRepository automobileRepository;

    @Autowired
    public AutomobileServiceImpl(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }


    @Override
    public List<Automobile> getAutomobilesWithoutSales() {
        return automobileRepository.findAutomobilesWithoutSales();
    }



    @Override
    public Automobile writeAuto(Automobile automobile) {
        return automobileRepository.save(automobile);
    }

    @Override
    public void editAuto(Long id, Automobile automobile) {
        Automobile newAutomobile = automobileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auto not found with ID:" + id));
        newAutomobile.setWin(automobile.getWin());
        newAutomobile.setPower(automobile.getPower());
        newAutomobile.setEngineCapacity(automobile.getEngineCapacity());
        newAutomobile.setIssueYear(automobile.getIssueYear());
        newAutomobile.setAutomobileModel(automobile.getAutomobileModel());

        newAutomobile.setEquipment(automobile.getEquipment());
        automobileRepository.save(newAutomobile);
    }

    @Override
    public String deleteAuto(Long id) {
        automobileRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Automobile> getAllAuto() {
        return automobileRepository.findAll();
    }

    @Override
    public Automobile getAutoById(Long id) {
        return automobileRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Auto not found with ID:" + id));
    }



}
