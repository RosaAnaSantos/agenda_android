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

