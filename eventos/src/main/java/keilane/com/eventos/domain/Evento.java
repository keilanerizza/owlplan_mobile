package keilane.com.eventos.domain;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer usuarioId;
    private String descricao;
    private Date data;

    public Evento(){}

    public Evento(Integer id, Integer usuarioId, String descricao, Date data){
        super();
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
