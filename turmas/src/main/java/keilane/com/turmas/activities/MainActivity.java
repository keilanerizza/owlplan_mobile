package keilane.com.turmas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import keilane.com.turmas.R;
import keilane.com.turmas.domain.Turma;
import keilane.com.turmas.domain.TurmaAdapter;
import keilane.com.turmas.services.InterfaceDeServicos;
import keilane.com.turmas.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.list);
        imprimeLista();
    }

    private void imprimeLista() {

        InterfaceDeServicos services = RetrofitService.getServico();

        Call<List<Turma>> call = services.getTurmas();

        call.enqueue(new Callback<List<Turma>>() {
            public void onResponse(Call<List<Turma>> call, Response<List<Turma>> response) {
                List<Turma> listaTurmas = response.body();
                lista.setAdapter(new TurmaAdapter(MainActivity.this, listaTurmas));
            }

            public void onFailure(Call<List<Turma>> call, Throwable t) {
                Log.i("debug:", t.getMessage());
            }
        });
    }
}