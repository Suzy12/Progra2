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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.logica.Periodo;
import com.example.poo.progra2.xml.PeriodoDAO;
import com.example.poo.progra2.logica.Calendario;
import com.example.poo.progra2.logica.Entregable;
import java.util.ArrayList;

public class CrearCalendarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    String[] responsables = {"Profesor de Curso", "Profesor Asesor"};
    String[] cantidad = {"1", "2", "3", "4", "5"};
    ArrayList<Periodo> periodos = PeriodoDAO.periodos;
    String[] periodo = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_calendario);
        arrayPeriodo();
        final Spinner periodoSpinner = (Spinner) findViewById(R.id.spinner);
        periodoSpinner.setOnItemSelectedListener(this);
        ArrayAdapter spin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, periodo);
        periodoSpinner.setAdapter(spin);
        final Spinner usuarios = (Spinner) findViewById(R.id.textView24);
        usuarios.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, responsables);
        usuarios.setAdapter(aa);
        final Spinner usuarios1 = (Spinner) findViewById(R.id.textView32);
        usuarios1.setOnItemSelectedListener(this);
        usuarios1.setAdapter(aa);
        final Spinner usuarios2 = (Spinner) findViewById(R.id.textView28);
        usuarios2.setOnItemSelectedListener(this);
        usuarios2.setAdapter(aa);
        final Spinner usuarios3 = (Spinner) findViewById(R.id.textView242);
        usuarios3.setOnItemSelectedListener(this);
        usuarios3.setAdapter(aa);
        final Spinner usuarios5 = (Spinner) findViewById(R.id.textView36);
        usuarios5.setOnItemSelectedListener(this);
        usuarios5.setAdapter(aa);
        final Spinner cant = (Spinner) findViewById(R.id.spinner11);
        cant.setOnItemSelectedListener(this);
        ArrayAdapter af = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cantidad);
        usuarios5.setAdapter(af);
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
                                startActivity(new Intent(CrearCalendarioActivity.this, EncargadoActivity.class));
                                break;
                            case R.id.nav_empresa:
                                startActivity(new Intent(CrearCalendarioActivity.this, RegistrarEmpresaActivity.class));
                                break;
                            case R.id.nav_profC:
                                startActivity(new Intent(CrearCalendarioActivity.this, RegistrarProfCursoActivity.class));
                                break;
                            case R.id.nav_periodo:
                                startActivity(new Intent(CrearCalendarioActivity.this, RegistrarPeriodoActivity.class));
                                break;
                            case R.id.nav_practicante:
                                startActivity(new Intent(CrearCalendarioActivity.this, RegistrarPeriodoActivity.class));
                                break;
                            case R.id.nav_calendario:
                                startActivity(new Intent(CrearCalendarioActivity.this, CrearCalendarioActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(CrearCalendarioActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
                    }
                });
        Button button = (Button) findViewById(R.id.crear_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(periodos.isEmpty()){
                    Toast.makeText(CrearCalendarioActivity.this,"No hay periodos registrados", Toast.LENGTH_SHORT).show();
                }else {
                    EditText detalleField;
                    EditText fechaField;
                    EditText porcentajeField;
                    EditText electronicoField;
                    detalleField = findViewById(R.id.textView29);
                    String detalle = detalleField.getText().toString();
                    fechaField = findViewById(R.id.textView30);
                    String fecha = fechaField.getText().toString();
                    porcentajeField = findViewById(R.id.textView31);
                    String porcentaje = porcentajeField.getText().toString();
                    String responsable = usuarios1.getSelectedItem().toString();
                    boolean electronico = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
                    final PeriodoDAO dao = new PeriodoDAO(getApplicationContext());
                    String per = periodoSpinner.getSelectedItem().toString();
                    String[] parts = per.split(",");
                    Periodo periodo = dao.buscarPeriodo(parts[0], parts[1]);
                    Calendario calend = new Calendario();
                    Entregable ent = new Entregable(detalle, porcentaje, fecha, electronico);
                    calend.agregarEntregable(ent);
                    periodo.setCalendario(calend);
                    Toast.makeText(CrearCalendarioActivity.this,"Calendario registrado", Toast.LENGTH_SHORT).show();
                }
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
    public void arrayPeriodo(){
        if(!periodos.isEmpty()) {
            for (int i = 0; i < periodos.size(); i++) {
                String p = periodos.get(i).getSemestre() + "," + periodos.get(i).getAno();
                int currentSize = periodo.length;
                int newSize = currentSize+1;
                String[] temp = new String[newSize];
                for(int j = 0; j < currentSize; i++){
                    temp[j] = periodo[j];
                }
                temp[newSize-1] = p;
                periodo = temp;
            }
        }
    }
}


