package com.example.aluno.condutascancermama.activity.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.condutascancermama.R;
import com.example.aluno.condutascancermama.activity.adapter.ExibirExamesAdapter;
import com.example.aluno.condutascancermama.activity.adapter.ExibirMedicamentoAdapter;
import com.example.aluno.condutascancermama.activity.helper.SlidingTabLayout;

public class MeusMedicamentos extends AppCompatActivity {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_medicamentos);

        setTitle("Meus Medicamentos");

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slid_tabMedicamento);
        viewPager = (ViewPager) findViewById(R.id.vp_paginaMedicamento);

        //Configurar sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));

        ExibirMedicamentoAdapter tabAdapter = new ExibirMedicamentoAdapter( getSupportFragmentManager() );
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
    }
}
