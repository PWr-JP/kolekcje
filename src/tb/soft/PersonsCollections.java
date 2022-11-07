package tb.soft;

import java.util.*;

public class PersonsCollections {
    //kolekcje przechowujące obiekty typu Person
    static TreeSet<Person> TreeSetPersons = new TreeSet<>();
    static HashSet<Person> HashSetPersons = new HashSet<>();
    static LinkedList<Person> LinkedListPersons = new LinkedList<>();
    static ArrayList<Person> ArrayListPersons = new ArrayList<>();
    static TreeSet<PersonMod> TreeSetPersonsMod = new TreeSet<>();
    static HashSet<PersonMod> HashSetPersonsMod = new HashSet<>();
    static LinkedList<PersonMod> LinkedListPersonsMod = new LinkedList<>();
    static ArrayList<PersonMod> ArrayListPersonsMod = new ArrayList<>();


    static String showTreeSet(){
        StringBuilder sb = new StringBuilder();
        for(Person p:TreeSetPersons){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }


    static String showTreeSetMod(){
        StringBuilder sb = new StringBuilder();
        for(Person p:TreeSetPersonsMod){
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


    static String showHashSetMod(){
        StringBuilder sb= new StringBuilder();
        for(Person p:HashSetPersonsMod){
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


    static String showArrayListMod(){
        StringBuilder sb= new StringBuilder();
        for(Person p:ArrayListPersonsMod){
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


    static String showLinkedListMod(){
        StringBuilder sb= new StringBuilder();
        for(Person p:LinkedListPersonsMod){
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }


    static void removeFromTreeSetByIndex(int index){
        List<Person>temp = new ArrayList<>(TreeSetPersons);
        temp.remove(index);
        TreeSetPersons.clear();
        TreeSetPersons.addAll(temp);
    }


    static void removeFromTreeSetModByIndex(int index){
        List<PersonMod>temp = new ArrayList<>(TreeSetPersonsMod);
        temp.remove(index);
        TreeSetPersonsMod.clear();
        TreeSetPersonsMod.addAll(temp);
    }


    static void removeFromHashSetByIndex(int index){
        List<Person>temp = new ArrayList<>(HashSetPersons);
        temp.remove(index);
        HashSetPersons.clear();
        HashSetPersons.addAll(temp);
    }


    static void removeFromHashSetModByIndex(int index){
        List<PersonMod>temp = new ArrayList<>(HashSetPersonsMod);
        temp.remove(index);
        HashSetPersonsMod.clear();
        HashSetPersonsMod.addAll(temp);
    }
}
