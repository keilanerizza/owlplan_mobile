package keilane.com.turmas.domain;

import java.io.Serializable;

import keilane.com.turmas.domain.Professor;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String apelido;
    private String serie;
    private String periodo;

    private Integer professorId;

    private Integer escolaId;

    public Turma() {
    }

    public Turma(Integer id, String apelido, String serie, String periodo, Integer professor, Integer escola) {
        super();
        this.id = id;
        this.apelido = apelido;
        this.serie = serie;
        this.periodo = periodo;
        this.professorId = professor;
        this.escolaId = escola;
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

    public Integer getProfessor() {
        return professorId;
    }

    public void setProfessor(Integer professor) {
        this.professorId = professor;
    }

    public Integer getEscola() {
        return escolaId;
    }

    public void setEscola(Integer escola) {
        this.escolaId = escola;
    }
}
