package com.iesvirgendelcarmen.socialtech;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class FormularioEditar extends Fragment {

    @BindView(R.id.editText_nombr) EditText editext_nombre;
    @BindView(R.id.editText_apellido) EditText editext_apellidos;
    @BindView(R.id.editText_telefon) EditText editext_telefono;
    @BindView(R.id.editText_emai) EditText editext_email;
    @BindView(R.id.spinner_provincias) Spinner spinner_provincias;
    @BindView(R.id.multiAutoCompleteTextView)MultiAutoCompleteTextView mt_formacion;
    @BindView(R.id.seekBar) SeekBar edad;
    @BindView(R.id.radioGrou) RadioGroup radioGroup;
    @BindView(R.id.radioButonMasculino) RadioButton radioBotonMasculio;
    @BindView(R.id.radioButonFemenino) RadioButton radioBotonFemenino;
    @BindView(R.id.seekbar_valorEdad) TextView valor_edad;
   // @BindView(R.id.spinner_foto_editar) Spinner spinner_foto_editar;
   // @BindView(R.id.imageView)
   // ImageView foto;
    private  int images[] = {R.drawable.foto0, R.drawable.foto1, R.drawable.foto2, R.drawable.foto3, R.drawable.foto4, R.drawable.foto5, R.drawable.foto6, R.drawable.foto7, R.drawable.foto8};
    private Alumno alumno;
    private int numAlumnos;
    private List<String> provincias;
    public static final String KEY_ALUMNO = "Alumno";
    public static final String EVENTO = "ListaAlumnos";
    public static final String NOMBRE = "nombre";
    private ListView listView;
    private List<Alumno> listaAlumnos;
    private AlumnosRegistradosFragment alumnosRegistradosFragment;
    private AlumnosRegistradosFragment.OnEventoSeleccionado callback;
    private FormularioEditar formularioEditar;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private int positio;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (AlumnosRegistradosFragment.OnEventoSeleccionado) context;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.formulario_editar, container, false);
        ButterKnife.bind(this, vista);

        ((MainActivity)getActivity()).cambiarTitulo(formularioEditar);
        List<Alumno>  listaAlumnos = new ArrayList<Alumno>();
        listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
        Alumno alumno=listaAlumnos.get(((MainActivity) getActivity()).valor);
        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);
        Spinner spinner_foto_editar=vista.findViewById(R.id.spinner_foto_editar);

        editext_nombre.setText(alumno.getNombre());
        editext_apellidos.setText(alumno.getApellidos());
        editext_telefono.setText(alumno.getTelefono());
        editext_email.setText(alumno.getEmail());
        spinner_provincias.setSelection(seleccionProvincia(alumno));
        mt_formacion.setText(alumno.getFormacion());
        radioGroup = vista.findViewById(R.id.radioGrou);
        valor_edad.setText(alumno.getEdad());
        mostrarSexo(alumno);
       int posicion = alumno.getFoto();

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

        Spinner spinner_fotos = vista.findViewById(R.id.spinner_foto_editar);

        spinner_fotos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "Has selecionado la imagen que está en la  Position: " + position + " " + images[position], Toast.LENGTH_SHORT).show();

                positio = position;

              //  Toast.makeText(getContext(), position + "  fotooooo------", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        AdaptadorImagen customAdapter = new AdaptadorImagen(getContext(), images);
        spinner_fotos.setAdapter(customAdapter);



        return vista;
    }


    @OnClick(R.id.btn_modificar_alumno)
    public void submit(){
        String nombre_alumno = editext_nombre.getText().toString();
        String apellidos_alumno = editext_apellidos.getText().toString();
        String telefono_alumno = editext_telefono.getText().toString();
        String email_alumno = editext_email.getText().toString();
        String formacion_alumno = mt_formacion.getText().toString();
        String provincia = spinner_provincias.getSelectedItem().toString();
        String edad = valor_edad.getText().toString();
        String sex = valorSexo(radioGroup);

        if (comprobarDatosFormularioAlumno(nombre_alumno, apellidos_alumno, telefono_alumno, email_alumno)) {
            if (formacion_alumno.length() == 0) {
                formacion_alumno = " ----NO contesta en FORMACIÓN----";
            }

            alumno = new Alumno(nombre_alumno, apellidos_alumno, edad, sex, telefono_alumno, email_alumno, formacion_alumno, provincia);
            alumno.setFoto(positio);
            listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
            listaAlumnos.set(((MainActivity) getActivity()).valor, alumno);

        }

    }



    public int seleccionProvincia(Alumno alumno){
        int s=0;
        if(alumno.getProvincia()=="Almería") {
            s=0;
        }else if (alumno.getProvincia()=="Cádiz"){
            s=1;
        }else if (alumno.getProvincia()=="Córdoba") {
            s=2;
        }else if (alumno.getProvincia()=="Granada"){
            s=3;
        }else if (alumno.getProvincia()=="Huelva") {
            s=4;
        }else if (alumno.getProvincia()=="Jaén") {
            s=5;
        }else if (alumno.getProvincia()=="Málaga"){
            s=6;
        }else if (alumno.getProvincia()=="Sevilla") {
            s=7;
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



