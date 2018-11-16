package com.iesvirgendelcarmen.socialtech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity implements AlumnosRegistradosFragment.OnEventoSeleccionado {
    private Alumno alumno;
    private int numAlumnos;
    private List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    private  FormularioAlumnosFragment formularioAlumnosFragment= new FormularioAlumnosFragment();
    private EditText editext_nombre;
    private EditText editext_apellidos;
    private EditText editext_telefono;
    private EditText editext_email;
    private Spinner spinner_provincias;
    private List<String> provincias;
    private MultiAutoCompleteTextView mt_formacion;
    private TextView totalAlumnos;
    private SeekBar edad;
    private RadioGroup radioGroup;
    private RadioButton radioBotonMasculio;
    private RadioButton radioBotonFemenino;
    private TextView valor_edad;
    public static final String KEY_ALUMNO = "Alumno";
    public static final String KEY_LISTA_ALUMNO = "ListaAlumnos";
    public static final String TOTAL = "TotalAlumnos";
    public static final String NOMBRE = "nombre";
    private AlumnoAdapter alumnoAdapter;
    private ListView listView;
    private Button boton_guardar;
    private DetallesAlumnoFragment detallesAlumnoFragment;
    private FormularioAlumnosFragment fragmentFormulario = new FormularioAlumnosFragment();
    private AlumnosRegistradosFragment alumnosRegistradosFragment = new AlumnosRegistradosFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.contenedor, fragmentFormulario);
        FT.commit();

    }


    public void cambiarFragmento(Fragment fragmento){
        FragmentManager FM=getSupportFragmentManager();
        FragmentTransaction FT=FM.beginTransaction();
        FT.replace(R.id.contenedor, fragmento);
        FT.commit();

    }

   public void eventoSeleccionado(Alumno alumno){
     DetallesAlumnoFragment detallesAlumnoFragment=new DetallesAlumnoFragment();
     Bundle bundle =new Bundle();
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
                return true;
            case R.id.menu_alumnos_registrados:
                cambiarFragmento(alumnosRegistradosFragment);
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

}