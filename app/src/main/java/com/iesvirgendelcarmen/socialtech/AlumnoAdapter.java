package com.iesvirgendelcarmen.socialtech;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.util.List;
public class AlumnoAdapter extends BaseAdapter {
    private Context context;
    private List<Alumno> list;
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
                    inflate(R.layout.alumnos, parent, false);
        }
        Alumno alumno = list.get(position);
        TextView nombre = convertView.findViewById(R.id.textViewNombre);
        TextView apellidos = convertView.findViewById(R.id.textViewApellidos);
        TextView email = convertView.findViewById(R.id.textViewEmail);
        nombre.setText(alumno.getNombre());
        apellidos.setText(alumno.getApellidos());
        email.setText(alumno.getEmail());
        return convertView;
    }
}