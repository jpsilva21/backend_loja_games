package br.org.generation.lojagame.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.lojagame.model.UsuarioLG;
import br.org.generation.lojagame.model.UsuarioLGLogin;
import br.org.generation.lojagame.repository.UsuarioLGRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioLGRepository usuarioRepository;
	
	
public Optional<UsuarioLG>cadastrarUsuario(UsuarioLG usuario) {
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}//essa chave ñ é necessária, só p n confundir
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
	
		return Optional.of(usuarioRepository.save(usuario));
	}
	

	public Optional<UsuarioLGLogin> autenticarUsuario(Optional<UsuarioLGLogin> usuarioLogin){
	
		Optional<UsuarioLG> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if(usuario.isPresent()) {
			if(compararSenha(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(geradorBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
			
				return usuarioLogin;
			}
		}
		return Optional.empty();
	
	}



	private boolean compararSenha(String senhaDigitada, String senhaDoBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaDoBanco);
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private String geradorBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));

		return "Basic " + new String(tokenBase64);
	}

	public Optional<UsuarioLG> atualizarUsuario (UsuarioLG usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<UsuarioLG> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if ((buscaUsuario.isPresent())&& (buscaUsuario.get().getId() != usuario.getId()))
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario existente",null);

		usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.of(usuarioRepository.save(usuario));
			}
		return Optional.empty();
		}

}
