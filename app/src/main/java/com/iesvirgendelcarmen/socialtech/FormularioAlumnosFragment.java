package com.iesvirgendelcarmen.socialtech;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FormularioAlumnosFragment extends Fragment {
Button boton_ver_alumnos;
AlumnosRegistradosFragment alumnosRegistradosFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.formulario_alumnos, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        verRegistroAlumnos();
    }

    private void verRegistroAlumnos() {
        boton_ver_alumnos = (Button) getView().findViewById(R.id.btn_ver_registro);
        boton_ver_alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmentoEnPrincipal(new AlumnosRegistradosFragment());
            }
        });
    }

}