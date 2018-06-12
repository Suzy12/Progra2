package com.example.poo.progra2.View.ActivitiesEncargado;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.xml.PeriodoDAO;

public class RegistrarPeriodoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    String[] semestre = {"1", "2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_periodo);
        final Spinner semestreSpinner = (Spinner) findViewById(R.id.editText6);
        semestreSpinner.setOnItemSelectedListener(this);
        ArrayAdapter spin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, semestre);
        semestreSpinner.setAdapter(spin);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (item.getItemId()) {
                            case R.id.nav_inicio:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, EncargadoActivity.class));
                                break;
                            case R.id.nav_empresa:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, RegistrarEmpresaActivity.class));
                                break;
                            case R.id.nav_profC:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, RegistrarProfCursoActivity.class));
                                break;
                            case R.id.nav_practicante:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, RegistrarPracticanteActivity.class));
                                break;
                            case R.id.nav_calendario:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, CrearCalendarioActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(RegistrarPeriodoActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
                    }
                });
        Button button = (Button) findViewById(R.id.button55);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText anoField = findViewById(R.id.editText24);
                String ano = anoField.getText().toString();
                String sem = semestreSpinner.getSelectedItem().toString();
                final PeriodoDAO dao = new PeriodoDAO(getApplicationContext());
                dao.agregarPeriodo(sem, ano);
                Toast.makeText(RegistrarPeriodoActivity.this,"Periodo registrado", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> Av , View v, int position, long id){

    }

    @Override
    public void onNothingSelected(AdapterView<?> aa){

    }
}
