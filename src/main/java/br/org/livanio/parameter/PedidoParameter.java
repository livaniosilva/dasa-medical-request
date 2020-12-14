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
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

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
        /** PREEHCIMENTO DATA DE VALIDADE */
        pedido.setDataValidade(calculaDataValidade());

        /** PREEHCIMENTO DOS DADOS DO MÉDICO NO PEDIDO*/
        pedido.setMedico(this.medico);

        /** PREEHCIMENTO DOS DADOS DO PACIENTE NO PEDIDO*/
        pedido.setPaciente(this.paciente);

        /** PREEHCIMENTO DOS DADOS DO  EXAME DO PACIENTE NO PEDIDO*/
        pedido.setExame(this.exame);

        return pedido;
    }

    public LocalDate calculaDataValidade(){

        LocalDate dataValidade = LocalDate.now().plusDays(5);
        return dataValidade;
    }
}
