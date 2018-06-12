package com.example.poo.progra2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.poo.progra2.R;
import com.example.poo.progra2.View.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.View.ActivitiesEncargado.EncargadoActivity;
import com.example.poo.progra2.View.ActivitiesPracticante.PracticanteActivity;
import com.example.poo.progra2.View.ActivitiesProfCurso.PCursoActivity;
import com.example.poo.progra2.logica.Encargado;
import com.example.poo.progra2.logica.Profesor;
import com.example.poo.progra2.xml.PracticanteDAO;
import com.example.poo.progra2.xml.ProfesorDAO;

public class LogInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tiposDeUsuario = {"Practicante", "Profesor de Curso", "Profesor Asesor", "Encargado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        final Spinner usuarios = (Spinner) findViewById(R.id.tiposUsuarios);
        usuarios.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,tiposDeUsuario);
        usuarios.setAdapter(aa);
        final ProfesorDAO daoP = new ProfesorDAO(getApplicationContext());
        daoP.parseXml();
        daoP.writeXml();
        final PracticanteDAO dao = new PracticanteDAO(getApplicationContext());
        dao.parseXml();
        dao.writeXml();
        Button button = (Button) findViewById(R.id.log_in_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int tipo  = usuarios.getSelectedItemPosition();
                EditText idField = findViewById(R.id.textFieldId);
                String id = idField.getText().toString();
                EditText contraField = findViewById(R.id.contraTextField);
                String contra = contraField.getText().toString();
                switch (tipo){
                    case 0:
                        if(dao.logIn(id,contra)) {
                            startActivity(new Intent(LogInActivity.this, PracticanteActivity.class));
                        }
                        break;
                    case 1:
                        if(daoP.logIn(id, contra)) {
                            startActivity(new Intent(LogInActivity.this, PCursoActivity.class));
                        }
                        break;
                    case 2:
                        if(daoP.logIn(id, contra)) {
                            startActivity(new Intent(LogInActivity.this, AsesorActivity.class));
                        }
                        break;
                    case 3:
                        Encargado encargado = new Encargado();
                        if(id.equals(encargado.getUsuario()) && contra.equals(encargado.getContrasena())) {
                            startActivity(new Intent(LogInActivity.this, EncargadoActivity.class));
                        }
                        break;
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> Av , View v, int position, long id){

    }

    @Override
    public void onNothingSelected(AdapterView<?> aa){

    }


}
