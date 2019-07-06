package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	
	@Column(name = "nome", length=50, nullable = false)
	private String nome;
	
	@Column(name="cpf", length=14, nullable = false)
	private String cpf;
	
	@Column(name="email", length=100)
	private String email;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private Set<EnderecoCliente> endereco = new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private Set<Pedido> pedidos = new HashSet<>();
	
	public Cliente(String nome, String cpf, String email) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public Cliente() {
		super();
	}

	public long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<EnderecoCliente> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<EnderecoCliente> endereco) {
		this.endereco = endereco;
	}
}
