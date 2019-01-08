package com.testtask.controller;

import com.testtask.HandlingException;
import com.testtask.entity.Part;
import com.testtask.service.PartDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private PartDAOService partDAOService;
    private ModelAndView modelAndView;
    private String selectListPartsForView;
    private boolean inSearch;
    private String search;

    public Controller(PartDAOService partDAOService) {
        this.partDAOService = partDAOService;
        this.modelAndView = new ModelAndView();
        this.selectListPartsForView = "All";
        this.inSearch = false;
        this.search = null;
    }

    @RequestMapping(value = {"/", "/listParts"}, method = RequestMethod.GET)
    public ModelAndView start() {
        selectListPartsForView = "All";
        inSearch = false;
        search = null;
        modelAndView = listParts(null);
        return modelAndView;
    }

    public ModelAndView listParts(List<Part> partList) {
        if (partList == null) {
            if (!inSearch) {
                modelAndView.addObject("parts", partDAOService.listParts());
            } else {
                modelAndView.addObject("parts", partDAOService.getPartsByName());
            }

        } else {
            modelAndView.addObject("parts", partList);
        }
        modelAndView.addObject("countComputer", partDAOService.countComputer());
        modelAndView.addObject("selectListPartsForView", selectListPartsForView);
        modelAndView.addObject("search", search);
        modelAndView.setViewName("listParts");

        System.out.println(modelAndView.getViewName());

        return modelAndView;
    }

    @RequestMapping(value = "/searchPart", method = RequestMethod.GET)
    public ModelAndView searchPart(@RequestParam("search") String namePart,
                                   @RequestParam(value = "inSearch", defaultValue = "false") boolean inSearch,
                                   @RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        this.inSearch = inSearch;
        search = namePart;
        selectListPartsForView = listMenu;
        return listParts(partDAOService.findByName(namePart));
    }


    @RequestMapping(value = "/addPart", method = RequestMethod.POST)
    public String addPart() {
        inSearch = false;
        return "addPart";
    }

    @RequestMapping(value = "/createPart")
    public ModelAndView createPart(@RequestParam("namePart") String namePart,
                                   @RequestParam(value = "isNeeded", defaultValue = "false") boolean isNeeded,
                                   @RequestParam("amount") int amount) {
        try {
            partDAOService.addPart(new Part(namePart, isNeeded, amount));
        } catch (HandlingException e) {
            e.getStackTrace();
            System.out.println("RETRY");
            return listParts(null);
        }
        selectListPartsForView = "All";
        return listParts(null);
    }

    @RequestMapping(value = "/deletePart")
    public ModelAndView deletePart(@RequestParam("deletePart") Integer deletePartById) {
        partDAOService.deletePart(deletePartById);
        selectListPartsForView = "All";
        inSearch = false;
        search = null;
        return listParts(null);
    }

    @RequestMapping(value = "/selectListPartsForView")
    public ModelAndView selectListPartsForView(@RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        selectListPartsForView = listMenu;
        if (inSearch) {
            List<Part> partArrayListInSearchExist = partDAOService.getPartsByName();
            List<Part> partArrayListInSearchResultRequest = new ArrayList<>();
            switch (listMenu) {
                case "All":
                    modelAndView = listParts(partArrayListInSearchExist);
                    break;
                case "isNeeded":
                    partArrayListInSearchExist.forEach(part -> {
                        if (part.getIsNeeded()) partArrayListInSearchResultRequest.add(part);
                    });
                    modelAndView = listParts(partArrayListInSearchResultRequest);
                    break;
                case "options":
                    partArrayListInSearchExist.forEach(part -> {
                        if (!part.getIsNeeded()) partArrayListInSearchResultRequest.add(part);
                    });
                    modelAndView = listParts(partArrayListInSearchResultRequest);
            }
        } else {
            switch (listMenu) {
                case "All":
                    modelAndView = listParts(null);
                    break;
                case "isNeeded":
                    modelAndView = listParts(partDAOService.findAllPartsIsNeeded(true));
                    break;
                case "options":
                    modelAndView = listParts(partDAOService.findAllPartsIsNeeded(false));
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editPart")
    public ModelAndView editPart(@RequestParam("editPart") Integer editPartID) {
        selectListPartsForView = "All";
        inSearch = false;
        search = null;
        Part editPart = partDAOService.getPartByID(editPartID);
        modelAndView.addObject("editPart", editPart);
        modelAndView.setViewName("/editPart");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePart")
    public ModelAndView updatePart(@RequestParam("partIdEdit") Integer partIdEdit,
                                   @RequestParam("newNamePart") String newNamePart,
                                   @RequestParam(value = "newIsNeeded", defaultValue = "false") boolean newIsNeeded,
                                   @RequestParam("newAmount") int newAmount) {
        Part partUpdate = partDAOService.getPartByID(partIdEdit);
        partUpdate.setNamePart(newNamePart);
        partUpdate.setIsNeeded(newIsNeeded);
        partUpdate.setAmount(newAmount);
        partDAOService.updatePart(partUpdate);
        return listParts(null);
    }


}
