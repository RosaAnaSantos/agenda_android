package com.iesvirgendelcarmen.socialtech;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetallesAlumnoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle  savedInstanceState) {
        return inflater.inflate(R.layout.detalles_alumno, container, false);

       /* //Rescatamos el bundle
        Bundle datos = getIntent().getExtras();
        Alumno alumno = (Alumno) datos.getSerializable(MainActivity.KEY_LISTA_ALUMNO);
        //Seteamos los valores de los campos a mostrar
        TextView tv_nombr = findViewById(R.id.tvNombre);
        tv_nombr.setText(alumno.getNombre());
        TextView tv_apellido=findViewById(R.id.tvApellidos);
        tv_apellido.setText(alumno.getApellidos());

        TextView tv_mail=findViewById(R.id.tvEmail);
        tv_mail.setText(alumno.getEmail());

        TextView tv_telefon=findViewById(R.id.tvTelefono);
        tv_telefon.setText(alumno.getTelefono());

        TextView tv_formacio=findViewById(R.id.tvFormacion);
        tv_formacio.setText(alumno.getFormacion());

        TextView tv_provinci=findViewById(R.id.tvProvincia);
        tv_provinci.setText(alumno.getProvincia());

        TextView tv_eda=findViewById(R.id.tvEdad);
        tv_eda.setText(alumno.getEdad());

        TextView tv_sex=findViewById(R.id.tvSexo);
        tv_sex.setText(alumno.getSexo());
*/
    }

}
