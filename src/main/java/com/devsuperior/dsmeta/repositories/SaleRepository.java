package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDto;
import com.devsuperior.dsmeta.dto.SaleSumaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new  com.devsuperior.dsmeta.dto.SaleSumaryDto(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minimo AND :maximo " +
            "GROUP BY obj.seller.name")
    Page<SaleSumaryDto> searchAll(LocalDate minimo, LocalDate maximo, Pageable pageable);

    @Query(value = " SELECT obj FROM Sale obj JOIN FETCH obj.seller WHERE obj.date BETWEEN :minimo AND :maximo AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller WHERE obj.date BETWEEN :minimo AND :maximo AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleReportDto> searchReports(LocalDate minimo, LocalDate maximo, String name, Pageable pageable);
}
