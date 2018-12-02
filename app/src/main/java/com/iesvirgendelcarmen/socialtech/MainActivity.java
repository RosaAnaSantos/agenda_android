package com.iesvirgendelcarmen.socialtech;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;
import android.view.Menu;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

public class MainActivity  extends AppCompatActivity implements AlumnosRegistradosFragment.OnEventoSeleccionado {
    private Alumno alumno;
    private List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    private FormularioAlumnosFragment formularioAlumnosFragment = new FormularioAlumnosFragment();
    public static final String KEY_ALUMNO = "Alumno";
    public static final String KEY_LISTA_ALUMNO = "ListaAlumnos";
    public static final String TOTAL = "TotalAlumnos";
    public static final String NOMBRE = "nombre";
    private DetallesAlumnoFragment detallesAlumnoFragment=new DetallesAlumnoFragment();
    private FormularioAlumnosFragment fragmentFormulario = new FormularioAlumnosFragment();
    private AlumnosRegistradosFragment alumnosRegistradosFragment = new AlumnosRegistradosFragment();
    private FormularioEditar formularioEditar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    int valor = 0;
    int positio=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
         getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.contenedor, fragmentFormulario);
        FT.commit();

    }


    public void cambiarFragmento(Fragment fragmento) {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.contenedor, fragmento);
        cambiarTitulo(fragmento);
        FT.commit();
    }


    public void eventoSeleccionado(Alumno alumno) {
        DetallesAlumnoFragment detallesAlumnoFragment = new DetallesAlumnoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EVENTO", alumno);
        detallesAlumnoFragment.setArguments(bundle);
        cambiarFragmento(detallesAlumnoFragment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_formulario:
                cambiarFragmento(fragmentFormulario);
                cambiarTitulo(fragmentFormulario);
                return true;
            case R.id.menu_alumnos_registrados:
                cambiarFragmento(alumnosRegistradosFragment);
                cambiarTitulo(alumnosRegistradosFragment);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ALUMNO, (ArrayList<Alumno>) listaAlumnos);
        outState.putInt(TOTAL, listaAlumnos.size());
    }
   public void  cambiarTitulo(Fragment fragmento){
       TextView titulo;
       titulo=findViewById(R.id.textV_titulo);
       if(fragmento==fragmentFormulario ){
         titulo.setText("Registro Alumnos");
       }
       if(fragmento==alumnosRegistradosFragment){
          titulo.setText("Alumnos Registrados");
       }


       if(fragmento==detallesAlumnoFragment){
           titulo.setText("Detalle alumno");
       }

       if(fragmento==formularioEditar){
           titulo.setText("Editar alumno");
       }
       else {titulo.setText("Alumno");

       }
    }



}