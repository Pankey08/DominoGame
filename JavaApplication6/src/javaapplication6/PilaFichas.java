
import javaapplication6.Ficha;

public class PilaFichas{
    private ListaSimplementeEnlazada<Ficha>fichas;

    public PilaFichas(){
        crearFichas();
    }

    public void crearFichas(){
        fichas = new ListaSimplementeEnlazada<Ficha>();
        for(int i = 0 ; i <= 6; i++){
            for(int j = 0; j <= i; j++){
                fichas.agregarAlFinal(new Ficha(i, j));
            }
        }
    }

    public String toString(){
        return fichas.toString();
    }

    public int getFichasRestantes(){
        return fichas.getLength();
    }

    public Ficha getSiguiente(){
        Ficha ficha = fichas.getLength() > 0 ? fichas.obtenerElementoEnPosicion(0) : null;
        fichas.borrar(0);
        return ficha;
    }

    public void revolver(){
        int tamLista = fichas.getLength();
        for(int i = 0; i < tamLista; i++){
            fichas.intercambiar(i, (int)(Math.random()*tamLista));
        }
    }

    public static void main(String args[]){
        PilaFichas pila = new PilaFichas();
        System.out.println(pila);
        pila.revolver();
        System.out.println();
        System.out.println(pila);
    }
}