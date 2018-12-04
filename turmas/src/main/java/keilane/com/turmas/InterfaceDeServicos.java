package keilane.com.turmas;

import java.util.List;

import keilane.com.turmas.domain.Escola;
import keilane.com.turmas.domain.Turma;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceDeServicos {
    @GET("/turmas")
    Call<List<Turma>> getTurmas();

    @GET("/escola")
    Call<List<Escola>> getEscolas();
}
