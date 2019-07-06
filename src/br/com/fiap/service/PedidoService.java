package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;
import br.com.fiap.repository.PedidoRepository;
import br.com.fiap.repository.ProdutoRepository;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> getAllPedidos() {
		List<Pedido> lista = new ArrayList<>();
		pedidoRepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}

	@Override
	public Pedido getPedidoById(long id) {
		return pedidoRepository.findById(id).get();
	}

	@Override
	public Pedido addPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido updatePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public void deletePedido(long id) {
		pedidoRepository.deleteById(id);
		
	}

	@Override
	public Pedido addClientePedido(long id, Cliente cliente) {
		Pedido pedido = getPedidoById(id);
		
		cliente.getPedidos().add(pedido);
		pedido.setCliente(cliente);
		
		return updatePedido(pedido);
	}

	@Override
	public Pedido addProdutoPedido(long id, Produto produto) {
		Pedido pedido = getPedidoById(id);
		
		produto.getPedidos().add(pedido);
		pedido.getProdutos().add(produto);
		
		return updatePedido(pedido);
		
	}

}
