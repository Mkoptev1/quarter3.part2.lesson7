package com.geekbrains.repositories;

import com.geekbrains.entities.Client;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {
}