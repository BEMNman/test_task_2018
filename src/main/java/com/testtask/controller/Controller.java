package com.testtask.controller;

import com.testtask.HandlingException;
import com.testtask.entity.Part;
import com.testtask.service.PartDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
        this.search = "";
        this.modelParts = new ArrayList<>();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String start() {
        this.selectListPartsForView = "All";
        this.inSearch = false;
        this.search = "";
        modelParts = partDAOService.listParts();
        return "redirect:/listParts";
    }

//    @RequestMapping(value = {"/listParts"}, method = RequestMethod.GET)
//    public ModelAndView listParts() {
//        List<Part> partList = new ArrayList<>();
//        switch (selectListPartsForView) {
//            case "All":
//                partList = modelParts;
//                break;
//            case "isNeeded":
//                for(Part p : modelParts) {
//                    if(p.getIsNeeded()) {
//                        partList.add(p);
//                    }
//                }
//                break;
//            case "options":
//                for(Part p : modelParts) {
//                    if(!p.getIsNeeded()) {
//                        partList.add(p);
//                    }
//                }
//        }
//        modelAndView.addObject("countComputer", partDAOService.countComputer());
//        modelAndView.addObject("selectListPartsForView", selectListPartsForView);
//        modelAndView.addObject("search", search);
//        modelAndView.addObject("parts", partList);
//        modelAndView.setViewName("listParts");
//        return modelAndView;
//    }


    @RequestMapping(value = "/listParts", method = RequestMethod.GET)
    public ModelAndView userListPage(@RequestParam(required = false) Integer page) {
        ModelAndView model = new ModelAndView("listParts");
        List<Part> parts = partDAOService.listParts();


        PagedListHolder<Part> pagedListHolder = new PagedListHolder<>(parts);
        pagedListHolder.setPageSize(10);
        int numberOfPages = pagedListHolder.getPageCount();
        model.addObject("maxPages", numberOfPages);

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        model.addObject("page", page);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            parts = pagedListHolder.getPageList();
            model.addObject("parts", parts);
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            parts = pagedListHolder.getPageList();
            model.addObject("parts", parts);
            model.addObject("countComputer", partDAOService.countComputer());
            model.addObject("selectListPartsForView", selectListPartsForView);
            model.addObject("search", search);
        }
        return model;
    }

    @GetMapping(value = "/searchPart")
    public ModelAndView searchPart(@RequestParam(value = "search", defaultValue = "") String namePart) {
        inSearch = true;
        search = namePart.trim();
        selectListPartsForView = "All";
        modelParts = partDAOService.findByName(search);
        modelAndView = userListPage(1);
//        modelAndView = listParts();
        modelAndView.setViewName("/searchPart");
        return modelAndView;
    }

    @RequestMapping(value = "/addPart")
    public String addPart(@RequestParam(value = "isExistPart", defaultValue = "false") boolean isExistPart, Model model) {
        model.addAttribute("isExistPart", isExistPart);
        model.addAttribute("partAdd", new Part());
        return "/addPart";
    }

    @PostMapping(value = "/createPart")
    public String createPart(@ModelAttribute("part") Part part, Model model) {
        try {
            partDAOService.addPart(part);
        } catch (HandlingException e) {
            e.getStackTrace();
            model.addAttribute("isExistPart", true);
            model.addAttribute("partAdd", part);
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
    public ModelAndView selectListPartsForView(@RequestParam(value = "listMenu", defaultValue = "All") String listMenu) {
        selectListPartsForView = listMenu;
        modelAndView = userListPage(1);
//        modelAndView = listParts();
        if (inSearch) {
            modelAndView.setViewName("/searchPart");
        } else {
            modelAndView.setViewName("/listParts");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editPart")
    public String editPart(@ModelAttribute("editPartId") Integer editPartID, Model model) {
        Part editPart = partDAOService.getPartByID(editPartID);
        model.addAttribute("editPart", editPart);
        return "/editPart";
    }

    @RequestMapping(value = "/updatePart", method = RequestMethod.POST)
    public String updatePart(@ModelAttribute("editPart") Part part) {
        partDAOService.updatePart(part);
        modelParts = partDAOService.listParts();
        return "redirect:/";
    }
}
