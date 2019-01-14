package com.testtask.service;

import com.testtask.HandlingException;
import com.testtask.dao.PartDAO;
import com.testtask.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartDAOServiceImp implements PartDAOService {

    private PartDAO partDAO;

    @Autowired
    public PartDAOServiceImp(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    public List<Part> findByName(String namePart) {
        return partDAO.findByName(namePart);
    }

    @Override
    public void addPart(Part part)  throws HandlingException {
        partDAO.addPart(part);
    }

    @Override
    public void deletePart(Integer partID) {
        partDAO.deletePart(partID);
    }

    @Override
    public void updatePart(Part part) {
        partDAO.updatePart(part);
    }

    @Override
    public List<Part> listParts(){
        return partDAO.listParts();
    }

    @Override
    public int countComputer() {
        return partDAO.countComputer();
    }

    @Override
    public Part getPartByID(Integer idPart) {
        return partDAO.getPartByID(idPart);
    }
}
