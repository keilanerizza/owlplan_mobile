package keilane.com.turmas;

import java.util.List;

import keilane.com.turmas.domain.Escola;
import keilane.com.turmas.domain.Turma;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceDeServicos {
    @GET("/turmas")
    Call<List<Turma>> getTurmas();

    @GET("/escola")
    Call<List<Escola>> getEscolas();

    @POST("/turmas")
    Call<Turma> criaTurma(@Body Turma turma);
}
