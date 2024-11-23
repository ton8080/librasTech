package br.com.fmu.librasTech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long ordemId;
    private String nome;
    private boolean buscandoAtendimento;

    public UserDTO(String nome, boolean buscandoAtendimento) {
        this.nome = nome;
        this.buscandoAtendimento = buscandoAtendimento;
    }
}
