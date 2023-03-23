package com.example.aula3.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.aula3.model.Usuario;

@Repository
public class UsuarioRepository {

    private static String SELECT_ALL = "SELECT * FROM USUARIO";
    private static String INSERT = "insert into usuario (nome, email, senha) values (?,?,?)";
    private static String DELETE = "DELETE FROM USUARIO WHERE id = ?";
    private static String UPDATE = "UPDATE USUARIO SET (NOME = ?, EMAIL = ?, SENHA = ?) WHERE id = ?";
    private static String AUTENTICAR = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Usuario> obterTodos(int id){
        return jdbcTemplate.query(SELECT_ALL, Usuario.getRowMapper());
    }

    public void deletar (int id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public Usuario deletar (Usuario usuario){
        jdbcTemplate.update(UPDATE, new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha()});
        return usuario;
    }

    public Usuario inserir (Usuario usuario){
        jdbcTemplate.update(INSERT,new Object[]{usuario.getNome(), usuario.getEmail(), usuario.getSenha()});
        return usuario;
    }

    public boolean autenticar (String email, String senha){
        List<Usuario> lista = jdbcTemplate.query(AUTENTICAR, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Usuario(rs.getInt("id"), rs.getString("nome"), 
                    rs.getString("email"), rs.getString("senha"));
            }
        }, new Object[] { email, senha });
        return lista.size() > 0;
    }

}

