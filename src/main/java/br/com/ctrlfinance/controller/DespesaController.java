package br.com.ctrlfinance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctrlfinance.model.Despesa;
import br.com.ctrlfinance.service.DespesaService;

@Controller
public class DespesaController {

	@Autowired
	private DespesaService service;
	
	@RequestMapping(value = "salvaDespesa", method = RequestMethod.POST)
	public String salvaDespesa(Despesa despesa) {
		service.salvaDespesa(despesa);
		return "redirect:/principal";
	}
	
	@RequestMapping("/principal")
	public ModelAndView principal(ModelAndView model, HttpServletRequest req) {
		service.listarTodos(model, req);
		return model;
	}
	
	@RequestMapping("/lista")
	public ModelAndView lista(ModelAndView model, HttpServletRequest req) {
		service.listarTodos(model, req);
		return model;
	}
	
	@RequestMapping("/apagar/{id}")
	public String apagar(@PathVariable Long id) {
		service.apagar(id);
		return "redirect:/lista";
	}
	
}
