package com.iesvirgendelcarmen.socialtech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FormularioAlumnosFragment extends Fragment {
    private Alumno alumno;
    private int numAlumnos;
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
    private ListView listView;
    private Button boton_guardar;
    FormularioAlumnosFragment formularioAlumnosFragment;
    Button boton_ver_alumnos;
    AlumnosRegistradosFragment alumnosRegistradosFragment;
    private Object v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.formulario_alumnos, container, false);

        editext_nombre = vista.findViewById(R.id.editText_nombre);
        editext_apellidos = vista.findViewById(R.id.editText_apellidos);
        editext_telefono = vista.findViewById(R.id.editText_telefono);
        editext_email = vista.findViewById(R.id.editText_email);
        spinner_provincias = vista.findViewById(R.id.spinner_provincias);
        mt_formacion = vista.findViewById(R.id.multiAutoCompleteTextView);
        radioGroup = vista.findViewById(R.id.radioGrou);
        totalAlumnos = vista.findViewById(R.id.total_alumnos);
        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekbar_valorEdad);
        radioBotonMasculio = vista.findViewById(R.id.radioButonMasculino);


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


        boton_guardar = vista.findViewById(R.id.btn_guardar_alumno);
        boton_ver_alumnos = vista.findViewById(R.id.btn_ver_registro);
        boton_ver_alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).cambiarFragmento(new AlumnosRegistradosFragment());


            }
        });

        //ArrayAdapter MultiAutoCompleteTextView
        String[] formaciones = getResources().getStringArray(R.array.formacion);
        ArrayAdapter adapterFormacion = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, formaciones);
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
        ArrayAdapter adapterProvincias = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, provincias);
        spinner_provincias.setAdapter(adapterProvincias);
        Button btn_cargar = vista.findViewById(R.id.btn_guardar_alumno);
        btn_cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre_alumno = editext_nombre.getText().toString();
                String apellidos_alumno = editext_apellidos.getText().toString();
                String telefono_alumno = editext_telefono.getText().toString();
                String email_alumno = editext_email.getText().toString();
                String formacion_alumno = mt_formacion.getText().toString();
                String provincia = spinner_provincias.getSelectedItem().toString();
                int edad = Integer.parseInt(valor_edad.getText().toString());
                String sex = valorSexo(radioGroup);
                if (comprobarDatosFormularioAlumno(nombre_alumno, apellidos_alumno, telefono_alumno, email_alumno)) {
                    if (formacion_alumno.length() == 0) {
                        formacion_alumno = " ----NO contesta en FORMACIÓN----";
                    }

                    Alumno alumno = new Alumno(nombre_alumno, apellidos_alumno, edad, sex, telefono_alumno, email_alumno, formacion_alumno, provincia);

                    List<Alumno> listaAlumnos = ((MainActivity)getActivity()).getListaAlumnos();
                    listaAlumnos.add(alumno);
                    numAlumnos = listaAlumnos.size();
                    totalAlumnos.setText(numAlumnos + " ");
                    limpiarCampos();

                    Toast.makeText(getActivity(), "Se ha registrado " + nombre_alumno + " " + apellidos_alumno + " de " + edad + " edad y es " + " con formación en " + formacion_alumno + " de la provincia de " + provincia + "\n Total: " + numAlumnos + " registrados", Toast.LENGTH_LONG).show();


                }

            }
        });//cierre onclick

        return vista;
    }


    public boolean comprobarDatosFormularioAlumno(String nombre, String apellidos, String
            telefono, String email) {
        if (nombre.length() > 2) {
            if (apellidos.length() > 2) {
                if (telefono.length() > 8) {
                    if (email.contains("@")) {
                        return true;
                    } else {
                        Toast.makeText(getActivity(), "Introduzca su email correctamente", Toast.LENGTH_LONG).show();
                        editext_email.requestFocus();
                    }
                } else {
                    Toast.makeText(getActivity(), "Introduzca su teléfono correctamente", Toast.LENGTH_LONG).show();
                    editext_telefono.requestFocus();
                }
            } else {
                Toast.makeText(getActivity(), "Introduzca sus apellidos correctamente", Toast.LENGTH_LONG).show();
                editext_apellidos.requestFocus();
            }
        } else {
            Toast.makeText(getActivity(), "Introduzca su nombre correctamente", Toast.LENGTH_LONG).show();
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
        int sex = sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex == R.id.radioButonMasculino) {
            sexo = "chico";
        } else if (sex == R.id.radioButonFemenino) {
            sexo = "chica";
        } else {
            sexo = "---NO contesta en SEXO----";
        }
        return sexo;
    }

    private void verRegistroAlumnos() {
        boton_ver_alumnos = (Button) getView().findViewById(R.id.btn_ver_registro);
        boton_ver_alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new AlumnosRegistradosFragment());
            }
        });
    }


}
//Nuevo código-----------------------------
/*


    private void inicializar(){
      Button btn_guardar_alumnos  = (Button)getView().findViewById(R.id.btn_guardar_alumno);
        btn_guardar_alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar_alumnos();
            }
        });
    }
    private void guardar_alumnos(){
        editext_nombre = vista.findViewById(R.id.editText_nombre);
        editext_apellidos = vista.findViewById(R.id.editText_apellidos);
        editext_telefono = vista.findViewById(R.id.editText_telefono);
        editext_email = vista.findViewById(R.id.editText_email);
        spinner_provincias = vista.findViewById(R.id.spinner_provincias);
        mt_formacion = vista.findViewById(R.id.multiAutoCompleteTextView);
        radioGroup = vista.findViewById(R.id.radioGrou);
        totalAlumnos = vista.findViewById(R.id.total_alumnos);
        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekbar_valorEdad);
        radioBotonMasculio = vista.findViewById(R.id.radioButonMasculino);
        String nombre_alumno = editext_nombre.getText().toString();
        String apellidos_alumno = editext_apellidos.getText().toString();
        String telefono_alumno = editext_telefono.getText().toString();
        String email_alumno = editext_email.getText().toString();
        String formacion_alumno = mt_formacion.getText().toString();
        String provincia = spinner_provincias.getSelectedItem().toString();
        int edad = Integer.parseInt(valor_edad.getText().toString());
        String sex = valorSexo(radioGroup);
        Alumno alumno = new Alumno(nombre_alumno, apellidos_alumno, edad, sex, telefono_alumno, email_alumno, formacion_alumno, provincia);
        listaAlumnos.add(alumno);
        int num_alumnos=listaAlumnos.size();
    }


    //Método que devuelve el valor de RadioButton seleccionado
    public String valorSexo(RadioGroup sexoChico){
        int sex = sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex == R.id.radioButonMasculino) {
            sexo = "chico";
        } else if (sex == R.id.radioButonFemenino) {
            sexo = "chica";
        } else {
            sexo = "---NO contesta en SEXO----";
        }
        return sexo;
    }

    //Volvemos todos los campos del formulario a su valor por defecto
    public void limpiarCampos () {
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

    public boolean comprobarDatosFormularioAlumno (String nombre, String apellidos, String telefono, String email){
        if (nombre.length() > 2) {
            if (apellidos.length() > 2) {
                if (telefono.length() > 8) {
                    if (email.contains("@")) {
                        return true;
                    } else {
                        Toast.makeText(getActivity(), "Introduzca su email correctamente", Toast.LENGTH_LONG).show();
                        editext_email.requestFocus();
                    }
                } else {
                    Toast.makeText(getActivity(), "Introduzca su teléfono correctamente", Toast.LENGTH_LONG).show();
                    editext_telefono.requestFocus();
                }
            } else {
                Toast.makeText(getActivity(), "Introduzca sus apellidos correctamente", Toast.LENGTH_LONG).show();
                editext_apellidos.requestFocus();
            }
        } else {
            Toast.makeText(getActivity(), "Introduzca su nombre correctamente", Toast.LENGTH_LONG).show();
            editext_nombre.requestFocus();
        }
        return false;
    }

    public void guardarEdad(){
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
    }


}


*/


