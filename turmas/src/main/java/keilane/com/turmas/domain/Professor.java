package keilane.com.turmas.domain;

import com.owlplan.domain.Turma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date nascimento;
    private String sexo;

    private Usuario usuario;

    private List<Turma> turmas = new ArrayList<>();

    public Professor() {
    }

    public Professor(Integer id, Date nascimento, String sexo, Usuario usuario) {
        super();
        this.id = id;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}