package br.com.ctrlfinance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ctrlfinance.model.Usuario;
import br.com.ctrlfinance.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	public void salvar(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);
	}
	
	public void salvaToken(Usuario usuario) {
		repository.save(usuario);
	}
	
	public boolean emailExist(String email) {
		Usuario usuario = repository.findOne(email);
		if(usuario != null) {
			return true;
		}
		return false;
	}
	
	public Usuario findByToken(String token) {
		Usuario usuario = repository.findByToken(token);
		return usuario;
	}
	
	public Usuario loadUserByToken(String token) {
		Usuario usuario = repository.findOne(token);
		return usuario;
	}
	
	public Usuario loadUserByUsername(String email) {
		Usuario usuario = repository.findOne(email);
		return usuario;
	}

}
