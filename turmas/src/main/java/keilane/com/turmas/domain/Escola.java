package keilane.com.turmas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Escola implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String telefone;

    private Usuario usuario;

    private List<Turma> turmas = new ArrayList<>();

    public Escola() {
    }

    public Escola(Integer id, String telefone, Usuario usuario) {
        super();
        this.id = id;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

