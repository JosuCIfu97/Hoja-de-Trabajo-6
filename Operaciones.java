
import java.util.Iterator;
import java.util.Set;
import java.io.Console;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.logging.ConsoleHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Operaciones {
    
    private Set Conjunto;
    
    Operaciones(int opcion){
        SetFactory factory = new SetFactory();
        Conjunto = factory.GetSet(opcion);
    }
    
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @param ConjuntoC
     * @return Conjunto con elementos presentes en los tres conjuntos de los parámetros.
     */
    public Set interseccion(Set ConjuntoA, Set ConjuntoB, Set ConjuntoC){
     Conjunto.clear();
     Conjunto.addAll(ConjuntoA);
     Conjunto.retainAll(ConjuntoB);
     Conjunto.retainAll(ConjuntoC);
     return Conjunto;
 }   
 
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @return Conjunto con elementos presentes en los dos conjuntos de los parámetros.
     */
    public Set interseccion(Set ConjuntoA, Set ConjuntoB){
     Conjunto.clear();
     Conjunto.addAll(ConjuntoA);
     Conjunto.retainAll(ConjuntoB);
     return Conjunto;
 }
 
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @return Conjunto con todos los elementos presentes en los conjuntos de los parámetros.
     */
    public Set union(Set ConjuntoA, Set ConjuntoB){
     Conjunto.clear();
     Conjunto.addAll(ConjuntoA);
     Conjunto.addAll(ConjuntoB);
     return Conjunto;
 }
 
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @return Conjunto que posee todos los elementos del conjunto A que no están en B.
     */
    public Set resta(Set ConjuntoA, Set ConjuntoB){
     Conjunto.clear();
     Conjunto.addAll(ConjuntoA);
     Conjunto.removeAll(ConjuntoB);
     return Conjunto;
 }
 
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @return Indica si el conjunto A es un subconjunto del conjunto B.
     */
    public boolean subconjunto(Set ConjuntoA, Set ConjuntoB){
     return ConjuntoB.containsAll(ConjuntoA);
 }
 
    /**
     *
     * @param ConjuntoA
     * @param ConjuntoB
     * @param ConjuntoC
     * @return Devuelve la letra que representa al conjunto con mayor elementos.
     */
    public String conjuntoMayor(Set ConjuntoA, Set ConjuntoB, Set ConjuntoC){
     String mayor;
     if (ConjuntoA.size() >= ConjuntoB.size()){
         mayor = "A";
         if (ConjuntoA.size() < ConjuntoC.size()){
             mayor = "C";
         }
     }
     else{
         mayor = "B";
         if (ConjuntoB.size() < ConjuntoC.size())
             mayor = "C";
     }
     return mayor;
 }
 
    /**
     *
     * @param ConjuntoA
     */
    public void imprimirConjunto(Set ConjuntoA){
     Iterator itr = ConjuntoA.iterator();
     while (itr.hasNext()){
         Object Element = itr.next();
         System.out.println(Element);
     }
 }
}
