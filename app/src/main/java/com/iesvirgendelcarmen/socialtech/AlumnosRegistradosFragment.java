package com.iesvirgendelcarmen.socialtech;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private OnEventoSeleccionado callback;
    private AlumnoAdapter alumnoAdapter;
    private int itenSeleccionado;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (OnEventoSeleccionado) context;

    }


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
        listaAlumnos = new ArrayList<Alumno>();
        listaAlumnos = ((MainActivity) getActivity()).getListaAlumnos();
        alumnoAdapter = new AlumnoAdapter(getActivity(), listaAlumnos);
        listView.setAdapter(alumnoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.eventoSeleccionado(listaAlumnos.get(position));
            }
        });




        registerForContextMenu(listView);
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



