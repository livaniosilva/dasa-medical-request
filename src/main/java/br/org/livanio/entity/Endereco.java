package br.org.livanio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco implements Serializable {
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String cep;
    private String uf;
}
