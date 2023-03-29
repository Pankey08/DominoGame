package javaapplication6;

public class Ficha{
    
    //hacemos un arreglo de fichas
    private int [] ficha;

    //creamos nuestra ficha 
    public Ficha(int valor1, int valor2){
        ficha = new int [2];
        ficha[0] = valor1;
        ficha[1] = valor2;
    }

    //dibujamos la ficha
    public String toString(){
        return "[" + ficha[0] + "|" + ficha[1] + "]";
    }

    public int [] getFicha(){
        return ficha;
    }

    //se voltea la ficha para ser reposicionada en caso de necesitarlo
    public void voltearFicha(){
        int temp = ficha[0];
        ficha[0] = ficha[1];
        ficha[1] = temp;
    }

    //comprobamos que la ficha es doble, es decir si es una "mula"
    public boolean esDoble(){
        return ficha[0] == ficha[1];
    }

    //se suman los valores de las fichas para 
    //posteriormete elegir un ganador
    public int sumarValores(){
        return ficha[0] + ficha[1];
    }

    /* esto soloe s para rprovar que las fichas se estan 
    creando de manera correcta
    
    public static void main(String [] args){
        Ficha f1 = new Ficha(6, 5);
        System.out.println(f1);
    }
*/
}