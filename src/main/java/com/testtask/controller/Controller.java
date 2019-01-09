package com.testtask.controller;

import com.testtask.HandlingException;
import com.testtask.entity.Part;
import com.testtask.service.PartDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private String selectListPartsForView;  //переменная параметра фильтрации
    private boolean inSearch;               //находимся в форме searchPart
    private String search;                  //запрос поиска
    private List<Part> modelParts;          //список комплектующих для отображения
    private ModelAndView modelAndView;

    @Autowired
    private PartDAOService partDAOService;

    public Controller(PartDAOService partDAOService) {
        this.partDAOService = partDAOService;
        this.modelAndView = new ModelAndView();
        this.selectListPartsForView = "All";
        this.inSearch = false;
        this.search = null;
        this.modelParts = new ArrayList<>();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String start() {
        this.selectListPartsForView = "All";
        this.inSearch = false;
        this.search = null;
        modelParts = partDAOService.listParts();
        return "redirect:/listParts";
    }

    @RequestMapping(value = {"/listParts"}, method = RequestMethod.GET)
    public ModelAndView listParts() {
        modelAndView.addObject("countComputer", partDAOService.countComputer());
        modelAndView.addObject("selectListPartsForView", selectListPartsForView);
        modelAndView.addObject("search", search);
        modelAndView.addObject("parts", modelParts);
        modelAndView.setViewName("listParts");
        return modelAndView;
    }

    @GetMapping(value = "/searchPart")
    public ModelAndView searchPart(@RequestParam(value = "search", defaultValue = "") String namePart,
                                   @RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        if (!namePart.equals("")) {
            inSearch = true;
            search = namePart;
            selectListPartsForView = listMenu;
            modelParts = partDAOService.findByName(search);
        } else {
            search = null;
            modelParts = partDAOService.listParts();
        }
        modelAndView = listParts();
        modelAndView.setViewName("/searchPart");
        return modelAndView;
    }


    @RequestMapping(value = "/addPart")
    public String addPart(@RequestParam(value = "partExist", defaultValue = "false") boolean partExist,
                          Model model) {
        model.addAttribute("partExist", partExist);
        return "/addPart";
    }

    @PostMapping(value = "/createPart")
    public String createPart(@ModelAttribute("part") Part part, Model model) {
        try {
            partDAOService.addPart(part);
            modelParts = partDAOService.listParts();
        } catch (HandlingException e) {
            e.getStackTrace();
            model.addAttribute("partExist", true);
            return "/addPart";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/deletePart")
    public String deletePart(@RequestParam("deletePart") Integer deletePartById) {
        partDAOService.deletePart(deletePartById);
        modelParts = partDAOService.listParts();
        return "redirect:/";
    }

    @GetMapping(value = "/selectListPartsForView")
    public String selectListPartsForView(@RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        selectListPartsForView = listMenu;
        /*
         * фильтрация в зависимости нахождении в форме(вне формы) "searchPart"
         */
        if (inSearch) {
            List<Part> partArrayListInSearchExist = partDAOService.getPartsByName();
            switch (listMenu) {
                case "All":
                    modelParts = partArrayListInSearchExist;
                    break;
                case "isNeeded":
                    modelParts = new ArrayList<>();
                    partArrayListInSearchExist.forEach(part -> {
                        if (part.getIsNeeded()) modelParts.add(part);
                    });
                    break;
                case "options":
                    modelParts = new ArrayList<>();
                    partArrayListInSearchExist.forEach(part -> {
                        if (!part.getIsNeeded()) modelParts.add(part);
                    });
            }
            return "redirect:/searchPart";
        } else {
            switch (listMenu) {
                case "All":
                    modelParts = partDAOService.listParts();
                    break;
                case "isNeeded":
                    modelParts = partDAOService.findAllPartsIsNeeded(true);
                    break;
                case "options":
                    modelParts = partDAOService.findAllPartsIsNeeded(false);
            }
        }
        return "redirect:/listParts";
    }

    @RequestMapping(value = "/editPart")
    public ModelAndView editPart(@RequestParam("editPart") Integer editPartID) {
        Part editPart = partDAOService.getPartByID(editPartID);
        modelAndView.addObject("editPart", editPart);
        modelAndView.setViewName("/editPart");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePart")
    public String updatePart(@RequestParam("partIdEdit") Integer partIdEdit,
                             @RequestParam("newNamePart") String newNamePart,
                             @RequestParam(value = "newIsNeeded", defaultValue = "false") boolean newIsNeeded,
                             @RequestParam("newAmount") int newAmount) {
        Part partUpdate = partDAOService.getPartByID(partIdEdit);
        partUpdate.setNamePart(newNamePart);
        partUpdate.setIsNeeded(newIsNeeded);
        partUpdate.setAmount(newAmount);
        partDAOService.updatePart(partUpdate);
        modelParts = partDAOService.listParts();
        return "redirect:/";
    }
}
