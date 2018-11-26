package com.iesvirgendelcarmen.socialtech;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class AlumnoAdapter extends BaseAdapter {
    private Context context;
    private List<Alumno> list;
    private ImageView imageWiew;
    private MainActivity mainActivity;
    FormularioAlumnosFragment formularioAlumnosFragment;

    public AlumnoAdapter() {

    }

    public AlumnoAdapter(Context context, List<Alumno> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.alumnos_diseno_sin_fragment_java, parent, false);
        }
        Alumno alumno = list.get(position);
        TextView nombre = convertView.findViewById(R.id.textViewNombre);
        TextView apellidos = convertView.findViewById(R.id.textViewApellidos);
        TextView telefono = convertView.findViewById(R.id.textViewTelefono);
        TextView email = convertView.findViewById(R.id.textViewEmail);
        ImageView foto = convertView.findViewById(R.id.listView_foto);

        nombre.setText(alumno.getNombre());
        apellidos.setText(alumno.getApellidos());
        telefono.setText(alumno.getTelefono());
        email.setText(alumno.getEmail());
        int posicion = alumno.getFoto();


        String ruta="http://www.puliafitoautopartes.com.ar/img/novedad/superpop-noti_vertical_ana-4.jpg";

        if(posicion!=0) {
            if (posicion == 1) {
                ruta = "http://4.bp.blogspot.com/-UpikwXY4_Nk/UmqCXWZVvWI/AAAAAAAAAPQ/ROunLwblO_I/s1600/ehyttyj.jpg ";
            } else if (posicion == 2) {
                ruta = "https://ytudedondevienes.files.wordpress.com/2014/04/cintia-moreno.jpg";
            } else if (posicion == 3) {
                ruta = "http://www.puliafitoautopartes.com.ar/img/novedad/superpop-noti_vertical_ana-4.jpg";
            } else if (posicion == 4) {
                ruta = "https://pepitomas.files.wordpress.com/2012/05/foto-carnet-pepi-002.jpg";
            } else if (posicion == 5) {
                ruta = "http://i685.photobucket.com/albums/vv213/Apohell2/Blog/Articulos/Aniversario/037-Carnet.jpg";
            } else if (posicion == 6) {
                ruta = "https://st-listas.20minutos.es/images/2010-05/217319/2352756_640px.jpg?1273604195";
            } else if (posicion == 7) {
                ruta = "https://fotos.pisocompartido.com/fotos/2017/08/08/F663786/59899034172c4-foto-carnet.jpg";
            } else if (posicion == 8) {
                ruta = "https://www.wanuncios.com/uploads/4/144/12893-1.jpg";
            }


            Glide.with(convertView)
                    .load(ruta)
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(foto);


        } else{foto.setImageResource(R.drawable.foto0);

        }

        return convertView;
    }
}