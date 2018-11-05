package com.iesvirgendelcarmen.socialtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class AlumnosRegistradosFragment extends Fragment {
    private Alumno alumno;
    List<Alumno> listaAlumnos;
    private TextView totalAlumnos;
    public static final String KEY_ALUMNO = "Alumno";
    public static final String KEY_LISTA_ALUMNO = "ListaAlumnos";
    public static final String TOTAL = "TotalAlumnos";
    public static final String NOMBRE = "nombre";
    private ListView listView;
    FormularioAlumnosFragment formularioAlumnosFragment;
    AlumnosRegistradosFragment alumnosRegistradosFragment;
    private Button volver_a_formulario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.alumnos_registrados_listview, container, false);

        anadeListView(vista);
        volverAlFormulario(vista);

        return vista;


    }


        public void anadeListView(View view) {
            ListView listView = view.findViewById(R.id.listView_alumnos);
            listaAlumnos = ((MainActivity)getActivity()).getListaAlumnos();
            AlumnoAdapter alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);
            listView.setAdapter(alumnoAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });


        }


    private void volverAlFormulario(View vista) {

        volver_a_formulario = (Button) vista.findViewById(R.id.btn_ver_formulario);
        volver_a_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FormularioAlumnosFragment());
            }
        });
    }



}

