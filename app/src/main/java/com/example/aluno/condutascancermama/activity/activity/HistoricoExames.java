package com.example.aluno.condutascancermama.activity.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.adapter.ExibirExamesAdapter;
import com.example.aluno.condutascancermama.activity.helper.SlidingTabLayout;

public class HistoricoExames extends AppCompatActivity {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_exames);

        setTitle("Historico de Exames");

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slid_tabExame);
        viewPager = (ViewPager) findViewById(R.id.vp_paginaExame);

        //Configurar sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));

        ExibirExamesAdapter tabAdapter = new ExibirExamesAdapter( getSupportFragmentManager() );
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
    }
}
