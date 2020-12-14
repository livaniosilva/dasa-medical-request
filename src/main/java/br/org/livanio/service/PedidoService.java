package br.org.livanio.service;

import br.org.livanio.entity.Pedido;

import java.util.List;

public interface PedidoService {
    public Pedido save(Pedido pedido);
    public List<Pedido> findAll();
    public Pedido findById(Long id);
    public boolean update(Long id, Pedido pedido);
    public boolean delete(Long id);
}
