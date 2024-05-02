package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDto;
import com.devsuperior.dsmeta.dto.SaleSumaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate dataAtual = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	@Transactional
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	@Transactional(readOnly = true)
	public Page<SaleSumaryDto> findAll(String minimo, String maximo, Pageable pageable) {

		LocalDate result =minimo.equals("") ? dataAtual.minusYears(1L) : LocalDate.parse(minimo);
		LocalDate hoje = maximo.equals("") ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maximo);

		return repository.searchAll(result, hoje, pageable);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDto> findReports(String minimo, String maximo, String name, Pageable pageable){
		LocalDate result =minimo.equals("") ? dataAtual.minusYears(1L) : LocalDate.parse(minimo);
		LocalDate hoje = maximo.equals("") ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maximo);

		return repository.searchReports(result, hoje, name, pageable);
	}
}
