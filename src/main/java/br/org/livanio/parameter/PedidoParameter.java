package br.org.livanio.parameter;

import br.org.livanio.entity.Exame;
import br.org.livanio.entity.Medico;
import br.org.livanio.entity.Paciente;
import br.org.livanio.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoParameter implements Serializable {
    private Exame exame;
    private Paciente paciente;
    private Medico medico;



    public Pedido toModel(){
        Pedido pedido = new Pedido();
        pedido.setDataValidade(calculaDataValidade());

        pedido.setMedico(this.medico);

        pedido.setPaciente(this.paciente);

        pedido.setExame(this.exame);

        return pedido;
    }

    public LocalDate calculaDataValidade(){

        return LocalDate.now().plusDays(5);
    }
}
