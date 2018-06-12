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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.logica.Profesor;
import com.example.poo.progra2.xml.ProfesorDAO;

public class RegistrarProfCursoActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_prof_curso);
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
                                startActivity(new Intent(RegistrarProfCursoActivity.this, EncargadoActivity.class));
                                break;
                            case R.id.nav_empresa:
                                startActivity(new Intent(RegistrarProfCursoActivity.this, RegistrarEmpresaActivity.class));
                                break;
                            case R.id.nav_periodo:
                                startActivity(new Intent(RegistrarProfCursoActivity.this, RegistrarPeriodoActivity.class));
                                break;
                            case R.id.nav_practicante:
                                startActivity(new Intent(RegistrarProfCursoActivity.this, RegistrarPracticanteActivity.class));
                                break;
                            case R.id.nav_calendario:
                                startActivity(new Intent(RegistrarProfCursoActivity.this, CrearCalendarioActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(RegistrarProfCursoActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
                    }
                });
        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText nombreField = findViewById(R.id.editText6);
                String nombre = nombreField.getText().toString();
                EditText telefonoField = findViewById(R.id.textFieldTel);
                String telefono = telefonoField.getText().toString();
                EditText correoField = findViewById(R.id.editText4);
                String correo = correoField.getText().toString();
                final ProfesorDAO dao = new ProfesorDAO(getApplicationContext());
                dao.registrarProfesor(nombre, correo, telefono, telefono);
                Toast.makeText(RegistrarProfCursoActivity.this,"Profesor registrado", Toast.LENGTH_SHORT).show();
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
}
