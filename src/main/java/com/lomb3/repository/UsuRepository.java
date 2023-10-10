package com.lomb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lomb3.entities.Usuario;

public interface UsuRepository extends JpaRepository<Usuario, Long> {
	

}
