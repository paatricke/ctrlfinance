package br.com.ctrlfinance.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Despesa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long despesa_id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private float valor;
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar data;
	private String categoria;
	@Column(nullable=false)
	private String tipo;
	@ManyToOne
	private Usuario usuario;
	
	public Long getDespesa_id() {
		return despesa_id;
	}
	public void setDespesa_id(Long despesa_id) {
		this.despesa_id = despesa_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
