
import javaapplication6.Ficha;

public class Tablero{
    private ListaDoblementeEnlazada<Ficha> tablero;

    public Tablero(){
        tablero = new ListaDoblementeEnlazada<Ficha>();
    }

    public String toString(){
        return tablero.toString();
    }

    public int probarColocacion(Ficha ficha){
        int tamTablero = tablero.getLength();
        int posiblesPosiciones = 0;
        if(tamTablero > 0){
            Ficha extremoIzq = tablero.obtenerElementoEnPosicion(0);
            Ficha extremoDer = tablero.obtenerElementoEnPosicion(tamTablero-1);
            for(int i = 0; i < 2; i++){
                if(extremoIzq.getFicha()[0] == ficha.getFicha()[i]){
                    posiblesPosiciones += 2;
                }
                if(extremoDer.getFicha()[1] == ficha.getFicha()[i]){
                    posiblesPosiciones += 10;
                }
            }
        }
        return posiblesPosiciones;
    }

    public boolean colocarFicha(Ficha ficha){
        int lados = probarColocacion(ficha);
        if(tablero.getLength() == 0){
            tablero.agregarAlInicio(ficha);
        }
        else if(lados > 0 && lados < 5){
            if(ficha.getFicha()[1] != tablero.obtenerElementoEnPosicion(0).getFicha()[0]){
                ficha.voltearFicha();
            }
            tablero.agregarAlInicio(ficha);
        }
        else if(lados >= 10){
            int tamTablero = tablero.getLength();
            if(ficha.getFicha()[0] != tablero.obtenerElementoEnPosicion(tamTablero-1).getFicha()[1]){
                ficha.voltearFicha();
            }
            tablero.agregarAlFinal(ficha);
        }
        return lados > 0;
    }
}