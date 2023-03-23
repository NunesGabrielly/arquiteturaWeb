package com.example.aula3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula3.model.Usuario;
import com.example.aula3.repository.UsuarioRepository;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository _UsuarioRepository;

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public List<Usuario> GET() {
        return _UsuarioRepository.obterTodos(0);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public Usuario Post(@Validated @RequestBody Usuario usuario){
        return _UsuarioRepository.inserir(usuario);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> Put(@PathVariable(value = "id") int id, @RequestBody Usuario newUsuario){
        List<Usuario> antUsuario = _UsuarioRepository.obterTodos(id);
        if(antUsuario.isEmpty()){
            Usuario usuario = antUsuario.get(id);
            usuario.setNome(newUsuario.getNome());
            _UsuarioRepository.inserir(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") int id){
        List<Usuario> usuario = _UsuarioRepository.obterTodos(id);
        if(usuario.isEmpty()){
            _UsuarioRepository.deletar(usuario.get(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
