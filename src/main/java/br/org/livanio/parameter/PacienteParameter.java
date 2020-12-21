package br.org.livanio.parameter;

import br.org.livanio.entity.Contato;
import br.org.livanio.entity.Documento;
import br.org.livanio.entity.Endereco;
import br.org.livanio.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

        paciente.setNome(this.nome);
        paciente.setDataNascimento(this.dataNascimento);
        paciente.setSexo(this.sexo);
        paciente.setNomeMae(this.nomeMae);

        paciente.setContato(this.contato);

        paciente.setEndereco(this.endereco);

        paciente.setDocumento(this.documento);
        return paciente;
    }
}
