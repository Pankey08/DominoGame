import javaapplication6.Ficha;
import java.text.ParseException;

import javax.swing.JOptionPane;

//creamos nuestro objeto jugador
public class Jugador {
    private ListaSimplementeEnlazada<Ficha> fichas;
    private Tablero tablero;
    private PilaFichas pila;
    private String nombre;
    private int puntos;

    public Jugador(){
        fichas = new ListaSimplementeEnlazada<Ficha>();
    }

    public Jugador(String nombre){
        fichas = new ListaSimplementeEnlazada<Ficha>();
        this.nombre = nombre;
        puntos = 0;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPila(PilaFichas pila){
        this.pila = pila;
    }

    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }

    public String getNombre(){
        return nombre;
    }

    public void agregarFicha(Ficha ficha){
        fichas.agregarAlFinal(ficha);
    }

    public void limpiarFichas(){
        fichas = new ListaSimplementeEnlazada<Ficha>();
    }

    public String toString(){
        return "Jugador: " + nombre + "\n" + fichas.obtenerNumerado() + "\nTablero actual:\n"+tablero.toString();
    }

    public boolean gano(){
        return fichas.getLength() == 0;
    }
    //verificamos que la ficha no este colocada en el tablero de juego
    public boolean existeJugada(){
        boolean hayJugada = false;
        int i = 0;
        int tam = fichas.getLength();
        while(i < tam && !hayJugada){
            hayJugada = tablero.probarColocacion(fichas.obtenerElementoEnPosicion(i)) > 0;
            i++;
        }
        return hayJugada;
    }

    //el jugador come fichas en caso de no terner ninguna para jugar si
    //las fichas estan puestas en el tablero no se usan
    public void comerFichas(){
        Ficha nueva;
        while((nueva = pila.getSiguiente()) != null && !existeJugada()){
            JOptionPane.showMessageDialog(null, nombre+" no posee movimientos, por lo que come: "+ nueva);
            fichas.agregarAlFinal(nueva);
        }
    }    

    public Ficha jugar(){
        Ficha ficha = null;
        boolean fichaValida = false;
        int numFicha = -1;
        comerFichas();
        do{
            String valor = JOptionPane.showInputDialog(this.toString()+ "\nElija una ficha o digite 'paso' para finalizar el turno");
            if(valor.toLowerCase().equals("paso")){
                fichaValida = true;
            }
            else{
                try{
                    numFicha = Integer.parseInt(valor);
                    ficha = fichas.obtenerElementoEnPosicion(numFicha-1);
                    if(ficha !=null){
                        fichaValida = tablero.colocarFicha(ficha);
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "La ficha digitada es inválida, por favor intente de nuevo");
                }
            }
        }while(!fichaValida);
        fichas.borrar(numFicha-1);
        return ficha;
    }



    public void reiniciarPuntaje(){
        puntos = 0;
    }

    //se suman los puntos
    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }

    //se cuentan los puntos para decidir por un ganador
    public int contarPuntos(){
        int puntos = 0;
        int tam = fichas.getLength();
        for(int i = 0; i < tam; i++){
            puntos += fichas.obtenerElementoEnPosicion(i).sumarValores();
        }
        return puntos;
    }

    //ficha más grande y que sea doble
    private int getPosicionDobleMayor(){
        int posicion = -1;
        Ficha mayor = null;
        int tam = fichas.getLength();
        for(int  i = 0; i < tam; i++){
            Ficha ficha = fichas.obtenerElementoEnPosicion(i);
            if(ficha.esDoble() && (mayor == null || mayor.getFicha()[0] < ficha.getFicha()[1])){
                mayor = ficha;
                posicion = i;
            }
        }
        return posicion;
    }

    public Ficha tieneDobleFicha(){
        int posicion = getPosicionDobleMayor();
        Ficha mayor = posicion == -1 ? null : fichas.obtenerElementoEnPosicion(posicion);
        return mayor;
    }

    public void consumirFichaDoble(){
        int posicion = getPosicionDobleMayor();
        fichas.borrar(posicion);
    }
}
