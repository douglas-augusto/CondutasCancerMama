package com.example.aluno.condutascancermama.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.model.Exame;

import java.util.ArrayList;

public class ExameAdapter extends ArrayAdapter<Exame> {

    private ArrayList<Exame> exames;
    private Context context;

    public ExameAdapter(@NonNull Context c, @NonNull ArrayList<Exame> objects) {
        super(c,0, objects);
        this.exames = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if(exames != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_exames, parent, false);

            TextView nomeExame = (TextView) view.findViewById(R.id.tv_nomeExame);
            TextView infoExame = (TextView) view.findViewById(R.id.tv_infoExame);

            Exame exame = exames.get(position);
            nomeExame.setText(exame.getNome());
            infoExame.setText("Data: "+exame.getData() + "  Resultado: "+exame.getResultado());

        }

        return view;

    }

}
