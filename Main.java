
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josue
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Bienvenido al programa de colección de desarrolladores Java, Web o de celulares");
        
        //Creación de objetos
        Set WebDesarrolladores;
        Set JavaDesarrolladores;
        Set CelularDesarrolladores;
        Set Solucion;
        Set Solucion2;
        Operaciones op;
        SetFactory factory = new SetFactory();
        Scanner teclado = new Scanner(System.in);
        int patron = 0;
        System.out.println("Escoja el patrón de diseño que desea utilizar para las colecciones:");
        while (!(patron == 1 | patron == 2 | patron == 3)){
            patron = menuPatron();
            if (!(patron == 1 | patron == 2 | patron == 3))
                System.out.println("ERROR. Opción inválida.");
        }
        //Se selecciona la implementación de Set.
        op = new Operaciones(patron);
        JavaDesarrolladores = factory.GetSet(patron);
        WebDesarrolladores = factory.GetSet(patron);
        CelularDesarrolladores = factory.GetSet(patron);
        Solucion = factory.GetSet(patron);
        Solucion2 = factory.GetSet(patron);
        
        //Solicitud de nombres y selección de área de experiencia.
        //Comentar la siguiente sección y descomentar la del profiler si quieren medirse tiempos con datos al azar.
        System.out.println("Ahora se le solicitará el nombre del desarrollador y que indique en qué ramas trabaja el mismo.");
        int seguir = 1;
        while (seguir == 1){
            seguir = 0;
            System.out.println("Ingrese el nombre del desarrollador:");
            String nombre = teclado.nextLine();
            int java = 0;
            int web = 0;
            int cel = 0;
            while (!(java == 1 | java == 2)){
                java = menuDesarrollo(nombre, "Java");
                if (java == 1)
                    JavaDesarrolladores.add(nombre);
                if (!(java == 1 | java == 2)){
                    System.out.println("ERROR. Opción inválida.");
                }
            }
            while (!(web == 1 | web == 2)){
                web = menuDesarrollo(nombre, "Web");
                if (web == 1)
                    WebDesarrolladores.add(nombre);
                if (!(web == 1 | web == 2)){
                    System.out.println("ERROR. Opción inválida.");
                }
            }
            while (!(cel == 1 | cel == 2)){
                cel = menuDesarrollo(nombre, "Celular");
                if (cel == 1)
                    CelularDesarrolladores.add(nombre);
                if (!(cel == 1 | cel == 2)){
                    System.out.println("ERROR. Opción inválida.");
                }
            }
            while (!(seguir == 1 | seguir == 2)){
                seguir = seguirAgregando();
                if (!(seguir == 1 | seguir == 2))
                    System.out.println("ERROR. Opción inválida.");
            }
        }
        
        //Operaciones
        System.out.println("Desarrolladores con experiencia en Java, Web y Celulares:");
        Solucion.addAll(op.interseccion(JavaDesarrolladores, WebDesarrolladores, CelularDesarrolladores)); //Agrega al conjunto solución la triple intersección de conjuntos.
        op.imprimirConjunto(Solucion); //Imprime elementos del conjunto solucion de manera ascendente.
        System.out.println("Desarrolladores con experiencia en Java pero no en Web:");
        Solucion.clear(); //Se eliminan todos los elementos del conjunto para empezar de cero con el siguiente proceso.
        Solucion.addAll(op.resta(JavaDesarrolladores, WebDesarrolladores)); //Se agrega la resta del conjunto A - conjunto B.
        op.imprimirConjunto(Solucion);
        System.out.println("Desarrolladores con experiencia en Web y Celulares pero no en Java:");
        Solucion.clear();
        Solucion.addAll(op.interseccion(WebDesarrolladores, CelularDesarrolladores)); //Se agrega la intersección de conjunto A y B.
        Solucion2.addAll(op.resta(Solucion, JavaDesarrolladores)); //A 2ndo objeto se le agrega la resta de la primera solución con el conjunto Java.
        op.imprimirConjunto(Solucion2);
        System.out.println("Desarrolladores con experiencia en Web o Celulares pero no en Java:");
        Solucion.clear();
        Solucion.addAll(op.union(WebDesarrolladores, CelularDesarrolladores)); //Unión de conjuntos A y B.
        Solucion2.clear();
        Solucion2.addAll(op.resta(Solucion, JavaDesarrolladores));
        op.imprimirConjunto(Solucion2);
        System.out.println("¿Es Desarrolladores Java un subconjunto de Desarrolladores Web ?");
        String ans;
        boolean sub = op.subconjunto(JavaDesarrolladores, WebDesarrolladores);
        if (sub)
            ans = "SI";
        else
            ans = "NO";
        System.out.println(ans + ", Java Desarrolladores " + ans + " es un subconjunto de Desarrolladores Web");
        //Conjunto mayor1
        String mayor = op.conjuntoMayor(JavaDesarrolladores, WebDesarrolladores, CelularDesarrolladores);
        switch (mayor) {
            case "A":
                Solucion = JavaDesarrolladores; //Puntero solución ahora señala a puntero del conjunto Java.
                mayor = "Java";
                break;
            case "B":
                Solucion = WebDesarrolladores; //Puntero solución ahora señala a puntero del conjunto Web.
                mayor = "Web";
                break;
            case "C":
                Solucion = CelularDesarrolladores; //Puntero solución ahora señala a puntero del conjunto Celular.
                mayor = "Celular";
                break;
        }
        System.out.println("El conjunto con mayor cantidad de desarrolladores es: " + mayor);
        System.out.println("Los desarrolladores del conjunto mayor " + mayor + " son: ");
        op.imprimirConjunto(Solucion);
        System.out.println("Gracias por utilizar el programa. Vuelva pronto");
        
    }
    
    //Método para que el usuario indique la implementación de Set
    private static int menuPatron(){
        System.out.println("Ingrese el número de la opción:");
        System.out.println("1. Hash Set");
        System.out.println("2. Tree Set");
        System.out.println("3. Linked Hash Set");
        Scanner teclado = new Scanner(System.in);
        int patron;
        try{
            patron = teclado.nextInt();
        }
        catch(InputMismatchException e){
            return 0;
        }
        return patron;
    }
    
    //Método para indicar si el desarrollador 'nombre' posee experiencia en el ambiente 'desarrollo'
    private static int menuDesarrollo(String nombre, String desarrollo){
        System.out.println("¿ " + nombre + " es desarrollador " + desarrollo + "?");
        System.out.println("Ingrese el número de la opción:");
        System.out.println("1. Sí");
        System.out.println("2. No");
        Scanner teclado = new Scanner(System.in);
        int decision;
        try{
            decision = teclado.nextInt();
        }
        catch(InputMismatchException e){
            return 0;
        }
        return decision;
    }
    
    //Método para seguir agregando nombres.
    private static int seguirAgregando(){
        System.out.println("¿Desea seguir agregando desarrolladores?");
        System.out.println("Ingrese el número de la opción:");
        System.out.println("1. Sí");
        System.out.println("2. No");
        Scanner teclado = new Scanner(System.in);
        int decision;
        try{
            decision = teclado.nextInt();
        }
        catch(InputMismatchException e){
            return 0;
        }
        return decision; 
    }
}
