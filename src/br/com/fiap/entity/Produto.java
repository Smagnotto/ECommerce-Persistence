package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto implements Serializable {
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
	
	@Column(name="cpf", length=50, nullable = false)
	private String nome;
	private int qtdEstoque;
	private double valor;

	public Produto (String nome, int qtdEstoque, double valor) {
		super();
		this.nome = nome;
		this.qtdEstoque = qtdEstoque;
		this.valor = valor;
	}
	
	public Produto () {
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

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="produtos")
	private Set<Pedido> pedidos = new HashSet<Pedido>();
}
