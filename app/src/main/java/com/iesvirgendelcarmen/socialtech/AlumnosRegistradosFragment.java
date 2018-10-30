package com.iesvirgendelcarmen.socialtech;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AlumnosRegistradosFragment extends Fragment {
    FormularioAlumnosFragment formularioAlumnosFragment;
    Button boton_ver_formulario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alumnos_registrados_listview, container, false);

    }


    /*
      Alumno a=  new Alumno("Rosa","Santos","micole@gmail.es");
        listaAlumnos.add(a);
        Alumno b=  new Alumno("Ana","Gore","ana@gmail.es");
        listaAlumnos.add(b);
        ListView listView = vista.findViewById(R.id.listView_alumnos);
        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);
        listView.setAdapter(alumnoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alumno alumno_position = listaAlumnos.get(position);
                String nombre=alumno_position.getNombre();
                irAdetallasActivity( position);
                Toast toast = Toast.makeText(getActivity(), alumno_position.getNombre(), Toast.LENGTH_SHORT);
                toast.show();




            }
        });








    */

    @Override
    public void onResume() {
        super.onResume();
        verFormularioAlumnos();
    }

    private void verFormularioAlumnos() {
        boton_ver_formulario = (Button) getView().findViewById(R.id.btn_ver_formulario);
        boton_ver_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmentoEnPrincipal(new FormularioAlumnosFragment());
            }
        });
    }

}

