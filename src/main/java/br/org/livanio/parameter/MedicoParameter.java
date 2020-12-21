package br.org.livanio.parameter;

import br.org.livanio.entity.Medico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoParameter {
    private Long conselho;
    private String nome;
    private String tipoCOnselho;
    private String ufConselho;

    public Medico toModel(){
        Medico medico = new Medico();

        medico.setConselho(this.conselho);
        medico.setTipoConselho(this.tipoCOnselho);
        medico.setUfConselho(this.ufConselho);
        medico.setNome(this.nome);
        return medico;
    }
}
