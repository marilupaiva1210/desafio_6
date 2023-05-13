package com.edusync.desafio_6.services.security;

import com.edusync.desafio_6.repositories.security.UsuarioRepository;
import com.edusync.desafio_6.users.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByName(username);
    }

    public boolean userExists(String username) {
        Usuario usu = usuarioRepository.findByName(username);
        return (usu != null);
    }

    public void createAdminUser() {
        Usuario usuAdm = new Usuario();
        usuAdm.setName("admin");
        usuAdm.setPassword(passwordEncoder.encode("1210"));

        usuarioRepository.save(usuAdm);
    }
}
