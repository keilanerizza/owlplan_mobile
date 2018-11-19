package keilane.com.turmas.domain;

import android.content.Context;
import android.widget.BaseAdapter;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class TurmaAdapter extends BaseAdapter {
    Context ctx;
    List<Turma> lista;

    public TurmaAdapter(Context ctx, List<Turma> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public void notifyDataSetChanged() {
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
}