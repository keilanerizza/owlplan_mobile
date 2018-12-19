package keilane.com.turmas;

import java.util.List;

import keilane.com.turmas.domain.Escola;
import keilane.com.turmas.domain.Turma;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceDeServicos {
    @GET("/turmas")
    Call<List<Turma>> getTurmas();

    @GET("/escola")
    Call<List<Escola>> getEscolas();

    @POST("/turmas")
    Call<Turma> criaTurma(@Body Turma turma);

    @PUT("/turmas/{id}")
    Call<Turma> putTurma(@Body Turma turma, @Path("id") Integer id);

    @DELETE("/turmas/{id}")
    Call<Turma> deleteTurma(@Path("id") String id);
}
