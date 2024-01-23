package com.example.db.service.autoModel;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileModel;
import com.example.db.repository.AutomobileModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileModelServiceImpl implements AutomobileModelService {

    @Override
    public List<Object[]> findMarksAndEquipsWithSalesThanTwo() {
        return automobileModelRepository.findMarksAndEquipsWithSalesThanTwo();
    }

    @Autowired
    private final AutomobileModelRepository automobileModelRepository;

    public AutomobileModelServiceImpl(AutomobileModelRepository automobileModelRepository) {
        this.automobileModelRepository = automobileModelRepository;
    }

    @Override
    public List<AutomobileModel> getAutomobileModelsWithoutAutomobiles() {
        return automobileModelRepository.findAutomobileModelsWithoutAutomobiles();
    }


    @Override
    public AutomobileModel writeAutoModel(AutomobileModel automobileModel) {
        return automobileModelRepository.save(automobileModel);
    }

    @Override
    public void editAutoModel(Long id, AutomobileModel automobileModel) {
        AutomobileModel newAutomobileModel = automobileModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AutoModel not found with ID:" + id));
        newAutomobileModel.setName(automobileModel.getName());
        newAutomobileModel.setAutomobileMark(automobileModel.getAutomobileMark());
        newAutomobileModel.setAutomobiles(automobileModel.getAutomobiles());
        automobileModelRepository.save(newAutomobileModel);
    }

    @Override
    public String deleteAutoModel(Long id) {
        automobileModelRepository.deleteById(id);
        return null;
    }

    @Override
    public List<AutomobileModel> getAllAutoModels() {
        return automobileModelRepository.findAll();
    }

    @Override
    public AutomobileModel getAutoModelById(Long id) {
        return automobileModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AutomobileModel not found with ID:" + id));
    }
}
