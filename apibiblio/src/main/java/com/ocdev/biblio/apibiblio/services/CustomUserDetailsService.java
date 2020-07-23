package com.ocdev.biblio.apibiblio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<Utilisateur> user = utilisateurRepository.findByEmailIgnoreCase(username);
        if (!user.isPresent())
        {
            throw new UsernameNotFoundException("Pas d'utilisateur avec l'identifiant : " + username);
        }
        
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.get().getRole().toString()));
		
		UserDetails result = new User(username, user.get().getPassword(), authorities);
		
		return result;
	}
}
