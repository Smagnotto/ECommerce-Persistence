package br.com.fiap.service;

import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;

public interface IPedidoService {
	List<Pedido> getAllPedidos();
	Pedido getPedidoById(long id);
	Pedido addPedido(Pedido pedido); 
	Pedido updatePedido(Pedido pedido); 
	void deletePedido(long id);
	
	Pedido addClientePedido(long id, Cliente cliente);
	Pedido addProdutoPedido(long id, Produto produto);
}
