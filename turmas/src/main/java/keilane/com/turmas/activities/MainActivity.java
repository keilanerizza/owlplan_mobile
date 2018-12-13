package keilane.com.turmas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_turma);
        lista = findViewById(R.id.lista);
        //registerForContextMenu(lista);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_turma, menu);
        menu.setHeaderTitle("Select The Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.aaa){
            Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.bbb){
            Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    public void visualizarTurma(View view) {
        startActivity(new Intent(this, keilane.com.planejamentos.MainActivity.class));
    }
}
