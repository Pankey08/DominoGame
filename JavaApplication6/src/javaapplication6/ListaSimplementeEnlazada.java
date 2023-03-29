public class ListaSimplementeEnlazada<T> {
    
    private Nodo primero;

    private class Nodo{
        private T valor;
        private Nodo siguiente;

        public Nodo(T valor){
            this.valor = valor;
            this.siguiente = null;
        }

        public String toString(){
            return this.valor + " ";
        }

    }

    public ListaSimplementeEnlazada(){
        primero = null;
    }


    public void agregarAlInicio(T elemento){
        //1. Crear un nodo
        //2. Actualizar las referencias -> agregar el siguiente
        //3. Actualizar el primer elemento
        Nodo nuevo = new Nodo(elemento);
        nuevo.siguiente = primero;
        primero = nuevo;
    }

    public void agregarAlFinal(T elemento){
        Nodo nuevo = new Nodo(elemento);
        if(primero == null){
            primero = nuevo;
        }
        else{
            // iterar hasta el ultimo elemento
            // actualizar la referencia al siguiente
            Nodo actual = primero;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
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

    public T obtenerElementoEnPosicion(int posicion){
        Nodo actual = obtenerNodoEnPosicion(posicion);
        return actual != null ? actual.valor : null;
    }

    public boolean intercambiar(int pos1, int pos2){
        boolean intecambiado = false;
        Nodo nodo1 = obtenerNodoEnPosicion(pos1);
        Nodo nodo2 = obtenerNodoEnPosicion(pos2);
        if(nodo1 != null && nodo2 != null){
            T temp = nodo1.valor;
            nodo1.valor = nodo2.valor;
            nodo2.valor = temp;
            intecambiado = true;
        }
        return intecambiado;
    }

    public int getLength(){
        int tam = 0;
        Nodo actual = primero;
        while(actual != null){
            tam++;
            actual = actual.siguiente;
        }
        return tam;
    }

    public boolean modificarNodo(int posicion, T valor){
        boolean modificado = false;
        Nodo nodo = obtenerNodoEnPosicion(posicion);
        if(nodo != null){
            nodo.valor = valor;
            modificado = true;
        }
        return modificado;
    }

    public boolean insertar(int posicion, T elemento){
        boolean insertado = false;
        if(posicion==0){
            agregarAlInicio(elemento);
            insertado = true;
        }
        else if(posicion > 0 && primero != null){
            Nodo anterior = obtenerNodoEnPosicion(posicion-1);
            if(anterior != null){
                Nodo nuevo = new Nodo(elemento);
                nuevo.siguiente = anterior.siguiente;
                anterior.siguiente = nuevo;
                insertado = true;
            }
        }

        return insertado;
    }

    public boolean borrar(int posicion){
        // Casos para borrar
        // Si es el primer elemento actualizamos el inicio
        // Si es un elemento intermedio
        // Si es un elemento final (el último)
        boolean borrado = false;
        if(posicion >= 0 && primero != null){
            if(posicion == 0){
                primero = primero.siguiente;
                borrado = true;
            }
            else{
                Nodo anterior = obtenerNodoEnPosicion(posicion-1);
                if(anterior != null){
                   Nodo borrar = anterior.siguiente;
                   if(borrar == null || borrar.siguiente == null){
                       anterior.siguiente = null;
                       borrar = null;
                   }
                   else{// Nodo intermedio
                        anterior.siguiente = borrar.siguiente;
                        borrar.siguiente = null;
                        borrar = null;
                   }
                   borrado = true;
                }
            }
        }
        return borrado;
    }

    public String obtenerNumerado(){
        String contenido ="";
        Nodo actual = primero;
        int contador = 1;
        while(actual != null){
            contenido += contador + "- " + actual.toString() + "\n";
            actual = actual.siguiente;
            contador++;
        }
        return contenido;
    }


    public String toString(){
        String contenido = "";
        Nodo actual = primero;
        while(actual != null){
            contenido += actual.toString();
            actual = actual.siguiente;
        }
        return contenido;
    }

    public static void main(String args[]){
      ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<Integer>();
      lista.agregarAlInicio(1);
      lista.agregarAlInicio(2);
      lista.agregarAlInicio(3);
      System.out.println(lista);
      lista.agregarAlFinal(4);
      lista.agregarAlFinal(5);
      lista.agregarAlFinal(6);
      System.out.println(lista);
      lista.insertar(1, 10);
      System.out.println(lista);
      lista.insertar(7, 7);
      System.out.println(lista);

      lista.borrar(1);
      System.out.println(lista);
      lista.borrar(0);
      System.out.println(lista);
      lista.borrar(5);
      System.out.println(lista);
      lista.borrar(-5);
      lista.borrar(50);
      System.out.println(lista);
      lista.modificarNodo(2, 50);
      System.out.println(lista);
      System.out.println("Tamaño: " + lista.getLength());
    }
}
