package br.com.ctrlfinance.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctrlfinance.model.Despesa;
import br.com.ctrlfinance.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repo;
	
	public void salvaDespesa(Despesa despesa) {
		repo.save(despesa);
	}
	
	public List<Despesa> buscaDespesa(String usuario) {
		return repo.findByTipo(usuario);
	}
	
	public ModelAndView listarTodos(ModelAndView model, HttpServletRequest req) {
		String usuario = req.getRemoteUser();	
		List<Despesa> buscas = buscaDespesa(usuario);
		model.addObject("buscas", buscas);
		return model;
	}
	
	public void apagar(Long id) {
		repo.delete(id);
	}
}
