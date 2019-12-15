package com.javacodegeeks.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebArithmeticController {
	@Autowired
	protected WebAdditionService additionService;

	@Autowired
	protected WebSubtractionService subtractionService;

	@Autowired
	protected WebCreaclienteService creaclienteService;

	@Autowired
	protected WebKpideclientesService kpideclientesService;

	@Autowired
	protected WebListclientesService listclientesService;

	protected Logger logger = Logger.getLogger(WebArithmeticController.class
			.getName());

	public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService,WebCreaclienteService creaclienteService,WebKpideclientesService kpideclientesService, WebListclientesService listclientesService) {
		this.additionService = additionService;
		this.subtractionService = subtractionService;
		this.creaclienteService = creaclienteService;
		this.kpideclientesService = kpideclientesService;
		this.listclientesService = listclientesService;
	}

	@RequestMapping("/add")
	public String doAdd(@RequestParam(defaultValue="0") String addend1,
			@RequestParam(defaultValue="0") String addend2,
			Model model) {

		String sum = additionService.add(addend1, addend2);

		logger.info("Sum: " + sum);
		model.addAttribute("json", sum);

		return "sum";
	}

	@RequestMapping("/subtract")
	public String doSubtract(@RequestParam(defaultValue="0") String minuend,
			@RequestParam(defaultValue="0") String subtrahend,
			Model model) {

		String difference = subtractionService.subtract(minuend, subtrahend);

		logger.info("Difference: " + difference);
		model.addAttribute("json", difference);

		return "difference";
	}

	@RequestMapping("/creacliente")
	public String doCreacliente(@RequestParam(defaultValue="") String nombre,
			@RequestParam(defaultValue="") String apellido,
			@RequestParam(defaultValue="") String edad,
			@RequestParam(defaultValue="") String fechanacimiento,
			Model model) {

		String cliente = creaclienteService.creacliente(nombre, apellido, edad, fechanacimiento);

		logger.info("cliente: " + cliente);
		model.addAttribute("json", cliente);

		return "cliente";
	}
	@RequestMapping("/kpideclientes")
	public String doKpideclientes(Model model) {

		String kpi = kpideclientesService.kpideclientes();

		logger.info("kpi: " + kpi);
		model.addAttribute("json", kpi);

		return "kpi";
	}
	@RequestMapping("/listclientes")
	public String doListclientes(Model model) {

		String list = listclientesService.listclientes();

		logger.info("list: " + list);
		model.addAttribute("json", list);

		return "list";
	}
	
}
