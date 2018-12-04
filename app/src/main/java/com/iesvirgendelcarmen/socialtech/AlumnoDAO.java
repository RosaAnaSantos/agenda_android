package com.iesvirgendelcarmen.socialtech;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.app.Activity;
        import android.os.Parcelable;
        import android.util.Log;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

public class AlumnoDAO implements Serializable {
    private ArrayList<Object> listaAlumnos = new ArrayList<Object>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;

    public void removeAlumno(Object alumno){
        listaAlumnos.remove(alumno);
        guardarListaAlumnosEnFirebase(listaAlumnos);
    }

    public ArrayList<Object> mostrarAlumnos(){
        return this.listaAlumnos;
    }

    public void actualizarAlumnos(ArrayList<Object> listaActualizada) { this.listaAlumnos = listaActualizada; }

    public void addPersonaFireBase(Alumno alumno, final AlumnoDAO dao, final MainActivity activity){
        Log.v("FirebaseEmail", "PersonaDAO:addEmpresaFireBase:Longitud lista:" + listaAlumnos.size());
        String posicionEnLaBd = listaAlumnos.size() + "";

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaPersonas").child(posicionEnLaBd).setValue(alumno);
        //myRef.setValue("Hello, World!");

        myRef.child("listaPersonas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseEmail", "PersonaDAO:addPersonaFirebase:DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaAlumnos = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseEmail", "AlumnoDAO:addAlumnoFirebase:onDataChange:Contenido lista en dao:"+mostrarAlumnos());
               // activity.cambiarFragmento(new Fragmento1(dao));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void guardarListaAlumnosEnFirebase(ArrayList<Object> listaPersonas){
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaAlumnos").setValue(listaAlumnos);
        //myRef.setValue("siiiiiiiiiiii");

        Log.v("FirebaseEmail", "AlumnoDAO:listaLongitud:" + listaAlumnos.size() + ":usuario:" + usuario.getEmail());
    }

}