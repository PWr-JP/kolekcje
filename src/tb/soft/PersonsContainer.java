package tb.soft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PersonsContainer {
	private Set<Person> hashSet;
	private Set<PersonExtended> hashSetExtended;

	private Set<Person> treeSet;
	private Set<PersonExtended> treeSetExtended;

	private List<Person> arrayList;
	private List<PersonExtended> arrayListExtended;

	private List<Person> linkedList;
	private List<PersonExtended> linkedListExtended;

	private Map<Person, Integer> hashMap;
	private Map<PersonExtended, Integer> hashMapExtended;

	private Map<Person, Integer> treeMap;
	private Map<PersonExtended, Integer> treeMapExtended;

	public PersonsContainer() {
		hashSet = new HashSet<>();
		hashSetExtended = new HashSet<>();

		treeSet = new TreeSet<>();
		treeSetExtended = new TreeSet<>();

		arrayList = new ArrayList<>();
		arrayListExtended = new ArrayList<>();

		linkedList = new LinkedList<>();
		linkedListExtended = new LinkedList<>();

		hashMap = new HashMap<>();
		hashMapExtended = new HashMap<>();

		treeMap = new TreeMap<>();
		treeMapExtended = new TreeMap<>();
	}

	public void add(Person person) throws PersonException {
		PersonExtended personExtended = new PersonExtended(person);
		
		hashSet.add(person);
		hashSetExtended.add(personExtended);

		treeSet.add(person);
		treeSetExtended.add(personExtended);

		arrayList.add(person);
		arrayListExtended.add(personExtended);

		linkedList.add(person);
		linkedListExtended.add(personExtended);

		hashMap.put(person, hashMap.size() + 1);
		hashMapExtended.put(personExtended, hashMapExtended.size() + 1);

		treeMap.put(person, treeMap.size() + 1);
		treeMapExtended.put(personExtended, treeMapExtended.size() + 1);

	}

	public void remove(Person person) throws PersonException {
		PersonExtended personExtended = new PersonExtended(person);
		
		hashSet.remove(person);
		hashSetExtended.remove(personExtended);

		treeSet.remove(person);
		treeSetExtended.remove(personExtended);

		arrayList.remove(person);
		arrayListExtended.remove(personExtended);

		linkedList.remove(person);
		linkedListExtended.remove(personExtended);

		hashMap.remove(person);
		hashMapExtended.remove(personExtended);

		treeMap.remove(person);
		treeMapExtended.remove(personExtended);

	}

	public Set<Person> getHashSet() {
		return hashSet;
	}

	public Set<PersonExtended> getHashSetExtended() {
		return hashSetExtended;
	}

	public Set<Person> getTreeSet() {
		return treeSet;
	}

	public Set<PersonExtended> getTreeSetExtended() {
		return treeSetExtended;
	}

	public List<Person> getArrayList() {
		return arrayList;
	}

	public List<PersonExtended> getArrayListExtended() {
		return arrayListExtended;
	}

	public List<Person> getLinkedList() {
		return linkedList;
	}

	public List<PersonExtended> getLinkedListExtended() {
		return linkedListExtended;
	}

	public Map<Person, Integer> getHashMap() {
		return hashMap;
	}

	public Map<PersonExtended, Integer> getHashMapExtended() {
		return hashMapExtended;
	}

	public Map<Person, Integer> getTreeMap() {
		return treeMap;
	}

	public Map<PersonExtended, Integer> getTreeMapExtended() {
		return treeMapExtended;
	}
}
