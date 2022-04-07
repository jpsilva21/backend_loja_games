package br.org.generation.lojagame.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.lojagame.model.UsuarioLG;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	//construtor CHEIO
	public UserDetailsImpl(UsuarioLG usuario) {
		/*this.*/ userName = usuario.getUsuario();
		/*this.*/ password = usuario.getSenha();
	}
	
	//construtor VAZIO (para testar)
	public UserDetailsImpl() {}
		
		@Override
		public String getPassword() {
			return password;
		}
		
		@Override
		public String getUsername() {
			return userName;
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override //metodos padrão
		public boolean isAccountNonExpired() {
			return true;
		}
		
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

}
