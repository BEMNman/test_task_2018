package com.testtask.service;

import com.testtask.HandlingException;
import com.testtask.entity.Part;
import java.util.List;

public interface PartDAOService {
    List<Part> findByName(String namePart);
    void addPart(Part part)  throws HandlingException;
    void deletePart(Integer partID);
    void updatePart(Part part);
    List<Part> listParts();
    int countComputer();
    Part getPartByID(Integer idPart);
}
