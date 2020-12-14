package br.org.livanio.controller;

import br.org.livanio.entity.Pedido;
import br.org.livanio.parameter.PedidoParameter;
import br.org.livanio.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("dasa")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;


    @ApiOperation(value="Cria novo pedido médico", tags = {"Pedido"})
    @PostMapping("/pedido")
    public ResponseEntity<Pedido> save(@Valid @RequestBody PedidoParameter parameter) {
        Pedido pedido = pedidoService.save(parameter.toModel());
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value="Lista os pedidos médicos realizados", tags = {"Pedido"})
    @GetMapping("/pedido")
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> list = pedidoService.findAll();
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value="Recupera pedidos por id", tags = "Pedido")
    @ResponseBody
    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable(value = "id") Long id) throws Exception {
        Pedido pedido = pedidoService.findById(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "Exclui medido", tags = "Pedido")
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boolean pedidoToDelete = pedidoService.delete(id);
        if (pedidoToDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Aatualiza dados do pedido", tags = "Pedido")
    @PutMapping("/pedido/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @Valid @RequestBody PedidoParameter parameter) {
        boolean pedidoToUpdate = pedidoService.update(id, parameter.toModel());
        if (pedidoToUpdate) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
