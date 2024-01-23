package com.example.db.service.autoModel;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileModel;

import java.util.List;

public interface AutomobileModelService {

    List<Object[]> findMarksAndEquipsWithSalesThanTwo();
    List<AutomobileModel> getAutomobileModelsWithoutAutomobiles();
    public AutomobileModel writeAutoModel(AutomobileModel automobileModel);
    void editAutoModel(Long id, AutomobileModel automobileModel);
    public String deleteAutoModel(Long id);
    public List<AutomobileModel> getAllAutoModels();

    public AutomobileModel getAutoModelById(Long id);
}
