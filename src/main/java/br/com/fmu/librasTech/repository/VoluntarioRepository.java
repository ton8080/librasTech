package br.com.fmu.librasTech.repository;

import br.com.fmu.librasTech.entity.VoluntarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoluntarioRepository extends JpaRepository<VoluntarioEntity, Long> {
    Optional<VoluntarioEntity> findByEmailAndSenha(String email, String senha);

    List<VoluntarioEntity> findByDisponivelTrue();

    Optional<VoluntarioEntity> findByEmail(String email);

    Optional<VoluntarioEntity> findFirstByDisponivelTrue();
}
