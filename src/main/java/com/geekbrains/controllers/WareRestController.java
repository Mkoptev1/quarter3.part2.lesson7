package com.geekbrains.controllers;

import com.geekbrains.ExceptionHandler.WareErrorResponse;
import com.geekbrains.ExceptionHandler.WareNotFoundException;
import com.geekbrains.entities.Ware;
import com.geekbrains.services.WareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WareRestController {
    private WareServiceImpl wareServiceImpl;

    @Autowired
    public void setWareServiceImpl(WareServiceImpl wareServiceImpl) {
        this.wareServiceImpl = wareServiceImpl;
    }

    // Получаем товар
    // http://localhost:8189/app/api/ware/1
    @GetMapping("/ware/{wareId}")
    public Ware getWareById(@PathVariable Long wareId) {
        return wareServiceImpl.get(wareId)
                .orElseThrow(() -> new WareNotFoundException("Ware not found: " + wareId));
    }

    // Получаем список товаров
    // http://localhost:8189/app/api/ware/
    @GetMapping("/ware")
    public List<Ware> getAllWare() {
        return wareServiceImpl.getAll();
    }

    // Добавление товара
    // http://localhost:8189/app/api/ware/
    @PostMapping("/ware")
    public Ware addWare(@RequestBody Ware addWare) {
        addWare.setId(0L);
        return wareServiceImpl.saveAddRest(addWare);
    }

    // Редактирование товара
    // http://localhost:8189/app/api/ware/
    @PutMapping("/ware")
    public Ware editWare(@RequestBody Ware editWare) {
        editWare = wareServiceImpl.saveEditRest(editWare);
        return editWare;
    }

    // Удаление товара
    // http://localhost:8189/app/api/ware/1
    @DeleteMapping("/ware/{wareId}")
    public int delWare(@PathVariable(name = "wareId") Long wareId) {
        wareServiceImpl.delete(wareId);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<WareErrorResponse> handleException(WareNotFoundException exc) {
        WareErrorResponse studentsErrorResponse = new WareErrorResponse();
        studentsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentsErrorResponse.setMessage(exc.getMessage());
        studentsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(studentsErrorResponse, HttpStatus.NOT_FOUND);
    }
}