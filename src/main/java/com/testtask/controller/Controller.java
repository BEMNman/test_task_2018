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

    public Controller(PartDAOService partDAOService) {
        this.partDAOService = partDAOService;
        this.modelAndView = new ModelAndView();
        this.selectListPartsForView = "All";
        this.inSearch = false;
    }

    @RequestMapping(value = {"/", "/listParts"}, method = RequestMethod.GET)
    public ModelAndView start() {
        selectListPartsForView = "All";
        inSearch = false;
        modelAndView = listParts(null);
        return modelAndView;
    }

    public ModelAndView listParts(List<Part> partList) {
        List<Object> dataForJsp = new ArrayList<>();
        if (partList == null) {
            if(!inSearch) {
                dataForJsp.add(partDAOService.listParts());
            } else {
                System.out.println("+++++++++" + partDAOService.getPartsByName());
                dataForJsp.add(partDAOService.getPartsByName());
            }

        } else {
            dataForJsp.add(partList);
        }
        dataForJsp.add(partDAOService.countComputer());
        dataForJsp.add(selectListPartsForView);
        modelAndView.addObject("part", dataForJsp);
        modelAndView.setViewName("listParts");
        return modelAndView;
    }

    @RequestMapping(value = "/searchPart", method = RequestMethod.GET)
    public ModelAndView searchPart(@RequestParam("search") String namePart,
                                   @RequestParam(value = "inSearch", defaultValue = "false") boolean inSearch,
                                   @RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        this.inSearch = inSearch;
        selectListPartsForView = listMenu;
        List<Object> dataForJsp = new ArrayList<>();
        dataForJsp.add(partDAOService.findByName(namePart));
        dataForJsp.add(partDAOService.countComputer());
        dataForJsp.add(selectListPartsForView);
        modelAndView.addObject("part", dataForJsp);
        return modelAndView;
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

    @RequestMapping(value = "/addPart", method = RequestMethod.POST)
    public String addPart() {
        return "/addPart";
    }

    @RequestMapping(value = "/deletePart")
    public ModelAndView deletePart(@RequestParam("deletePart") Integer deletePartById) {
        partDAOService.deletePart(deletePartById);
        selectListPartsForView = "All";
        inSearch = false;
        return listParts(null);
    }

    @RequestMapping(value = "/updatePart")
    public ModelAndView updatePart(Part partUpdate) {
        modelAndView.addObject("selectListPartsForView", selectListPartsForView);
        modelAndView.addObject("inSearch", inSearch );
        modelAndView.setViewName("/updatePart");
        return modelAndView;
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "/test1";
    }

    @RequestMapping(value = "/selectListPartsForView")
    public ModelAndView selectListPartsForView(@RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        selectListPartsForView = listMenu;
        if(inSearch) {
            List<Part> partArrayListInSearchExist = partDAOService.getPartsByName();
            List<Part> partArrayListInSearchResultRequest = new ArrayList<>();
            switch (listMenu) {
                case "All":
                    modelAndView = listParts(partArrayListInSearchExist);
                    break;
                case "isNeeded":
                    partArrayListInSearchExist.forEach(part -> {
                        if(part.getIsNeeded()) partArrayListInSearchResultRequest.add(part);
                    });
                    modelAndView = listParts(partArrayListInSearchResultRequest);
                    break;
                case "options":
                    partArrayListInSearchExist.forEach(part -> {
                        if(!part.getIsNeeded()) partArrayListInSearchResultRequest.add(part);
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
}
