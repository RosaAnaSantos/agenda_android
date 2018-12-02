package com.iesvirgendelcarmen.socialtech;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private OnEventoSeleccionado callback;
    private AlumnoAdapter alumnoAdapter;
    private int itenSeleccionado;

    public AlumnosRegistradosFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (OnEventoSeleccionado) context;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.alumnos_registrados_listview, container, false);
        ButterKnife.bind(this, vista);
        anadeListView(vista);

        return vista;

    }
    public void anadeListView(View view) {
        ListView listView = view.findViewById(R.id.listView_alumnos);
        listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
        alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("alumnos");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    Alumno alumno = dataSnapshot.getValue(Alumno.class);
                    listaAlumnos.add(alumno);
                    alumnoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


*/


        listView.setAdapter(alumnoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.eventoSeleccionado(listaAlumnos.get(position));
            }
        });




        registerForContextMenu(listView);
    }





    public interface OnEventoSeleccionado {
        public void eventoSeleccionado(Alumno alumno);
    }





    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_lista_alumnos, menu);
    }
    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ver_alumno:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                callback.eventoSeleccionado(listaAlumnos.get(info.position));
                return true;
            case R.id.eliminar_alumno:

                AlertDialog.Builder dialogoContacto = new AlertDialog.Builder(getContext());
                dialogoContacto.setTitle("¿DESEA ELIMINAR ESTE ALUMNO?");
                dialogoContacto.setCancelable(false);

                dialogoContacto.setPositiveButton("SI" +
                        "", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo infor = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        listaAlumnos.remove(listaAlumnos.get(infor.position));
                        alumnoAdapter.notifyDataSetChanged();


                    }
                });

                dialogoContacto.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialogoContacto.show();
                return true;

            case R.id.editar_alumno:
                AdapterView.AdapterContextMenuInfo inf = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ((MainActivity) getActivity()).cambiarFragmento(new FormularioEditar());

                ((MainActivity) getActivity()).valor=inf.position;

                Toast.makeText(getContext(), (inf.position)+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),  ((MainActivity) getActivity()).valor+"---", Toast.LENGTH_SHORT).show();
                return  true;

        }

        return super.onContextItemSelected(item);
    }




}



