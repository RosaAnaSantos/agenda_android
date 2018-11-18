package com.iesvirgendelcarmen.socialtech;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class FormularioEditar extends Fragment {

    private Alumno alumno;
    private int numAlumnos;
    private EditText editext_nombre;
    private EditText editext_apellidos;
    private EditText editext_telefono;
    private EditText editext_email;
    private Spinner spinner_provincias;
    private List<String> provincias;
    private MultiAutoCompleteTextView mt_formacion;
    private SeekBar edad;
    private RadioGroup radioGroup;
    private RadioButton radioBotonMasculio;
    private RadioButton radioBotonFemenino;
    private TextView valor_edad;
    public static final String KEY_ALUMNO = "Alumno";
    public static final String EVENTO = "ListaAlumnos";
    public static final String NOMBRE = "nombre";
    private ListView listView;
    private Button boton_guardar;
    FormularioEditar formularioEditar;
    Button boton_ver_alumnos;
    private AlumnosRegistradosFragment alumnosRegistradosFragment;
    private AlumnosRegistradosFragment.OnEventoSeleccionado callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (AlumnosRegistradosFragment.OnEventoSeleccionado) context;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.formulario_editar, container, false);



        List<Alumno>  listaAlumnos = new ArrayList<Alumno>();
        listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
        Alumno alumno=listaAlumnos.get(((MainActivity) getActivity()).valor);
        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);


        editext_nombre = vista.findViewById(R.id.editText_nombre);
        editext_nombre.setText(alumno.getNombre());

        editext_apellidos = vista.findViewById(R.id.editText_apellidos);
        editext_apellidos.setText(alumno.getApellidos());

        editext_telefono = vista.findViewById(R.id.editText_telefono);
        editext_telefono.setText(alumno.getTelefono());

        editext_email = vista.findViewById(R.id.editText_email);
        editext_email.setText(alumno.getEmail());

        spinner_provincias = vista.findViewById(R.id.spinner_provincias);
        spinner_provincias.setSelection(seleccionProvincia(alumno));

        mt_formacion = vista.findViewById(R.id.multiAutoCompleteTextView);
        mt_formacion.setText(alumno.getFormacion());

        radioGroup = vista.findViewById(R.id.radioGrou);


        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekbar_valorEdad);
        valor_edad.setText(alumno.getEdad());

        radioBotonMasculio = vista.findViewById(R.id.radioButonMasculino);
        radioBotonFemenino=vista.findViewById(R.id.radioButonFemenino);
        mostrarSexo(alumno);



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
        spinner_provincias.setSelection(seleccionProvincia(alumno));


        Button btn_modificar = vista.findViewById(R.id.btn_modificar_alumno);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
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

                    List<Alumno> listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
                    listaAlumnos.set(((MainActivity) getActivity()).valor, alumno);



                    Toast.makeText(getActivity(), "Se ha modificado corectamente " + nombre_alumno + " " + apellidos_alumno + " de " + edad + " edad y es " + " con formación en " + formacion_alumno + " de la provincia de " + provincia + "\n Total: " + numAlumnos + " registrados", Toast.LENGTH_LONG).show();


                }

            }
        });//cierre onclick




        return vista;
    }
    public int seleccionProvincia(Alumno alumno){
        int s=0;
        if(alumno.getProvincia()=="Almería") {
            s=0;
        }else if (alumno.getProvincia()=="Cádiz"){
            s=1;
        }else if (alumno.getProvincia()=="Córdoba") {
            s=2;
        }else if (alumno.getProvincia()=="Granad"){
            s=3;
        }else if (alumno.getProvincia()=="Huelva") {
            s=1;
        }else if (alumno.getProvincia()=="Málaga"){
            s=4;
        }else if (alumno.getProvincia()=="Sevilla") {
            s=5;
        }

        return s;
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

    public void mostrarSexo(Alumno alumno){
        String sexo=alumno.getSexo();
        if(alumno.getSexo()=="chico"){
            radioBotonMasculio.setChecked(true);
        } else {
            radioBotonFemenino.setChecked(true);
        }
    }


}


