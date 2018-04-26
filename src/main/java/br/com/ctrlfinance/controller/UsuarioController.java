package br.com.ctrlfinance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ctrlfinance.model.Usuario;
import br.com.ctrlfinance.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	public JavaMailSender mail;

	@RequestMapping("/")
	public String cadastro() {
		return "form";
	}
	
	
	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public String salvar(Usuario usuario, RedirectAttributes att){
		if(service.emailExist(usuario.getEmail())) {
			att.addFlashAttribute("erro", "Usuário já cadastrado");
		} else {
		att.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		service.salvar(usuario);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/recuperaSenha")
	public String esqueciSenha() {
		return "recuperaSenha";
	}
	
	@RequestMapping(value="recupera", method = RequestMethod.POST)
	public String recupera(@RequestParam("email") String email, HttpServletRequest request, RedirectAttributes att) {
		//Salva token
		Usuario usuario = service.loadUserByUsername(email);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setToken(encoder.encode(usuario.getEmail()));
		service.salvaToken(usuario);
		//Envia email
		String url = request.getScheme() + "://" + request.getServerName();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(usuario.getEmail());
		message.setSubject("Redefinição de senha");
		message.setText("Para redefinir sua senha clique no link abaixo \n"
							+ url + "/reset?token=" + usuario.getToken());
		mail.send(message);
		att.addFlashAttribute("enviado", "Um link para redefinir sua senha foi enviado no seu e-mail");
		return "redirect:/recuperaSenha";
	}
	
	@RequestMapping("/tokenInvalido")
	public String erroToken() {
		return "tokenInvalido";
		}
		
	
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String atualizaSenha(@RequestParam("token") String token) {
		Usuario usuario = service.findByToken(token);
		if(usuario == null) {
			return "tokenInvalido";
		} else {
		return "atualizaSenha";
		}
	}
	
	@RequestMapping("/senhaAlterada")
	public String reset() {
		return "senhaAlterada";
	}
	
	@RequestMapping(value="reset", method = RequestMethod.POST)
	public String atualiza(@RequestParam("senha") String senha, @RequestParam("token") String token, RedirectAttributes att) {
		Usuario usuario = service.findByToken(token);
		usuario.setSenha(senha);
		usuario.setToken(null);
		service.salvar(usuario);
		att.addFlashAttribute("salvo", "Senha redefinida com sucesso");
		return "redirect:/senhaAlterada";
	}
}
