import javaapplication6.Ficha;
import javax.swing.JOptionPane;
public class Juego {
    private Tablero tablero;
    private PilaFichas pila;
    //nuestra lista enlazda contiene nodos de tipo jugador
    private ListaSimplementeEnlazada<Jugador> jugadores;
    private int primerJugador;

    //juego va  arecibir un numero entero positivo para crear la cantidad de jugadores 
    //la forma en la que se agregan jugadores es mediante una lista simple enlazada
    //que va a guardar las diferentes instancias de nuestro objeto jugador 
    //de esta manera poodemos tener hasta un maximo de 4 jugadores todos con las mismas 
    //caracteristicas
    public Juego(int cantJugadores){
        tablero = new Tablero();
        pila = new PilaFichas();
        jugadores = new ListaSimplementeEnlazada<Jugador>();
        //cantJugadores = JOptionPane.showInputDialog("por favor ingresa la cantidad de jgadores: ");
        for(int i = 0 ; i < cantJugadores; i++){
            jugadores.agregarAlFinal(new Jugador(JOptionPane.showInputDialog("Digite el nombre del jugador"+(i+1))));
        }
        primerJugador = 0;
    }

    public void iniciarJuego(){
        int cantidadJugadores = jugadores.getLength();
        Ficha fichaDoble = null;
        //si nadie tiene ficha doble, los jugadores comeran hasta tener una 
        do{
            pila.crearFichas();
            pila.revolver();
            tablero = new Tablero();
            
            //iteramos por cada uno de los jugadores, por cada jugar que existe se le asdgna la pila de fichas
            for(int i = 0; i < cantidadJugadores; i++){
                Jugador referenciaJugador = jugadores.obtenerElementoEnPosicion(i);
                referenciaJugador.setPila(pila);
                referenciaJugador.setTablero(tablero);
                referenciaJugador.limpiarFichas();
                
                //comenzamos a repartir 6 fichas por jugador
                for(int j = 0; j < 6 ; j++){
                    referenciaJugador.agregarFicha(pila.getSiguiente());
                }
                //revisamos quien es el possedor de la ficha doble más grande
                Ficha doble = referenciaJugador.tieneDobleFicha();
                if(doble != null){
                    if(fichaDoble == null || fichaDoble.getFicha()[0] < doble.getFicha()[0]){
                        fichaDoble = doble;
                        //este sera el primer jugador
                        primerJugador = (i) % cantidadJugadores;
                    }
                }
            }
            //si no podemos hacer este ciclo, se vuelven a crear la fichas y a repartir hasta que encontremos
            //al jugador que tenga la mayor ficha doble del juego actual
        }while(fichaDoble == null);
        tablero.colocarFicha(fichaDoble);
        jugadores.obtenerElementoEnPosicion(primerJugador).consumirFichaDoble();
        primerJugador++;
    }

    public void jugar(){
        iniciarJuego();
        int ganador = 0;
        int jugadorActual = primerJugador;
        int turno = 1;
        int tam = jugadores.getLength();
        while(ganador == 0 && !juegoBloqueado()){
            while(ganador == 0 && jugadorActual < tam){
                Jugador actual = jugadores.obtenerElementoEnPosicion(jugadorActual);
                actual.jugar();
                ganador = actual.gano() ? jugadorActual : 0;
                jugadorActual++;
                turno++;
            }
            jugadorActual = 0;
        }
        if(ganador != 0){
            JOptionPane.showMessageDialog(null, jugadores.obtenerElementoEnPosicion(ganador).getNombre() + " gano!!!");
        }
        else{
            String ganaNombre = "";
            int puntosMenor = 0;
            while(jugadorActual < tam){
                int pts = jugadores.obtenerElementoEnPosicion(jugadorActual).contarPuntos();
                if(puntosMenor == 0 || pts < puntosMenor){
                    puntosMenor = pts;
                    ganaNombre = jugadores.obtenerElementoEnPosicion(jugadorActual).getNombre();
                }
                jugadorActual++;
            }
            JOptionPane.showMessageDialog(null, ganaNombre + " gana! Por menor cantidad de puntos en las fichas");
        }
    }

    public boolean juegoBloqueado(){
        boolean juegoFinalizado = false;
        int jugadorActual = 0;
        int tam = jugadores.getLength();
        while(jugadorActual < tam){
            Jugador actual = jugadores.obtenerElementoEnPosicion(jugadorActual);
            juegoFinalizado |= !actual.existeJugada();
            jugadorActual++;
        }
        juegoFinalizado &= pila.getFichasRestantes() == 0; //  juegoFinalizado =  juegoFinalizado && pila.getFichasRestantes() == 0
        return juegoFinalizado;
    }
    
