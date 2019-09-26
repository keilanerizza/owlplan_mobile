package keilane.com.eventos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import keilane.com.servicos.services.RetrofitService;
import keilane.com.eventos.InterfaceDeServicos;
import keilane.com.turmas.domain.Turma;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import keilane.com.eventos.domain.Evento;

public class MainActivity extends AppCompatActivity {

    InterfaceDeServicos services = RetrofitService.criaRetrofit().create(InterfaceDeServicos.class);

    TextView descricao;
    TextView data;
    Bundle extras;
    Evento evento;
    Date data_formatada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_evento);
        extras = getIntent().getExtras();

        descricao = findViewById(R.id.descricao_evento);
        data = findViewById(R.id.data_evento);

        if(extras != null) {
            String date = extras.getString("data_selecionada");
            data.setText(date);
        }
    }

    public void criarEvento(View view) {

        String data = ((TextView) findViewById(R.id.data_evento)).getText().toString();
        String descricao = ((TextView) findViewById(R.id.descricao_evento)).getText().toString();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            data_formatada = sdf.parse(data);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        evento = new Evento(null, 3, descricao, data_formatada);

        Call<Evento> call = null;
        String msg = "";

        Button btn = findViewById(R.id.criar_turma);

        if(btn.getText().equals("CRIAR")) {
            call = services.criaEvento(evento);
            msg = "Turma criada";
        } /*else if (btn.getText().equals("EDITAR")) {
            turma.setId(idExtra);
            call = services.putTurma(turma, turma.getId());
            msg = "Turma editada";
        }*/

        final String finalMsg = msg;
        call.enqueue(new Callback<Evento>() {

            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                Toast.makeText(getApplicationContext(), finalMsg,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro",Toast.LENGTH_LONG).show();
                Log.e("Erro: ","Metodo delete da classe MainActivity: "+t.getMessage());
            }
        });
    }

    public void limparForm(View view) {
        descricao.setText("");
    }

    public void cancelarForm(View view) {
        finish();
    }
}
