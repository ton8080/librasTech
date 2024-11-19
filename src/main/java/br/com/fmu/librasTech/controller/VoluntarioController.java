package br.com.fmu.librasTech.controller;

import br.com.fmu.librasTech.entity.VoluntarioEntity;
import br.com.fmu.librasTech.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @PostMapping("/cadastro")
    public VoluntarioEntity cadastrarVoluntario(@RequestBody VoluntarioEntity voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    @PostMapping("/login")
    public VoluntarioEntity loginVoluntario(@RequestParam String email, @RequestParam String senha) {
        return voluntarioRepository.findByEmailAndSenha(email, senha).orElse(null);
    }

    @PostMapping("/voluntarioEmFila")
    public VoluntarioEntity atualizarDisponibilidade(@RequestParam Long id, @RequestParam boolean disponivel) {
        VoluntarioEntity voluntario = voluntarioRepository.findById(id).orElseThrow();
        voluntario.setDisponivel(disponivel);
        return voluntarioRepository.save(voluntario);
    }

    @GetMapping("/voluntariosDisponiveis")
    public List<VoluntarioEntity> listarVoluntariosDisponiveis() {
        return voluntarioRepository.findByDisponivelTrue();
    }
}
