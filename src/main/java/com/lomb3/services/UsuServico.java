package com.lomb3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lomb3.entities.Usuario;
import com.lomb3.repository.UsuRepository;

@Service
public class UsuServico {
	
	private final UsuRepository usuRepository;
	
	@Autowired
	public UsuServico (UsuRepository usuRepository) {
		this.usuRepository = usuRepository;
	}
	public List<Usuario> buscaTodosUsuarios(){
		return usuRepository.findAll();
	}
	public Usuario buscaUsuarioId(Long id) {
		Optional <Usuario> Usuario = usuRepository.findById(id);
		return Usuario.orElse(null);
	}
	public Usuario alterarUsuario (Long id, Usuario alterarUsuario) {
		Optional <Usuario> existeUsuario = usuRepository.findById(id);
		if(existeUsuario.isPresent()) {
			alterarUsuario.setId(id);
			return usuRepository.save(alterarUsuario);
		}
		return null;
	}
	public boolean apagarUsuario(Long id) {
		Optional <Usuario> existeUsuario = usuRepository.findById(id);
		if(existeUsuario.isPresent()) {
		usuRepository.deleteById(id);
		return true;
	}
	return false;
	}
	public Usuario salvaUsuario(Usuario usuario) {
		return usuRepository.save(usuario);
	}
}
