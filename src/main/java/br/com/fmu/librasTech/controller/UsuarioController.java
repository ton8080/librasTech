package br.com.fmu.librasTech.controller;

import br.com.fmu.librasTech.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private ConcurrentHashMap<Long, UserDTO> usuariosEmFila = new ConcurrentHashMap<>();

    @PostMapping("/usuarioEmFila")
    public String atualizarUsuarioEmFila(@RequestParam Long ordemId, @RequestParam String nome, @RequestParam boolean buscandoAtendimento) {
        UserDTO usuario = new UserDTO(ordemId, nome, buscandoAtendimento);
        if (buscandoAtendimento) {
            usuariosEmFila.put(ordemId, usuario);
        } else {
            usuariosEmFila.remove(ordemId);
        }
        return "Status do usuário atualizado!";
    }
}
