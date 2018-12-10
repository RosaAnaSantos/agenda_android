package com.iesvirgendelcarmen.socialtech;

import android.os.Bundle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetallesAlumnoFragment extends Fragment {
    @BindView(R.id.tvNombre)
    TextView nombre;
    @BindView(R.id.tvApellidos)
    TextView apellidos;
    @BindView(R.id.tvTelefono)
    TextView telefono;
    @BindView(R.id.tvEmail)
    TextView email;
    @BindView(R.id.tvProvincia)
    TextView provincia;
    @BindView(R.id.tvEdad)
    TextView edad;
    @BindView(R.id.tvSexo)
    TextView sexo;
    @BindView(R.id.tvFormacion)
    TextView formacion;
    @BindView(R.id.listView_foto)
    ImageView foto;
    DetallesAlumnoFragment detallesAlumnoFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.detalles_alumno, container, false);
        ButterKnife.bind(this, vista);
        Bundle bundle = getArguments();
        Alumno alumno = (Alumno) bundle.get("EVENTO");

        nombre.setText(alumno.getNombre());
        apellidos.setText(alumno.getApellidos());
        email.setText(alumno.getEmail());
        telefono.setText(alumno.getTelefono());
        formacion.setText(alumno.getFormacion());
        provincia.setText(alumno.getProvincia());
        edad.setText(alumno.getEdad());
        sexo.setText(alumno.getSexo());
        int posicion = alumno.getFoto();


        if (posicion != 0) {
            if (posicion == 1) {
                foto.setImageResource(R.drawable.foto1);
            } else if (posicion == 2) {
                foto.setImageResource(R.drawable.foto2);
            } else if (posicion == 3) {
                foto.setImageResource(R.drawable.foto3);
            } else if (posicion == 4) {
                foto.setImageResource(R.drawable.foto4);
            } else if (posicion == 5) {
                foto.setImageResource(R.drawable.foto5);
            } else if (posicion == 6) {
                foto.setImageResource(R.drawable.foto6);
            } else if (posicion == 7) {
                foto.setImageResource(R.drawable.foto7);
            } else if (posicion == 8) {
                foto.setImageResource(R.drawable.foto8);
            }

        } else {
            foto.setImageResource(R.drawable.foto0);

        }
 return vista;
    }


}
