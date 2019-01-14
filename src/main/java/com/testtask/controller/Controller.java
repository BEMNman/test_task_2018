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
    private boolean inSearch;               //флаг нахождения в форме searchPart
    private String search;                  //запрос поиска
    private List<Part> modelParts;          //список комплектующих для отображения
    private ModelAndView modelAndView;      //модель отображения в заданном view
    private int page;                       //страница списка

    @Autowired
    private PartDAOService partDAOService;

    public Controller(PartDAOService partDAOService) {
        this.partDAOService = partDAOService;
        this.modelAndView = new ModelAndView();
        this.selectListPartsForView = "All";
        this.inSearch = false;
        this.search = "";
        this.page = 0;
        this.modelParts = new ArrayList<>();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String start() {
        selectListPartsForView = "All";
        inSearch = false;
        search = "";
        page = 0;
        modelParts = partDAOService.listParts();
        return "redirect:/listParts";
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public ModelAndView listPage(@PathVariable(required = false ) Integer page) {
        this.page = page;
        if(inSearch) {
            modelAndView = searchPart(search);
            modelAndView.setViewName("/searchPart");
        } else {
            modelAndView = listParts();
            modelAndView.setViewName("/listParts");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/listParts", method = RequestMethod.GET)
    public ModelAndView listParts() {
        modelAndView.setViewName("listParts");
        List<Part> parts = new ArrayList<>();
        switch (selectListPartsForView) {
            case "All":
                parts = modelParts;
                break;
            case "isNeeded":
                for(Part p : modelParts) {
                    if(p.getIsNeeded()) {
                        parts.add(p);
                    }
                }
                break;
            case "options":
                for(Part p : modelParts) {
                    if(!p.getIsNeeded()) {
                        parts.add(p);
                    }
                }
        }
        PagedListHolder<Part> pagedListHolder = new PagedListHolder<>(parts);
        pagedListHolder.setPageSize(10);
        int numberOfPages = pagedListHolder.getPageCount();
        modelAndView.addObject("maxPages", numberOfPages);
        if (page == 0 || page < 1 || page > pagedListHolder.getPageCount()) page = 1;
        modelAndView.addObject("page", page);
        if (page == 0 || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            parts = pagedListHolder.getPageList();
            modelAndView.addObject("parts", parts);
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            parts = pagedListHolder.getPageList();
            modelAndView.addObject("parts", parts);
            modelAndView.addObject("countComputer", partDAOService.countComputer());
            modelAndView.addObject("selectListPartsForView", selectListPartsForView);
            modelAndView.addObject("page", page);
            modelAndView.addObject("search", search);
        }
        return modelAndView;
    }

    @GetMapping(value = "/searchPart")
    public ModelAndView searchPart(@RequestParam(value = "search", defaultValue = "") String namePart) {
        if(!inSearch) page = 0;
        inSearch = true;
        search = namePart.trim();
        selectListPartsForView = "All";
        modelParts = partDAOService.findByName(search);
        modelAndView = listParts();
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
        page = 0;
        modelAndView = listParts();
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
