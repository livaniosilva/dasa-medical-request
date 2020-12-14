package br.org.livanio.parameter;

import br.org.livanio.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteParameter {
    private String nome;
    private String nomeMae;
    private String sexo;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Contato contato;
    private Documento documento;

    public Paciente toModel(){
        Paciente paciente = new Paciente();

        /** PREEHCIMENTO DOS DADOS DO PACIENTE NO PEDIDO*/
        paciente.setNome(this.nome);
        paciente.setDataNascimento(this.dataNascimento);
        paciente.setSexo(this.sexo);
        paciente.setNomeMae(this.nomeMae);

        /** PREEHCIMENTO DO CONTATO DO PACIENTE NO PEDIDO*/
        paciente.setContato(this.contato);

        /** PREEHCIMENTO DO ENDEREÇO DO PACIENTE NO PEDIDO*/
        paciente.setEndereco(this.endereco);

        paciente.setDocumento(this.documento);
        return paciente;
    }
}
