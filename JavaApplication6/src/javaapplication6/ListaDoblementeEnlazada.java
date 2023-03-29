public class ListaDoblementeEnlazada<T> {
    
    private Nodo primero;
    private Nodo ultimo;
    private int tam;

    private class Nodo{
        private T valor;
        private Nodo siguiente;
        private Nodo anterior;

        public Nodo(T valor){
            this.valor = valor;
            this.anterior = null;
            this.siguiente = null;
        }

        public String toString(){
            return valor + " ";
        }
    }
    public T obtenerElementoEnPosicion(int posicion){
        Nodo actual = obtenerNodoEnPosicion(posicion);
        return actual != null ? actual.valor : null;
    }

    public ListaDoblementeEnlazada(){
        this.primero = this.ultimo = null;
        tam = 0;
    }

    public int getLength(){
        return tam;
    }

    public void agregarAlInicio(T elemento){
        Nodo nuevo = new Nodo(elemento);
        Nodo primeroViejo = this.primero;
        nuevo.siguiente = primeroViejo;
        primero = nuevo;
        tam++;
        if(ultimo == null){
            ultimo = primero;
        }
        else{
            primeroViejo.anterior = nuevo;
        }
    }

    public void agregarAlFinal(T elemento){
        Nodo nuevo = new Nodo(elemento);
        tam++;
        if(primero == null){
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }
    }

    public boolean insertar(int posicion, T elemento){
        boolean insertado = false;
        if(posicion == 0){
            agregarAlInicio(elemento);
            insertado = true;
        }
        else if(posicion > 0 && primero != null){
            Nodo anterior = obtenerNodoEnPosicion(posicion-1);
            if(anterior != null){
                Nodo nuevo = new Nodo(elemento);
                nuevo.siguiente = anterior.siguiente;
                anterior.siguiente.anterior = nuevo;
                anterior.siguiente = nuevo;
                nuevo.anterior = anterior;
                insertado = true;
                tam++;
                if(anterior == ultimo){
                    ultimo = nuevo;
                }
            }
        }
        return insertado;
    }

    public Nodo obtenerNodoEnPosicion(int posicion){
        int posicionActual = 0;
        Nodo actual = primero;
        while(posicionActual != posicion && actual != null){
            posicionActual ++;
            actual = actual.siguiente;
        }
        return actual;
    }

    public boolean borrar(int posicion){
        boolean borrado = true;
        Nodo borrar = obtenerNodoEnPosicion(posicion);
        if(primero != null && borrar != null){
            if(primero == borrar){
                primero = primero.siguiente;
            }
            if(borrar.siguiente != null){
                borrar.siguiente.anterior = borrar.anterior;
            }
            if(borrar.anterior != null){
                borrar.anterior.siguiente = borrar.siguiente;
            }
            if(borrar == ultimo){
                ultimo = ultimo.siguiente;
            }
            tam--;
        }
        else{
            borrado = false;
        }
        return borrado;
    }

    public String toString(){
        String contenido = "";
        Nodo actual = this.primero;
        while(actual != null){
            contenido += actual;
            actual = actual.siguiente;
        }
        return contenido;
    }

    public String imprimirReverso(){
        String contenido = "";
        Nodo actual = ultimo;
        while(actual != null){
            contenido += actual;
            actual = actual.anterior;
        }
        return contenido;
    }

    public boolean setValor(int posicion, T valor){
        boolean asignado = false;
        Nodo nodo = obtenerNodoEnPosicion(posicion);
        if(nodo != null){
            nodo.valor = valor;
        }
        return asignado;
    }

    public int buscar(T elemento){
        Nodo actual = primero;
        int posicionActual = 0;
        while(actual != null && actual.valor != elemento){
            actual = actual.siguiente;
            posicionActual++;
        }
        return (actual != null) ? posicionActual : -1;
    }

    public int borrarElementos(T elemento){
        int borrados = 0;
        int posicion = buscar(elemento);
        while(posicion != -1){
            // borrar
            if(borrar(posicion)){
                borrados ++;
            }
            posicion = buscar(elemento);
        }
        return borrados;
    }

    /*
    solo es para probar que los metodos de las lsitas esten funcionando
    aunque hay metodos que no se usan dentro de nuestro juego de domino como es el 
    caso de borrar, se van a quedar para futuras actualizaciones del juego 
    ya que no implican que bajen el rendimiento del mismo, no se van a quitar
    
    
    public static void main(String args[]){
      ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<Integer>();
      lista.agregarAlInicio(1);
      lista.agregarAlInicio(2);
      lista.agregarAlInicio(3);
      lista.agregarAlFinal(4);
      lista.agregarAlFinal(5);
      lista.agregarAlFinal(6);
      lista.agregarAlFinal(6);
      lista.agregarAlFinal(6);
      lista.agregarAlFinal(6);
      lista.insertar(1, 55);
      System.out.println(lista);
      lista.borrar(1);
      System.out.println(lista);
      System.out.println(lista.imprimirReverso());
      System.out.println("Tam: "+ lista.getLength());
      lista.borrarElementos(6);
      System.out.println(lista);
    }

*/
}
