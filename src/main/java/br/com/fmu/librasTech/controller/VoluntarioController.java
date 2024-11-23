package br.com.fmu.librasTech.controller;

import br.com.fmu.librasTech.dto.CallResponseDTO;
import br.com.fmu.librasTech.entity.VoluntarioEntity;
import br.com.fmu.librasTech.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    private String url = "https://meet.google.com/jhe-ahhv-mwe";

    @PostMapping("/cadastro")
    public ResponseEntity<VoluntarioEntity> cadastrarVoluntario(@RequestBody VoluntarioEntity voluntario) {
        return ResponseEntity.ok(voluntarioRepository.save(voluntario));
    }

    @PostMapping("/login")
    public ResponseEntity<Long> loginVoluntario(@RequestParam String email, @RequestParam String senha) {
        Optional<VoluntarioEntity> voluntario =  voluntarioRepository.findByEmail(email);
        if (voluntario.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            voluntario = voluntarioRepository.findByEmailAndSenha(email, senha);
            if (voluntario.isEmpty()){
                return ResponseEntity.internalServerError().build();
            }
        }
       return ResponseEntity.ok(voluntario.get().getId());
    }

    @PostMapping("/voluntarioEmFila")
    public ResponseEntity<CallResponseDTO> atualizarDisponibilidade(@RequestParam Long id, @RequestParam boolean disponivel) {
        VoluntarioEntity voluntario = voluntarioRepository.findById(id).orElseThrow();
        CallResponseDTO responseDTO = new CallResponseDTO(voluntario.getId(), url);
        voluntario.setDisponivel(disponivel);
        voluntarioRepository.save(voluntario);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/voluntariosDisponiveis")
    public ResponseEntity<CallResponseDTO> listarVoluntariosDisponiveis() {
        Optional<VoluntarioEntity> voluntarioEntity = voluntarioRepository.findFirstByDisponivelTrue();

        if (voluntarioEntity.isPresent()) {
            // A URL pode ser alterada para a URL dinâmica, se necessário
            CallResponseDTO responseDTO = new CallResponseDTO(voluntarioEntity.get().getId(), url);
            voluntarioEntity.get().setDisponivel(false);
            voluntarioRepository.save(voluntarioEntity.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return null;
        }
    }

    @GetMapping("/voluntariosDisponivel/{id}")
    public ResponseEntity<Boolean> verificarDisponibilidadeById(@PathVariable Long id){
        Optional<VoluntarioEntity> voluntarioEntity = voluntarioRepository.findById(id);
        if (voluntarioEntity.isPresent()){
            if (voluntarioEntity.get().isDisponivel()){
                return ResponseEntity.ok(Boolean.TRUE);
            } else {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        } else {
          return   ResponseEntity.internalServerError().build();
        }

    }
}
