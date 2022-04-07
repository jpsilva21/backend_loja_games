package br.org.generation.lojagame.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.org.generation.lojagame.model.UsuarioLG;

public interface UsuarioLGRepository extends JpaRepository<UsuarioLG, Long> {
	
public Optional<UsuarioLG> findByUsuario(String usuario);
}
