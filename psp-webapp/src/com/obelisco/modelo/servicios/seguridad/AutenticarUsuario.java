package com.obelisco.modelo.servicios.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.IUserService;

@Service("autenticarUsuario")
public class AutenticarUsuario implements UserDetailsService{
	
	@Autowired
	private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String s)
			throws UsernameNotFoundException, DataAccessException {
		Usuario user = userService.getUserByLoginName(s);
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();		
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_ADMIN");
		auths.add(auth);
		
		if (user == null) {
			throw new UsernameNotFoundException("El usuario: " + s
					+ " no existe");
		}
		User acegiUser = new User(user.getLogin(), user.getPassword(), auths);
		return acegiUser;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	

}
