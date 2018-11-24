package com.iesvirgendelcarmen.socialtech;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Foto {
    @SerializedName("num_foto")
private int num_foto;

    public Foto() {
    }

    public Foto(int num_foto) {
        this.num_foto = num_foto;
    }

    public int getNum_foto() {
        return num_foto;
    }

    public void setNum_foto(int num_foto) {
        this.num_foto = num_foto;
    }

    /*
        public static List<Pelicula> mockPeliculas() {
            List<Pelicula> pelis = new ArrayList<Pelicula>();

            Pelicula p1 = new Pelicula();
            p1.setId(1);
            p1.setTitulo("Animales fantásticos: Los crímenes de Grindelwald");
            p1.setPortada("/zs6LFuE4aB1I8crKjAhlPVTHAOS.jpg");
            p1.setSinopsis("");

            pelis.add(p1);

            p1 = new Pelicula();
            p1.setId(2);
            p1.setTitulo("Misión imposible: Fallout");
            p1.setPortada("/aw4FOsWr2FY373nKSxbpNi3fz4F.jpg");
            p1.setSinopsis("");

            pelis.add(p1);

            return pelis;

        }
        */
    }
