package keilane.com.turmas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import keilane.com.servicos.services.RetrofitService;
import keilane.com.turmas.InterfaceDeServicos;
import keilane.com.turmas.R;
import keilane.com.turmas.domain.Escola;
import keilane.com.turmas.domain.Turma;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    InterfaceDeServicos services = RetrofitService.criaRetrofit().create(InterfaceDeServicos.class);

    private Spinner spinner;
    List<Escola> listaNomeEscolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_turma);
        listaNomeEscolas = new ArrayList<Escola>();
        imprimeListaEscolas();
    }

    private void imprimeListaEscolas() {

        Call<List<Escola>> call = services.getEscolas();
        call.enqueue(new Callback<List<Escola>>() {

            @Override
            public void onResponse(Call<List<Escola>> call, Response<List<Escola>> response) {
                listaNomeEscolas = response.body();
                preencheSpinner();
            }

            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t) {
                Log.i("lista escolas:", t.getMessage());
            }
        });


    }

    private void preencheSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> nomes = new ArrayList<String>();
        for (Escola e : listaNomeEscolas) {
            nomes.add(e.getUsuarioNome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ModalActivity.this, android.R.layout.simple_spinner_dropdown_item, nomes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        String escola = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void criarTurma(View view) {
        String apelido = findViewById(R.id.apelido_turma).toString();
        String serie = findViewById(R.id.serie_turma).toString();
        String turno = findViewById(R.id.turno_turma).toString();

    }
}
