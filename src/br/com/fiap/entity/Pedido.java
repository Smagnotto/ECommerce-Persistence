package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn; 

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="IDCLIENTE")
	private Cliente cliente;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL) 
	@JoinTable(name="PEDIDO_PRODUTO", joinColumns = {@JoinColumn(name="PEDIDO_ID", nullable=false, updatable=false)}, 
	inverseJoinColumns = {@JoinColumn(name="PRODUTO_ID", nullable=false, updatable=false)})
	private Set<Produto> produtos = new HashSet<Produto>();
	
	private double valorPedido;

	public long getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}
}
