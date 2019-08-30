package keilane.com.turmas.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import keilane.com.turmas.util.ModalDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    List<Turma> listaTurmas;
    int posicao;

    InterfaceDeServicos services = RetrofitService.criaRetrofit().create(InterfaceDeServicos.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);
        lista = findViewById(R.id.lista);
        //registerForContextMenu(lista);
        try{
            imprimeLista();
        } catch (Exception e){
            Log.e("api", String.valueOf(e.getCause()));
        }

    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    private void imprimeLista() {

        Call<List<Turma>> call = services.getTurmas();
        call.enqueue(new Callback<List<Turma>>() {
            public void onResponse(Call<List<Turma>> call, Response<List<Turma>> response) {
                listaTurmas = response.body();
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
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.editar_turma){
            Intent i = new Intent(this, ModalActivity.class);
            i.putExtra("key", "EDITAR");
            i.putExtra("keyId", listaTurmas.get(posicao).getId().toString());
            i.putExtra("keyProfessor", listaTurmas.get(posicao).getProfessor().toString());
            i.putExtra("keyApelido", listaTurmas.get(posicao).getApelido());
            i.putExtra("keySerie", listaTurmas.get(posicao).getSerie());
            i.putExtra("keyPeriodo", listaTurmas.get(posicao).getPeriodo());
            i.putExtra("keyEscola", listaTurmas.get(posicao).getEscola().toString());

            startActivity(i);
        }
        else if(item.getItemId()==R.id.excluir_turma){

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("teste");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            ModalDialog dialog = new ModalDialog();
            dialog.setActivity(this);
            dialog.setMensagem("Deseja excluir esta turma?");
            dialog.setId(listaTurmas.get(posicao).getId().toString());
            dialog.show(ft,"teste");

        }else{
            return false;
        }
        return true;
    }

    public void deleteTurma(String id) {

        Call<Turma> call = services.deleteTurma(id);

        call.enqueue(new Callback<Turma>() {

            @Override
            public void onResponse(Call<Turma> call, Response<Turma> response) {
                Toast.makeText(getApplicationContext(),"Turma deletada",Toast.LENGTH_LONG).show();
                imprimeLista();
            }

            @Override
            public void onFailure(Call<Turma> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro",Toast.LENGTH_LONG).show();
                Log.e("Erro: ","Metodo delete da classe MainActivity: "+t.getMessage());
            }
        });
    }

    public void visualizarTurma(View view) {
        startActivity(new Intent(this, keilane.com.planejamentos.MainActivity.class));
    }


}
