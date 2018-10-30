package com.iesvirgendelcarmen.socialtech;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity  extends AppCompatActivity{
    private Alumno alumno;
    private int numAlumnos;
    private List<Alumno> listaAlumnos = new ArrayList<Alumno>();
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
    private FormularioAlumnosFragment formularioAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager FM=getSupportFragmentManager();
        FragmentTransaction FT=FM.beginTransaction();
        FormularioAlumnosFragment fragmentFormulario=new FormularioAlumnosFragment();
        FT.add(R.id.contenedor, fragmentFormulario);
        FT.commit();

        /*
        editext_nombre = findViewById(R.id.editText_nombre);
        editext_apellidos = findViewById(R.id.editText_apellidos);
        editext_telefono = findViewById(R.id.editText_telefono);
        editext_email = findViewById(R.id.editText_email);
        spinner_provincias = findViewById(R.id.spinner_provincias);
        mt_formacion = findViewById(R.id.multiAutoCompleteTextView);
        radioGroup = findViewById(R.id.radioGrou);
        totalAlumnos = findViewById(R.id.total_alumnos);
        edad = findViewById(R.id.seekBar);
        valor_edad = findViewById(R.id.seekbar_valorEdad);



        Alumno a=  new Alumno("Rosa","Santos","micole@gmail.es");
        listaAlumnos.add(a);
        Alumno b=  new Alumno("Ana","Gore","ana@gmail.es");
        listaAlumnos.add(b);
        listView = findViewById(R.id.listView_alumnos);
        alumnoAdapter = new AlumnoAdapter(this, listaAlumnos);
        listView.setAdapter(alumnoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumno alumno_position = listaAlumnos.get(position);
                String nombre=alumno_position.getNombre();
                irAdetallasActivity( position);
                Toast toast = Toast.makeText(getApplicationContext(), alumno_position.getNombre(), Toast.LENGTH_SHORT);
                toast.show();




            }
        });



        if (edad != null) {
            edad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Write code to perform some action when progress is changed.
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is started.
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is stopped.
                    valor_edad.setText(edad.getProgress() + "");
                    // Toast.makeText(MainActivity.this, "Current value is " + edad.getProgress(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        boton_guardar = findViewById(R.id.btn_guardar);
        if (savedInstanceState != null) {
            numAlumnos = savedInstanceState.getInt(TOTAL, 0);
            listaAlumnos = (ArrayList<Alumno>) savedInstanceState.getSerializable(KEY_ALUMNO);
            totalAlumnos.setText(listaAlumnos.size() + "");
        }
        //ArrayAdapter MultiAutoCompleteTextView
        String[] formaciones = getResources().getStringArray(R.array.formacion);
        ArrayAdapter adapterFormacion = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, formaciones);
        mt_formacion.setAdapter(adapterFormacion);
        mt_formacion.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //ArrayAdapter spinner provincias
        provincias = new ArrayList<String>();
        provincias.add("Almería");
        provincias.add("Cádiz");
        provincias.add("Córdoba");
        provincias.add("Granada");
        provincias.add("Huelva");
        provincias.add("Jaén");
        provincias.add("Málaga");
        provincias.add("Sevilla");
        ArrayAdapter adapterProvincias = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                provincias);
        spinner_provincias.setAdapter(adapterProvincias);
        Button btn_cargar = findViewById(R.id.btn_guardar);
        btn_cargar.setOnClickListener(this);


*/
    }

    /*
    @Override
    public void onClick(View v) {
        String nombre_alumno = editext_nombre.getText().toString();
        String apellidos_alumno = editext_apellidos.getText().toString();
        String telefono_alumno = editext_telefono.getText().toString();
        String email_alumno = editext_email.getText().toString();
        String formacion_alumno = mt_formacion.getText().toString();
        String provincia = spinner_provincias.getSelectedItem().toString();
        int edad = Integer.parseInt(valor_edad.getText().toString());
        radioBotonMasculio = findViewById(R.id.radioButonMasculino);
        String sex = valorSexo(radioGroup);
        if (comprobarDatosFormularioAlumno(nombre_alumno, apellidos_alumno,telefono_alumno, email_alumno)) {
            if(formacion_alumno.length()==0){
                formacion_alumno=" ----NO contesta en FORMACIÓN----";
            }

          Alumno alumno=new Alumno(nombre_alumno, apellidos_alumno, edad, sex, telefono_alumno, email_alumno, formacion_alumno, provincia);
            listaAlumnos.add(alumno);
            alumnoAdapter.notifyDataSetChanged();
            numAlumnos = listaAlumnos.size();
            totalAlumnos.setText(numAlumnos + " ");
            limpiarCampos();

            Toast.makeText(this, "Se ha registrado " + nombre_alumno + " " + apellidos_alumno + " de " + edad + " edad y es " + sex + " con formación en " + formacion_alumno + " de la provincia de " + provincia + "\n Total: " + numAlumnos + " registrados", Toast.LENGTH_LONG).show();
        }


    }

    private void inicialize(){
        formularioAlumnos = new FormularioAlumnosFragment();
        reemplazarFragmentoPrincipal(formularioAlumnos);
        getFragmentManager().beginTransaction().add(R.id.contenedor, formularioAlumnos).commit();
    }

    public void reemplazarFragmentoPrincipal(Fragment fragmento){
        getFragmentManager().beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }



    public boolean comprobarDatosFormularioAlumno(String nombre, String apellidos, String telefono, String email) {
        if (nombre.length() > 2) {
            if (apellidos.length() > 2) {
                if (telefono.length() > 8) {
                    if (email.contains("@")) {
                        return true;
                    } else {
                        Toast.makeText(this, "Introduzca su email correctamente", Toast.LENGTH_LONG).show();
                        editext_email.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "Introduzca su teléfono correctamente", Toast.LENGTH_LONG).show();
                    editext_telefono.requestFocus();
                }
            } else {
                Toast.makeText(this, "Introduzca sus apellidos correctamente", Toast.LENGTH_LONG).show();
                editext_apellidos.requestFocus();
            }
        } else {
            Toast.makeText(this, "Introduzca su nombre correctamente", Toast.LENGTH_LONG).show();
            editext_nombre.requestFocus();
        }
        return false;
    }
    //Volvemos todos los campos del formulario a su valor por defecto
    public void limpiarCampos() {
        editext_nombre.setText(" ");
        editext_apellidos.setText(" ");
        editext_telefono.setText(" ");
        editext_email.setText(" ");
        edad.setProgress(0);
        valor_edad.setText(edad.getProgress() + "");
        mt_formacion.setText("");
        spinner_provincias.setSelection(0);
        radioGroup.clearCheck();
        editext_nombre.requestFocus();
    }
    //Método que devuelve el valor de RadioButton seleccionado
    public String valorSexo(RadioGroup sexoChico) {
        int sex=sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex==R.id.radioButonMasculino) {
            sexo = "chico";
        }
        else if(sex==R.id.radioButonFemenino){
            sexo="chica";
        }
        else{
            sexo="---NO contesta en SEXO----";
        }
        return sexo;
    }

     public void  irAdetallasActivity(int position){
         //Creamos un bundle para guardar el objeto alumno
         Bundle datos = new Bundle();
         datos.putSerializable(KEY_LISTA_ALUMNO,  listaAlumnos.get(position));
         //Creamos intent y pasamos el bundle al intent
         Intent intent = new Intent(this, DetallesAlumnoFragment.class);
         intent.putExtras(datos);
         startActivity(intent);

     }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ALUMNO, (ArrayList<Alumno>) listaAlumnos);
        outState.putInt(TOTAL, listaAlumnos.size());
    }

    */

    public void cambiarFragmentoEnPrincipal(Fragment fragmento){
        FragmentManager FM=getSupportFragmentManager();
        FragmentTransaction FT=FM.beginTransaction();
        FormularioAlumnosFragment fragmentFormulario=new FormularioAlumnosFragment();
        FT.replace(R.id.contenedor, fragmento);
        FT.commit();

    }
}