package com.edusync.desafio_6.repositories.security;

import com.edusync.desafio_6.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByName(String username);
}
