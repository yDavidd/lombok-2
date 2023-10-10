package com.lomb3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lomb3.entities.Usuario;
import com.lomb3.services.UsuServico;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuario")
public class UsuController {
	
	private final UsuServico usuServico;
	
	@Autowired
	public UsuController(UsuServico usuServico) {
		this.usuServico = usuServico;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){
		Usuario usuario = usuServico.buscaUsuarioId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl(){
		List<Usuario> Usuarios = usuServico.buscaTodosUsuarios();
		return ResponseEntity.ok(Usuarios);
	}
	@PostMapping("/")
	public ResponseEntity<Usuario> salvaUsuariosControl (@RequestBody @Valid Usuario usuario){
		Usuario salvaUsuario = usuServico.salvaUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alteraUsuarioControl (@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario alteraUsuario = usuServico.alterarUsuario(id, usuario);
		if(alteraUsuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> apagaUsuarioControl (@PathVariable Long id){
		boolean apagar = usuServico.apagarUsuario(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
 