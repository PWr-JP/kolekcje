package tb.soft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class PersonsCollections {
    //kolekcje przechowujące obiekty typu Person
    static TreeSet<Person> TreeSetPersons = new TreeSet<>();
    static HashSet<Person> HashSetPersons = new HashSet<>();
    static LinkedList<Person> LinkedListPersons = new LinkedList<>();
    static ArrayList<Person> ArrayListPersons = new ArrayList<>();


    static String showTreeSet(){
        StringBuilder sb = new StringBuilder();
        for(Person p:TreeSetPersons){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
    static String showHashSet(){
        StringBuilder sb= new StringBuilder();
        for(Person p:HashSetPersons){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
    static String showArrayList(){
        StringBuilder sb= new StringBuilder();
        for(Person p:ArrayListPersons){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
    static String showLinkedList(){
        StringBuilder sb= new StringBuilder();
        for(Person p:LinkedListPersons){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

}
