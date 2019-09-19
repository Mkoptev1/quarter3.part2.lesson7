package com.geekbrains.controllers;

import com.geekbrains.entities.Ware;
import com.geekbrains.repositories.specifications.WareSpecifications;
import com.geekbrains.services.WareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ware")
public class WareController {
    private WareServiceImpl wareServiceImpl;
    private String templateFolder = "ware/";

    @Autowired
    public void setWareServiceImpl(WareServiceImpl wareServiceImpl) {
        this.wareServiceImpl = wareServiceImpl;
    }

    // Список товаров
    // http://localhost:8189/app/ware
    @GetMapping()
    public String getWareList
    (
        Model model,
        Pageable pageable,
        @RequestParam(name = "pages", required = false, defaultValue = "0") int pageNumber,
        @RequestParam(name = "size", required = false, defaultValue = "5") int pageSize,
        @RequestParam(name = "name", required = false) String filterWareName,
        @RequestParam(name = "maxPrice", required = false) Long filterWareMaxPrice,
        @RequestParam(name = "minPrice", required = false) Long filterWareMinPrice
    ) {
        // Пагинация начинается с 0 элемента
        if (pageNumber > 0) {
            pageNumber --;
        }
        Specification<Ware> spec = Specification.where(null);
        spec = spec.and(WareSpecifications.getWareByName(filterWareName));
        spec = spec.and(WareSpecifications.priceGreaterThanOrEq(filterWareMinPrice));
        spec = spec.and(WareSpecifications.priceLesserThanOrEq(filterWareMaxPrice));

        Pageable firstPageWithFiveElements = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<Ware> wareList = wareServiceImpl.showAllWare(spec, firstPageWithFiveElements);

        model.addAttribute("page", wareList);
        model.addAttribute("pages", pageNumber);
        model.addAttribute("size", pageSize);
        model.addAttribute("name", filterWareName);
        model.addAttribute("maxPrice", filterWareMaxPrice);
        model.addAttribute("minPrice", filterWareMinPrice);

        List<String> pageCountList = Arrays.asList("1", "5", "10", "30", "50");
        model.addAttribute("pageCountList", pageCountList);

        return templateFolder + "ware-list";
    }

    // Форма редактирования товара
    // http://localhost:8189/app/ware/edit-ware
    @GetMapping("/edit-ware")
    public String editWare(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") Long id) {
        Optional<Ware> editWare = Optional.of(new Ware());

        // Редактирование существующего товара
        if (id > 0) {
            editWare = wareServiceImpl.get(id);
        }
        model.addAttribute("ware", editWare);
        return templateFolder + "edit-ware";
    }

    // Сохранение товара
    // http://localhost:8189/app/ware/save-ware
    @PostMapping("/save-ware")
    public String saveWare(@ModelAttribute("ware") Ware ware) {
        wareServiceImpl.save(ware);
        return "redirect:/ware/";
    }

    // Удаление товара
    // http://localhost:8189/app/ware/del-ware/1
    @GetMapping("/del-ware/{id}")
    public String delWare(@PathVariable(name = "id") Long id) {
        wareServiceImpl.delete(id);
        return "redirect:/ware/";
    }
}