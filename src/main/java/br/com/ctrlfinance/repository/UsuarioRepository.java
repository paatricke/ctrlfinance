package br.com.ctrlfinance.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ctrlfinance.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	
	public Usuario findByToken (String token);
}