    public static void menu(int numeroJugadores){
    
        String opcion = "";
        int numero;
        boolean opcionValida = false;
        int totalJugadores = 2;
        
        do{

            try {
                
                do {
                    
                    opcion = JOptionPane.showInputDialog("Bienvenido a Domino\n"
                    + "\n--Para poder visualizar las reglas del juego por favor digite 1\n"
                    + "--para empezar a jugar Por favor digite 2"
                            + "\n--o bien para salir presione 3");
	
	
	                numero = Integer.parseInt(opcion);
	
	                if (!(numero > 0 && numero < 4)){
	                 
	                	
		                JOptionPane.showMessageDialog(null, "La opción ingresada no es valida, por favor digite únicamente 1, 2 o 3");
		
	//	                numero = Integer.parseInt(opcion);
	                
	                } 
            	} while(!(numero > 0 && numero < 4));
                if (numero == 1){

                    //mostramos las reglas del juego
                    JOptionPane.showMessageDialog(null, "Bienvenido al juego de Domino\n\n"
                     + "las reglas son muy basicas\n"
                     + "--Al inicio del juego, se reparten seis fichas a cada jugador.\n"
                     + "--El jugador que tenga la ficha doble mayor inicia el juego.\n"
                     + "--Cada jugador debe tratar de colocar una ficha que tenga un\n "
                     + "extremo que coincida con el número de extremo abierto en la mesa.\n"
                     + "--Si un jugador no tiene fichas que puedan ser jugadas, debe tomar \n"
                     + "una del montón de fichas restantes.\n"
                     + "--Si el montón de fichas se agota y ningún jugador puede continuar\n"
                     + " el juego termina en empate.\n"
                     + "--El primer jugador en quedarse sin fichas gana la ronda.\n\n"
                            + "¡Vamos a jugar!", "Reglas", JOptionPane.INFORMATION_MESSAGE);
                    
                    //iniciamos el juego
                    
                    totalJugadores = Juego.numeroJugadores(2);
                    Juego juego = new Juego(totalJugadores);
                    juego.jugar();
                    
                    opcionValida = true;
                } else if (numero == 2){
                    
                    totalJugadores = Juego.numeroJugadores(2);
                    Juego juego = new Juego(totalJugadores);
                    juego.jugar();
                    
                    //acabamos con todos los procesos que se esten ejecutando
                } else if (numero == 3){
                
                    System.exit(0);
                }

            } catch (NumberFormatException e) {
                // Si la entrada no se puede convertir a un entero, mostrar un mensaje de error
                System.out.println("La entrada no es un número entero válido.");

                    JOptionPane.showMessageDialog(null,"Bienvenido a Domino\n\n"
                    + "la opcion ingresada no es valida, por favor digite unicamente 1, 2 o 3 ", 
                            "Aviso",JOptionPane.INFORMATION_MESSAGE );
            }
        } while(!opcionValida);
        
    }
    
    public static int numeroJugadores(int numeroJugadores){
    
        int numeroJugadores1;
        boolean opcionValida = false;
        String opcion = "";
        
        do{
            
            try{
                opcion = JOptionPane.showInputDialog("Ingresa el numero de jugadores\n\n"
                        + "este numero tiene que ser mas grande que uno y menor o igual a 4\n"
                        + "dando un maximo de hasta 4 jugadores");


                numeroJugadores = Integer.parseInt(opcion);

                //en caso de que la opcion no exista se repite el ciclo

                while(numeroJugadores <=1 || numeroJugadores >= 5){
                opcion = JOptionPane.showInputDialog("Numero de jugadores incorrecto \npor favor ingresa un numero valido\n\n"
                        + "este numero tiene que ser mas grande que uno y menor que 4\n");

                   numeroJugadores = Integer.parseInt(opcion);
                } 
                opcionValida = true;
            
            }catch(NumberFormatException e){
                // Si la entrada no se puede convertir a un entero, mostrar un mensaje de error
                System.out.println("La entrada no es un número entero válido.");

                JOptionPane.showMessageDialog(null,"la opcion ingresada no es valida, por favor digite "
                        + "unicamente un numero mayor que 1 y menor que 4 ", 
                            "Aviso",JOptionPane.INFORMATION_MESSAGE );
            
            }
        
            
        }while(!opcionValida);
        
        return numeroJugadores;
    }

    public static void main(String [] args){
                
       menu(2);
        
    }
}
