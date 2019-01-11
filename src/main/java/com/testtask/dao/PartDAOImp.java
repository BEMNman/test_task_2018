package com.testtask.dao;

import com.testtask.HandlingException;
import com.testtask.entity.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PartDAOImp implements PartDAO {

    private int countComputer = 0;
    private List<Part> partsByName;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Part> findByName(final String name) {
        partsByName = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part p where p.namePart like '%" + name.trim() + "%'");
        partsByName.addAll(query.getResultList());
        return partsByName;
    }

    @Override
    @Transactional
    public void addPart(Part part) throws HandlingException {
        partsByName = null;
        String namePart = part.getNamePart();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.namePart from Part p where p.namePart like '" + namePart + "'");
        if (!query.getResultList().contains(part.getNamePart())) {
            session.persist(part);
        } else {
            throw new HandlingException();

        }
    }

    @Override
    @Transactional
    public void deletePart(Integer partID) {
        partsByName = null;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(getPartByID(partID));
            listParts();
        } catch (IndexOutOfBoundsException e) {
            listParts();
        }
    }

    @Override
    @Transactional
    public void updatePart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    @Transactional
    public List<Part> listParts() {
        partsByName = null;
        List<Part> parts = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part");
        parts.addAll(query.getResultList());
        List<Integer> amountParts = new ArrayList<>();
        parts.forEach(p -> {
            if (p.getIsNeeded() == true) {
                amountParts.add(p.getAmount());
            }
        });
        countComputer = amountParts.size() > 0 ? Collections.min(amountParts) : 0;
        return parts;
    }

    @Override
    @Transactional
    public List<Part> findAllPartsIsNeeded(boolean isNeeded) {
        List<Part> parts = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from Part p where p.isNeeded like " + isNeeded + "");
        parts.addAll(query.getResultList());
        return parts;
    }

    @Override
    @Transactional
    public int countComputer() {
        return countComputer;
    }

    @Override
    @Transactional
    public List<Part> getPartsByName() {
        return partsByName;
    }

    @Override
    @Transactional
    public Part getPartByID(Integer idPart) {
        Session session = this.sessionFactory.getCurrentSession();
        Part partID = session.get(Part.class, idPart);
        return partID;
    }
}
