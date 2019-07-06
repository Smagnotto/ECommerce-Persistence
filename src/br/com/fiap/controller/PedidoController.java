package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;
import br.com.fiap.service.IPedidoService;

@RestController
@RequestMapping("ecommerce")
public class PedidoController {
	@Autowired
	private IPedidoService pedidoService;

	@GetMapping("pedido/{id}")
	public ResponseEntity<Pedido> getpedidoById(@PathVariable("id") Long id) {
		Pedido pedido = pedidoService.getPedidoById(id);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}

	@GetMapping("pedidos")
	public ResponseEntity<List<Pedido>> getAllpedidos() {
		List<Pedido> lista = pedidoService.getAllPedidos();
		return new ResponseEntity<List<Pedido>>(lista, HttpStatus.OK);
	}

	@PostMapping("pedido")
	public ResponseEntity<Void> addpedido(@RequestBody Pedido pedido, UriComponentsBuilder builder) {
		Pedido savedpedido = pedidoService.addPedido(pedido);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pedido/{id}").buildAndExpand(savedpedido.getCodigo()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("pedido")
	public ResponseEntity<Pedido> updatepedido(@RequestBody Pedido pedido) {
		pedidoService.updatePedido(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}

	@DeleteMapping("pedido/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
		pedidoService.deletePedido(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("pedido/produto/{id}")
	public ResponseEntity<Pedido> addProdutoPedido(@PathVariable("id") long id, @RequestBody Produto produto) {
		Pedido pedido = pedidoService.addProdutoPedido(id, produto);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	@PutMapping("pedido/cliente/{id}")
	public ResponseEntity<Pedido> addClienteProduto(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		Pedido pedido = pedidoService.addClientePedido(id, cliente);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
}
