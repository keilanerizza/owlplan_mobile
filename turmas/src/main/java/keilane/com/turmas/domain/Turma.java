package keilane.com.turmas.domain;

import java.io.Serializable;

import keilane.com.turmas.domain.Professor;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String apelido;
    private String serie;
    private String periodo;

    private Professor professor;

    private Escola escola;

    public Turma() {
    }

    public Turma(Integer id, String apelido, String serie, String periodo, Professor professor, Escola escola) {
        super();
        this.id = id;
        this.apelido = apelido;
        this.serie = serie;
        this.periodo = periodo;
        this.professor = professor;
        this.escola = escola;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
