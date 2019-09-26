package keilane.com.eventos;

import java.util.List;

import keilane.com.eventos.domain.Evento;
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
    @GET("/eventos")
    Call<List<Evento>> getTurmas();

    @POST("/eventos")
    Call<Evento> criaEvento(@Body Evento evento);

    @PUT("/eventos/{id}")
    Call<Evento> putEvento(@Body Evento evento, @Path("id") Integer id);

    @DELETE("/evento/{id}")
    Call<Evento> deleteEvento(@Path("id") String id);
}
