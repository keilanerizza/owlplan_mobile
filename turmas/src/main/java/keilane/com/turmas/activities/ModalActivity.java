package keilane.com.turmas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringReader;
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

    List<Escola> listaNomeEscolas;
    int idEscolaSelecionada;
    Spinner spinner;
    TextView apelido;
    TextView serie;
    Button btn;
    Turma turma;
    Integer idExtra;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_turma);
        extras = getIntent().getExtras();

        apelido = findViewById(R.id.apelido_turma);
        serie = findViewById(R.id.serie_turma);
        btn = findViewById(R.id.criar_turma);

        listaNomeEscolas = new ArrayList<Escola>();

        imprimeListaEscolas();

        if (extras != null) {
            String value = extras.getString("key");
            if (value.equals("EDITAR")) {
                editarTurma();
            }
        }
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
                Log.i("teste", t.getMessage());
            }
        });


    }

    private void preencheSpinner() {
        spinner = findViewById(R.id.spinner);
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
        //String escola = spinner.getSelectedItem().toString();
        idEscolaSelecionada = listaNomeEscolas.get(position).getId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void criarTurma(View view) {
        String apelido = ((TextView) findViewById(R.id.apelido_turma)).getText().toString();
        String serie = ((TextView) findViewById(R.id.serie_turma)).getText().toString();
        RadioGroup rg = findViewById(R.id.turno_turma);
        int radioButtonID = rg.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) rg.findViewById(radioButtonID);
        String periodo = radioButton.getText().toString();
        
        turma = new Turma(null, apelido, serie, periodo, 5, idEscolaSelecionada);
        
        Call<Turma> call = null;
        String msg = "";
        
        if(btn.getText().equals("CRIAR")) {
            call = services.criaTurma(turma);
            msg = "Turma criada";
        } else if (btn.getText().equals("EDITAR")) {
            turma.setId(idExtra);
            call = services.putTurma(turma, turma.getId());
            msg = "Turma editada";
        }

        final String finalMsg = msg;
        call.enqueue(new Callback<Turma>() {

            @Override
            public void onResponse(Call<Turma> call, Response<Turma> response) {
                Toast.makeText(getApplicationContext(), finalMsg,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Turma> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro",Toast.LENGTH_LONG).show();
                Log.e("Erro: ","Metodo delete da classe MainActivity: "+t.getMessage());
            }
        });

    }

    public void editarTurma() {
        btn.setText("EDITAR");

        String id = extras.getString("keyId");
        idExtra = Integer.parseInt(id);

        String idProfessor = extras.getString("keyProfessor");
        Integer professorExtra = Integer.parseInt(idProfessor);

        String idEscola = extras.getString("keyEscola");
        Integer escolaExtra = Integer.parseInt(idEscola);

        String apelidoExtra = extras.getString("keyApelido");
        apelido.setText(apelidoExtra);

        String serieExtra = extras.getString("keySerie");
        serie.setText(serieExtra);

        String periodoExtra = extras.getString("keyPeriodo").toUpperCase();
        RadioButton m = findViewById(R.id.turno_matutino);
        RadioButton v = findViewById(R.id.turno_vespertino);
        RadioButton n = findViewById(R.id.turno_noturno);

        if (periodoExtra.equals(m.getText())) {
            m.setChecked(true);
        } else if (periodoExtra.equals(v.getText())) {
            v.setChecked(true);
        } else if (periodoExtra.equals(n.getText())) {
            n.setChecked(true);
        }
    }

    public void limparForm(View view) {
        apelido.setText("");
        serie.setText("");
    }

    public void cancelarForm(View view) {
        finish();
    }
}
