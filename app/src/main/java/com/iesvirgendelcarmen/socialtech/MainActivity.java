package com.iesvirgendelcarmen.socialtech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity  implements View.OnClickListener{
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
    private TextView valor_edad;
    public static final String KEY_ALUMNO = "Alumno";
    public static final String TOTAL = "TotalAlumnos";
    private Button boton_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editext_nombre = findViewById(R.id.editText_nombre);
        editext_apellidos = findViewById(R.id.editText_apellidos);
        editext_telefono = findViewById(R.id.editText_telefono);
        editext_email = findViewById(R.id.editText_email);
        spinner_provincias = findViewById(R.id.spinner_provincias);
        mt_formacion=findViewById(R.id.multiAutoCompleteTextView);
        totalAlumnos = findViewById(R.id.total_alumnos);


        edad = findViewById(R.id.seekBar);
        valor_edad = findViewById(R.id.seekbar_valorEdad);

        if (edad!= null) {
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
                    valor_edad.setText(edad.getProgress()+"");
                   // Toast.makeText(MainActivity.this, "Current value is " + edad.getProgress(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        boton_guardar = findViewById(R.id.btn_guardar);

        if (savedInstanceState != null) {
            numAlumnos=savedInstanceState.getInt(TOTAL, 0);
            listaAlumnos = (ArrayList<Alumno>) savedInstanceState.getSerializable(KEY_ALUMNO);
            totalAlumnos.setText(listaAlumnos.size()+"");
        }

        //ArrayAdapter MultiAutoCompleteTextView
        String[] formaciones = getResources().getStringArray(R.array.formacion);
        ArrayAdapter adapterFormacion = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,formaciones);
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


    }

    @Override
    public void onClick(View v) {
        String nombre_alumno = editext_nombre.getText().toString();
        String apellidos_alumno = editext_apellidos.getText().toString();
        String telefono_alumno = editext_telefono.getText().toString();
        String email_alumno = editext_email.getText().toString();
       // String formacion = spinner_formacion.getSelectedItem().toString();
        String formacion_alumno = mt_formacion.getText().toString();
        String provincia = spinner_provincias.getSelectedItem().toString();
        int edad= Integer.parseInt(valor_edad.getText().toString());

        // listaAlumnos = new ArrayList<Alumno>(); SE CREA UN NUEVO ARRAYLIST Y SIEMPRE TIENE UN OBJETO--MACHACA EL ANTERIOR.
        // if (nombre_alumno.length()>2 && apellidos_alumno.length()>2 && telefono_alumno.length()>=9 && email_alumno.contains("@"))
        if (comprobarDatosFormularioAlumno(nombre_alumno, apellidos_alumno, telefono_alumno, email_alumno)) {
            new Alumno(nombre_alumno, apellidos_alumno, edad, telefono_alumno, email_alumno, formacion_alumno, provincia);

            listaAlumnos.add(alumno);
            numAlumnos = listaAlumnos.size();
            totalAlumnos.setText(numAlumnos + " ");
            //Limpiamos campos en cada ingreso de datos
            editext_nombre.setText(" ");
            editext_apellidos.setText(" ");
            editext_telefono.setText(" ");
            editext_email.setText(" ");
            mt_formacion.setText("");
          //  spinner_formacion.setSelection(0);//Vuelve a quedar al valor por defecto cada vez que se ingrese un dato sin instalar de nuevo la app
            spinner_provincias.setSelection(0);
            editext_nombre.requestFocus();//Volvemos el foco/el cursor al primer elemento del formulario

            Toast.makeText(this, "Se ha registrado " + nombre_alumno + " " + apellidos_alumno+" de " +edad+" edad y "+ " con formación en " + formacion_alumno + " de la provincia de "+ provincia+"\n Total: "+numAlumnos+" registrados", Toast.LENGTH_LONG).show();
        }

    }

    public boolean comprobarDatosFormularioAlumno(String nombre, String apellidos, String telefono, String email) {
        if (nombre.length() > 2) {
            if(apellidos.length()>2){
                if(telefono.length()>8){
                    if(email.contains("@")){
                        return true;
                    }else{Toast.makeText(this, "Introduzca su email correctamente", Toast.LENGTH_LONG).show();
                        editext_email.requestFocus();
                    }
                }else{Toast.makeText(this, "Introduzca su teléfono correctamente", Toast.LENGTH_LONG).show();
                    editext_telefono.requestFocus();
                }
            }else{Toast.makeText(this, "Introduzca sus apellidos correctamente", Toast.LENGTH_LONG).show();
                editext_apellidos.requestFocus();
            }
        } else {
            Toast.makeText(this, "Introduzca su nombre correctamente", Toast.LENGTH_LONG).show();
            editext_nombre.requestFocus();
        }
        return false;
    }

      @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ALUMNO, (ArrayList<Alumno>)listaAlumnos);
        outState.putInt(TOTAL, listaAlumnos.size());
    }


}
