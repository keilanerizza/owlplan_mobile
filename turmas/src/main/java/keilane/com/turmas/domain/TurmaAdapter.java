package keilane.com.turmas.domain;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import keilane.com.turmas.R;
import keilane.com.turmas.activities.MainActivity;

public class TurmaAdapter extends BaseAdapter {
    private Activity ctx;
    private List<Turma> lista;

    public TurmaAdapter(Activity ctx, List<Turma> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0L;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //1º passo
        Turma turma = lista.get(i);

        //2º passo
        View linha = ctx.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
//        LayoutInflater.from(ctx).inflate(R.layout.list_item, null);



        //3º passo
        final TextView apelido = linha.findViewById(R.id.apelido_turma);
        TextView periodo = linha.findViewById(R.id.turno_turma);

        apelido.setText(turma.getApelido());
        periodo.setText(turma.getPeriodo());

        ImageView more = linha.findViewById(R.id.imageView2);
        ctx.registerForContextMenu(more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity m = (MainActivity)ctx;
                m.setPosicao(i);
                ctx.openContextMenu(view);
            }
        });

        return linha;
    }


}