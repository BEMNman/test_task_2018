package com.testtask.dao;

import com.testtask.HandlingException;
import com.testtask.entity.Part;

import java.util.List;

public interface PartDAO {
    List<Part> findByName(String namePart);
    void addPart(Part part) throws HandlingException;
    void deletePart(Integer partID);
    void updatePart(Part part);
    List<Part> findAllPartsIsNeeded(boolean isNeeded);
    List<Part> listParts();
    int countComputer();
    List<Part> getPartsByName();
    Part getPartByID(Integer idPart);
}
