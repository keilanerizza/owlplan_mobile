package keilane.com.turmas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import keilane.com.servicos.services.RetrofitService;
import keilane.com.turmas.InterfaceDeServicos;
import keilane.com.turmas.R;
import keilane.com.turmas.domain.Turma;
import keilane.com.turmas.domain.TurmaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState == null)
            savedInstanceState = new Bundle();
        savedInstanceState.putInt("miolo", R.layout.activity_turma);*/
        setContentView(R.layout.activity_turma);
        lista = findViewById(R.id.lista);
        imprimeLista();
    }

    private void imprimeLista() {

        InterfaceDeServicos services = RetrofitService.criaRetrofit().create(InterfaceDeServicos.class);

        Call<List<Turma>> call = services.getTurmas();
        call.enqueue(new Callback<List<Turma>>() {
            public void onResponse(Call<List<Turma>> call, Response<List<Turma>> response) {
                List<Turma> listaTurmas = response.body();
                lista.setAdapter(new TurmaAdapter(MainActivity.this, listaTurmas));
            }

            public void onFailure(Call<List<Turma>> call, Throwable t) {
                Log.i("lista:", t.getMessage());
            }
        });
    }
}
