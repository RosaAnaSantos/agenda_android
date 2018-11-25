package com.iesvirgendelcarmen.socialtech;

import android.content.Context;
import android.media.Image;
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




        String ruta="http://www.puliafitoautopartes.com.ar/img/novedad/superpop-noti_vertical_ana-4.jpg";


/*
        if(posicion==1){
          ruta="http://4.bp.blogspot.com/-UpikwXY4_Nk/UmqCXWZVvWI/AAAAAAAAAPQ/ROunLwblO_I/s1600/ehyttyj.jpg ";
        }
         else if(posicion==2){
            ruta="https://ytudedondevienes.files.wordpress.com/2014/04/cintia-moreno.jpg";

        }
        */

        Glide.with(convertView)
                .load(ruta)
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(foto);


     // foto.setImageResource(R.drawable.foto);


        return convertView;
    }
}