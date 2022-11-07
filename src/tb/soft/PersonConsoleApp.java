package tb.soft;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 * operacji wykonywanych na obiektach klasy Person. Plik: PersonConsoleApp.java
 * 
 * Autor: Paweł Rogaliński Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane osoby        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"6 - Wyświetl wszystkie osoby\n" +
			"7 - Przetestuj działanie equals() i hashCode()\n" +
			"0 - Zakończ program        \n";	

	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";
	
	private static final String COLLECTIONS_MENU = 
			"   Wybierz kolekcję	\n" + 
	        "1 - HashSet	\n" + 
			"2 - TreeSet	\n" + 
	        "3 - ArrayList	\n" + 
			"4 - LinkedList	\n" +
	        "5 - HashMap	\n" + 
			"6 - TreeMap	\n" +
	        "0 - Powrót do menu głównego\n";

	private static final PersonsContainer personsContainer = new PersonsContainer();

	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw prostych metod do
	 * realizacji dialogu z użytkownikiem w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();

	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();

		application.runMainLoop();
	}

	/*
	 * Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;

	/*
	 * Metoda runMainLoop wykonuje główną pętlę aplikacji. UWAGA: Ta metoda zawiera
	 * nieskończoną pętlę, w której program się zatrzymuje aż do zakończenia
	 * działania za pomocą metody System.exit(0);
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1: {
					// utworzenie nowej osoby
					currentPerson = createNewPerson();

					personsContainer.add(currentPerson);

					break;
				}
				case 2: {
					// usunięcie danych aktualnej osoby.
					personsContainer.remove(currentPerson);

					currentPerson = null;

					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");

					break;
				}
				case 3: {
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null)
						throw new PersonException("Żadna osoba nie została utworzona.");

					// no better idea than that
					personsContainer.remove(currentPerson);

					changePersonData(currentPerson);

					personsContainer.add(currentPerson);

					break;
				}
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");

					currentPerson = Person.readFromFile(file_name);

					personsContainer.add(currentPerson);

					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);

					break;
					}
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego
					String file_name = UI.enterString("Podaj nazwę pliku: ");

					Person.printToFile(file_name, currentPerson);

					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);

					break;
					}
				case 6: {
					printMenuCollections();

					break;
					}
				case 7: {
					testEqualsAndHashCode();
					
					break;
					}
				case 0: {
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");

					System.exit(0);
					}
				} // koniec instrukcji switch
			} catch (PersonException e) {
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}

	/*
	 * Metoda wyświetla w oknie konsoli dane aktualnej osoby pamiętanej w zmiennej
	 * currentPerson.
	 */
	void showCurrentPerson() {
		UI.printInfoMessage("Aktualna osoba: \n");
		showPerson(currentPerson);
	}

	/*
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej przez obiekt
	 * klasy Person
	 */
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();

		if (person != null) {
			sb.append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append("Brak danych osoby\n");

		UI.printMessage(sb.toString());
	}

	/*
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt klasy Person i
	 * wypełnia atrybuty wczytanymi danymi. Walidacja poprawności danych odbywa się
	 * w konstruktorze i setterach klasy Person. Jeśli zostaną wykryte niepoprawne
	 * dane, to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson() {
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");

		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));

		String job_name = UI.enterString("Podaj stanowisko: ");

		Person person;

		try {
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);

			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());

			return null;
		}

		return person;
	}

	/*
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów obiekty person
	 * i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach klasy Person.
	 * Jeśli zostaną wykryte niepoprawne dane, to zostanie zgłoszony wyjątek, który
	 * zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person) {
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));

					person.setJob(UI.enterString("Podaj stanowisko: "));

					break;
				case 0:
					return;
				} // koniec instrukcji switch
			} catch (PersonException e) {
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	public void printMenuCollections() throws PersonException {
		while (true) {
			UI.clearConsole();

			showCurrentPerson();

			switch (UI.enterInt(COLLECTIONS_MENU + "==>> ")) {
			case 1:
				printHashSet();
				break;
			case 2:
				printTreeSet();
				break;
			case 3:
				printArrayList();
				break;
			case 4:
				printLinkedList();
				break;
			case 5:
				printHashMap();
				break;
			case 6:
				printTreeMap();
				break;
			case 0:
				return;
			}
		}
	}

	public void printHashSet() throws PersonException {
		Set<Person> hashSet = personsContainer.getHashSet();
		Set<PersonExtended> hashSetExtended = personsContainer.getHashSetExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("HashSet bez implementacji equals() i hashCode()");

		for (Person person : hashSet) {
			showPerson(person);

			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("HashSet z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : hashSetExtended) {
			showPerson(personExtended);

			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}

	public void printTreeSet() throws PersonException {
		Set<Person> treeSet = personsContainer.getTreeSet();
		Set<PersonExtended> treeSetExtended = personsContainer.getTreeSetExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("TreeSet bez implementacji equals() i hashCode()");

		for (Person person : treeSet) {
			showPerson(person);

			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("TreeSet z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : treeSetExtended) {
			showPerson(personExtended);

			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}

	public void printArrayList() throws PersonException {
		List<Person> arrayList = personsContainer.getArrayList();
		List<PersonExtended> arrayListExtended = personsContainer.getArrayListExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("ArrayList bez implementacji equals() i hashCode()");

		for (Person person : arrayList) {
			showPerson(person);

			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("ArrayList z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : arrayListExtended) {
			showPerson(personExtended);

			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}

	public void printLinkedList() throws PersonException {
		List<Person> linkedList = personsContainer.getLinkedList();
		List<PersonExtended> linkedListExtended = personsContainer.getLinkedListExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("LinkedList bez implementacji equals() i hashCode()");

		for (Person person : linkedList) {
			showPerson(person);

			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("LinkedList z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : linkedListExtended) {
			showPerson(personExtended);

			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}

	public void printHashMap() throws PersonException {
		Map<Person, Integer> hashMap = personsContainer.getHashMap();
		Map<PersonExtended, Integer> hashMapExtended = personsContainer.getHashMapExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("HashMap bez implementacji equals() i hashCode()");

		for (Person person : hashMap.keySet()) {
			showPerson(person);

			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("HashMap z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : hashMapExtended.keySet()) {
			showPerson(personExtended);

			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}

	public void printTreeMap() throws PersonException {
		Map<Person, Integer> treeMap = personsContainer.getTreeMap();
		Map<PersonExtended, Integer> treeMapExtended = personsContainer.getTreeMapExtended();
		PersonExtended currentPersonExtended = new PersonExtended(currentPerson);

		UI.printMessage("TreeMap bez implementacji equals() i hashCode()");

		for (Person person : treeMap.keySet()) {
			showPerson(person);
			if (person.equals(currentPerson))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}

		UI.clearConsole();
		UI.printMessage("TreeMap z implementacją equals() i hashCode()");

		for (PersonExtended personExtended : treeMapExtended.keySet()) {
			showPerson(personExtended);
			if (personExtended.equals(currentPersonExtended))
				UI.printMessage("Porównując z aktualnie wybraną osobą: sama osoba");
			else
				UI.printMessage("Porównując z aktualnie wybraną osobą: inna osoba");
		}
	}
	
	public void testEqualsAndHashCode() throws PersonException {
		PersonExtended personExtended = new PersonExtended(currentPerson);

		showCurrentPerson();
		
		UI.printMessage("hashCode bez implementacji: " + currentPerson.hashCode() );
		UI.printMessage("hashCode z implementacją: " + personExtended.hashCode());

		Person clonedPerson = new Person(currentPerson);
		
		PersonExtended clonedPersonExtended = new PersonExtended(clonedPerson);

		UI.printMessage("Klon aktualnie wybranej osoby: ");
		
		showPerson(clonedPerson);
		
		UI.printMessage("Wynik metody equals dla identycznych, sklonowanych obiektów (bez implementacji):" + currentPerson.equals(clonedPerson));
		UI.printMessage("Wynik metody equals dla identycznych, sklonowanych obiektów (z własną implementacją):" + clonedPersonExtended.equals(personExtended));
	}
} // koniec klasy PersonConsoleApp
