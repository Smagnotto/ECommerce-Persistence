package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Cacheable(value = "clienteCache", key = "#id")
	public Cliente getClienteById(long id) {
		return clienteRepository.findById(id).get();
	}

	@Override
	@Cacheable(value = "allClientesCache", unless = "#result.size() == 0")
	public List<Cliente> getAllClientes() {
		List<Cliente> lista = new ArrayList<>();
		clienteRepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}

	@Override
	@Caching(put = { @CachePut(value = "clienteCache", key = "#cliente.getCodigo()") }, evict = {
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public Cliente addCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Caching(put = { @CachePut(value = "clienteCache", key = "#cliente.getCodigo()") }, evict = {
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "clienteCache", key = "#id"),
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public void deleteCliente(long id) {
		clienteRepository.delete(clienteRepository.findById(id).get());
	}
}
