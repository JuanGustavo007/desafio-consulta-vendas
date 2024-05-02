package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDto;
import com.devsuperior.dsmeta.dto.SaleSumaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDto>> getReport(@RequestParam(name = "minimo", defaultValue = "") String minimo, @RequestParam (name = "maximo",defaultValue = "") String maximo,@RequestParam(value = "name", defaultValue = "")String name, Pageable pageable) {
		Page<SaleReportDto> dto = service.findReports(minimo, maximo,name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumaryDto>> getSummary(@RequestParam(name = "minimo", defaultValue = "") String minimo, @RequestParam (name = "maximo",defaultValue = "") String maximo, Pageable pageable) {
		Page<SaleSumaryDto> dto = service.findAll(minimo, maximo, pageable);
		return ResponseEntity.ok(dto);
	}
}
