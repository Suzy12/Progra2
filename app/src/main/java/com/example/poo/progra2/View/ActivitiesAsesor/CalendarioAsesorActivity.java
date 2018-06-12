package com.example.poo.progra2.View.ActivitiesAsesor;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;

public class CalendarioAsesorActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_entregables);
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
                        switch (item.getItemId()){
                            case R.id.nav_inicio:
                                startActivity(new Intent(CalendarioAsesorActivity.this,AsesorActivity.class));
                                break;
                            case R.id.nav_asignar:
                                startActivity(new Intent(CalendarioAsesorActivity.this, AsignarNotaActivity.class ));
                                break;
                            case R.id.nav_consultarEntregables:
                                startActivity(new Intent(CalendarioAsesorActivity.this, ConsultarEntregableActivity.class));
                                break;
                            case R.id.nav_solicitar:
                                startActivity(new Intent(CalendarioAsesorActivity.this, SolicitarReunionActivity.class));
                                break;
                            case R.id.nav_consultarMinutas:
                                startActivity(new Intent(CalendarioAsesorActivity.this, ConsultarMinutasActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(CalendarioAsesorActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
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
