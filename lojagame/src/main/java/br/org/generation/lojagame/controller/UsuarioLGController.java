package br.org.generation.lojagame.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagame.model.UsuarioLG;
import br.org.generation.lojagame.model.UsuarioLGLogin;
import br.org.generation.lojagame.repository.UsuarioLGRepository;
import br.org.generation.lojagame.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioLGController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioLGRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<UsuarioLG>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioLG> cadastrarUsuario(@RequestBody UsuarioLG usuario){
		return usuarioService.cadastrarUsuario(usuario)
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLGLogin> logarUsuario(@Valid @RequestBody Optional<UsuarioLGLogin> user){
		return usuarioService.autenticarUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioLG> putUsuario(@Valid @RequestBody UsuarioLG usuario){ 
		
		return usuarioService.atualizarUsuario(usuario)             
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()); 
	}
}
