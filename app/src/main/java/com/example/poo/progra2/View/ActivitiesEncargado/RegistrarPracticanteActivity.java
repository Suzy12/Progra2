package com.example.poo.progra2.View.ActivitiesEncargado;

import android.content.Intent;
import android.net.Uri;
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

import com.example.poo.progra2.View.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.View.ActivitiesPracticante.PracticanteActivity;
import com.example.poo.progra2.View.ActivitiesProfCurso.PCursoActivity;
import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.logica.Empresa;
import com.example.poo.progra2.logica.Periodo;
import com.example.poo.progra2.logica.Practicante;
import com.example.poo.progra2.logica.Profesor;
import com.example.poo.progra2.xml.EmpresaDAO;
import com.example.poo.progra2.xml.PeriodoDAO;
import com.example.poo.progra2.xml.PracticanteDAO;
import com.example.poo.progra2.xml.ProfesorDAO;

import java.util.ArrayList;

public class RegistrarPracticanteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    ArrayList<Periodo> periodos = PeriodoDAO.periodos;
    ArrayList<Profesor> profesores = ProfesorDAO.profesores;
    ArrayList<Empresa> empresas = EmpresaDAO.empresas;
    String[] periodo = {};
    String[] prof = {};
    String[] empre = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_practicante);
        inicializarPeriodos();
        final Spinner periodoSpinner = (Spinner) findViewById(R.id.editText3623);
        periodoSpinner.setOnItemSelectedListener(this);
        ArrayAdapter spin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, periodo);
        periodoSpinner.setAdapter(spin);
        inicializarProfesores();
        final Spinner profAsesorSpinner = (Spinner) findViewById(R.id.editText3);
        profAsesorSpinner.setOnItemSelectedListener(this);
        ArrayAdapter pSpin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, prof);
        profAsesorSpinner.setAdapter(pSpin);
        final Spinner profCursoSpinner = (Spinner) findViewById(R.id.editText34);
        profCursoSpinner.setOnItemSelectedListener(this);
        profCursoSpinner.setAdapter(pSpin);
        inicializarEmpresas();
        final Spinner empresaSpinner= (Spinner) findViewById(R.id.editText1029);
        empresaSpinner.setOnItemSelectedListener(this);
        ArrayAdapter empreSpin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, empre);
        empresaSpinner.setAdapter(empreSpin);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        final PracticanteDAO dao = new PracticanteDAO(getApplicationContext());
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (item.getItemId()) {
                            case R.id.nav_inicio:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, EncargadoActivity.class));
                                break;
                            case R.id.nav_empresa:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, RegistrarEmpresaActivity.class));
                                break;
                            case R.id.nav_profC:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, RegistrarProfCursoActivity.class));
                                break;
                            case R.id.nav_periodo:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, RegistrarPeriodoActivity.class));
                                break;
                            case R.id.nav_calendario:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, CrearCalendarioActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(RegistrarPracticanteActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
                    }
                });
        Button button = (Button) findViewById(R.id.button123);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(periodos.isEmpty()){
                    Toast.makeText(RegistrarPracticanteActivity.this,"No hay periodos registrados", Toast.LENGTH_SHORT).show();
                }else if(profesores.isEmpty()){
                    Toast.makeText(RegistrarPracticanteActivity.this,"No hay profesores registrados", Toast.LENGTH_SHORT).show();
                }else if(empresas.isEmpty()){
                    Toast.makeText(RegistrarPracticanteActivity.this,"No hay empresas registradas", Toast.LENGTH_SHORT).show();
                }else {
                    EditText nombreField = findViewById(R.id.editText29);
                    String nombre = nombreField.getText().toString();
                    EditText telefonoField = findViewById(R.id.editText28);
                    String telefono = telefonoField.getText().toString();
                    EditText carnetField = findViewById(R.id.editText30);
                    String carnet = carnetField.getText().toString();
                    EditText cedulaField = findViewById(R.id.editText31);
                    String cedula = cedulaField.getText().toString();
                    EditText fechaField = findViewById(R.id.editText32);
                    String fecha = fechaField.getText().toString();
                    EditText direccionField = findViewById(R.id.editText33);
                    String direccion = direccionField.getText().toString();
                    EditText correoField = findViewById(R.id.editText35);
                    String correo = correoField.getText().toString();
                    String perio = periodoSpinner.getSelectedItem().toString();
                    String profAsesor = profAsesorSpinner.getSelectedItem().toString();
                    String profCurso = profCursoSpinner.getSelectedItem().toString();
                    String empresa = empresaSpinner.getSelectedItem().toString();
                    final PeriodoDAO daoP = new PeriodoDAO(getApplicationContext());
                    String[] p = perio.split(",");
                    Periodo perD = daoP.buscarPeriodo(p[0], p[1]);
                    if(perD.getCalendario().equals(null)){
                        Toast.makeText(RegistrarPracticanteActivity.this,"No hay un calendario registrado", Toast.LENGTH_SHORT).show();
                    }else {
                        final PracticanteDAO dao = new PracticanteDAO(getApplicationContext());
                        dao.registrarPracticante(nombre, carnet, cedula, fecha, direccion, profAsesor, profCurso, empresa);
                        Toast.makeText(RegistrarPracticanteActivity.this,"Practicante registrado", Toast.LENGTH_SHORT).show();
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:" + correo)); // aqui se coloca el corre a enviar el mensaje, es necesario el mailto:
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Perfil completado"); // aqui se coloca el subject a enviar el correo
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Su contraseña es su numero de cedula." + "\n" + "Numero de carnet: " + carnet + "\n" + "Profesor Asesor: " + profAsesor + "\n" + "Profesor Curso: " + profCurso + "\n" + "Empresa: " + empresa + "\n" + "Periodo: " + perio); // aqui va el body del correo

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(RegistrarPracticanteActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
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
    public void inicializarPeriodos() {
        if (!periodos.isEmpty()) {
            for (int i = 0; i < periodos.size(); i++) {
                String p = periodos.get(i).getSemestre() + "," + periodos.get(i).getAno();
                int currentSize = periodo.length;
                int newSize = currentSize + 1;
                String[] temp = new String[newSize];
                for (int j = 0; j < currentSize; i++) {
                    temp[j] = periodo[j];
                }
                temp[newSize - 1] = p;
                periodo = temp;
            }
        }
    }
    public void inicializarProfesores() {
        if (!profesores.isEmpty()) {
            for (int i = 0; i < profesores.size(); i++) {
                String p = profesores.get(i).getEmail();
                int currentSize = prof.length;
                int newSize = currentSize + 1;
                String[] temp = new String[newSize];
                for (int j = 0; j < currentSize; i++) {
                    temp[j] = prof[j];
                }
                temp[newSize - 1] = p;
                prof = temp;
            }
        }
    }
    public void inicializarEmpresas() {
        if (!empresas.isEmpty()) {
            for (int i = 0; i < empresas.size(); i++) {
                String p = empresas.get(i).getNombre();
                int currentSize = empre.length;
                int newSize = currentSize + 1;
                String[] temp = new String[newSize];
                for (int j = 0; j < currentSize; i++) {
                    temp[j] = empre[j];
                }
                temp[newSize - 1] = p;
                empre = temp;
            }
        }

    }
}
