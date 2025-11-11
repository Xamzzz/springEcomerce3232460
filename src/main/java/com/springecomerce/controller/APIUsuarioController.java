package com.springecomerce.controller;

import java.util.List;
import java.util.Optional;

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

import com.springecomerce.model.Producto;
import com.springecomerce.model.Usuario;
import com.springecomerce.services.IProductoService;
import com.springecomerce.services.IUsuarioService;
import com.springecomerce.services.UsusarioServiceImplement;

@RestController
@RequestMapping("/apiusuario")
public class APIUsuarioController {
	
	private final UsusarioServiceImplement usuarioServiceImplement;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	APIUsuarioController(UsusarioServiceImplement ususarioServiceImplement){
		this.usuarioServiceImplement = ususarioServiceImplement;
	}
		
	@GetMapping("/list")
	public List<Usuario> getAllUsuarios(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
		Optional<Usuario> usuario = usuarioService.get(id);
		return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
		Usuario n = usuarioService.findById(1).get();
		usuario.setRol("user");
		Usuario savedUsuario = usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails ){
		Optional<Usuario> usuario = usuarioService.get(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Usuario existingUsuario = usuario.get();
		existingUsuario.setNombre(usuarioDetails.getNombre());
		existingUsuario.setDireccion(usuarioDetails.getDireccion());
		existingUsuario.setEmail(usuarioDetails.getEmail());
		existingUsuario.setTelefono(usuarioDetails.getTelefono());
		existingUsuario.setPassword(usuarioDetails.getPassword());
		usuarioService.update(existingUsuario);
		return ResponseEntity.ok(existingUsuario);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
		Optional<Usuario> usuario = usuarioService.get(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
