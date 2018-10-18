package com.iesvirgendelcarmen.socialtech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Alumno alumno;
    private List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    private EditText editext_nombre;
    private EditText editext_apellidos;
    private EditText editext_telefono;
    private EditText editext_email;
    private Spinner spinner_formacion;
    private TextView totalAlumnos;
    private Button boton_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editext_nombre = findViewById(R.id.editText_nombre);
        editext_apellidos = findViewById(R.id.editText_apellidos);
        editext_telefono = findViewById(R.id.editText_telefono);
        editext_email = findViewById(R.id.editText_email);
        spinner_formacion = findViewById(R.id.spinner_formacion);
        totalAlumnos = findViewById(R.id.total_alumnos);
        boton_guardar = findViewById(R.id.btn_guardar);

        Button btn_cargar = findViewById(R.id.btn_guardar);
        btn_cargar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String nombre_alumno = editext_nombre.getText().toString();
        String apellidos_alumno = editext_apellidos.getText().toString();
        String telefono_alumno = editext_telefono.getText().toString();
        String email_alumno = editext_email.getText().toString();
        String formacion = spinner_formacion.getSelectedItem().toString();


        // listaAlumnos = new ArrayList<Alumno>(); SE CREA UN NUEVO ARRAYLIST Y SIEMPRE TIENE UN OBJETO--MACHACA EL ANTERIOR.
        // if (nombre_alumno.length()>2 && apellidos_alumno.length()>2 && telefono_alumno.length()>=9 && email_alumno.contains("@"))
        if (comprobarDatosFormularioAlumno(nombre_alumno, apellidos_alumno, telefono_alumno, email_alumno)) {
            new Alumno(nombre_alumno, apellidos_alumno, telefono_alumno, email_alumno, formacion);

            listaAlumnos.add(alumno);
            int numAlumnos = listaAlumnos.size();
            totalAlumnos.setText(numAlumnos + " ");
            //Limpiamos campos en cada ingreso de datos
            editext_nombre.setText(" ");
            editext_apellidos.setText(" ");
            editext_telefono.setText(" ");
            editext_email.setText(" ");
            spinner_formacion.setSelection(0);//Vuelve a quedar al valor por defecto cada vez que se ingrese un dato sin instalar de nuevo la app
            editext_nombre.requestFocus();//Volvemos el foco/el cursor al primer elemento del formulario

            Toast.makeText(this, "Se ha registrado " + nombre_alumno + " " + apellidos_alumno + " con formación en " + formacion+"."+"\n Total: "+numAlumnos+" registrados", Toast.LENGTH_LONG).show();
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

}
