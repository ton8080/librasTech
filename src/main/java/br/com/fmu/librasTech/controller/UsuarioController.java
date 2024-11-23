package br.com.fmu.librasTech.controller;

import br.com.fmu.librasTech.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private ConcurrentHashMap<Long, UserDTO> usuariosEmFila = new ConcurrentHashMap<>();
    private Long orderId;

    @PostMapping("/usuarioEmFila")
    public ResponseEntity<UserDTO> adicionarUsuarioEmFila(@RequestParam String nome, @RequestParam boolean buscandoAtendimento) {
        UserDTO usuario = new UserDTO(nome, buscandoAtendimento);
        this.setUsuarioFila(usuario);
        System.out.println(usuario); // Debug: Ver o conteúdo do objeto
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/removeUsuarioEmFila")
    public ResponseEntity removerUsuarioEmFila(@RequestParam Long orderId) {
        try {
            usuariosEmFila.remove(orderId);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }

    }

    public void setUsuarioFila(UserDTO usuario){

        if (usuariosEmFila.size() == 0){
            usuario.setOrdemId(1L);
            usuariosEmFila.put(usuario.getOrdemId(), usuario);
            orderId = 1L;
        } else {
            orderId++;
            usuario.setOrdemId(orderId);
            usuariosEmFila.put(usuario.getOrdemId(), usuario);
        }

    }
}
