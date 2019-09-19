package com.geekbrains.repositories;

import com.geekbrains.entities.Ware;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareRepository extends PagingAndSortingRepository<Ware, Long>, JpaSpecificationExecutor<Ware> {
}