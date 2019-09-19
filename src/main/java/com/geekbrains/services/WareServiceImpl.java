package com.geekbrains.services;

import com.geekbrains.entities.Ware;
import com.geekbrains.repositories.WareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WareServiceImpl{
    private WareRepository wareRepository;

    @Autowired
    public void setWareRepository(WareRepository _wareRepository) {
        this.wareRepository = _wareRepository;
    }

    @Transactional(readOnly = true)
    public Page<Ware> getAll(Pageable pageable) {
        return (Page<Ware>) wareRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ware> getAll() {
        return (List<Ware>) wareRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Ware> get(Long id) {
        return wareRepository.findById(id);
    }

    @Transactional
    public void save(Ware ware) {
        wareRepository.save(ware);
    }

    @Transactional
    public Ware saveAddRest(Ware ware) {
        return wareRepository.save(ware);
    }

    @Transactional
    public void delete(Long id) {
        wareRepository.deleteById(id);
    }

    @Transactional
    public Page<Ware> showAllWare(Specification<Ware> specification, Pageable pageable) {
        return (Page<Ware>) wareRepository.findAll(specification, pageable);
    }

    public Ware saveEditRest(Ware editWare) {
        return wareRepository.save(editWare);
    }
}