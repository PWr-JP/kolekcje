package tb.soft;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
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
			"6 - Demo kolekcji			\n" +
			"0 - Zakończ program        \n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n";

	private static final String COLLECTION_MENU =
			"   Wybierz rodzaj kolekcji:	\n" +
			"1 - HashSet					\n" +
			"2 - TreeSet					\n" +
			"3 - ArrayList					\n" +
			"4 - LinkedList					\n" +
			"5 - HashMap					\n" +
			"6 - TreeMap					\n" +
			"0 - Powrót do menu głównego	\n";

	private static final String COLLECTION_OPERATION_MENU =
			"   Wybierz rodzaj operacji na kolekcji:\n" +
			"1 - Dodaj								\n" +
			"2 - Usuń								\n" +
			"3 - Wyświetl							\n" +
			"4 - Iteruj 							\n";

	private static final String ADD_MESSAGE =
			"Podaj liczbę do dodania";

	private static final String REMOVE_MESSAGE =
			"Podaj liczbę do usunięcia";

	private static final String KEY_MESSAGE =
			"Podaj klucz elementu na którym zostanie wykonana operacja";

	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();
	private final CollectionsDemo collectionsDemo = new CollectionsDemo();
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;
	
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}
				case 6: {
					chooseCollection();
				}
					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
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

	void chooseCollection() {
		var pick = UI.enterInt(COLLECTION_MENU);

		switch (pick) {
			case 1:
				handleCollection(collectionsDemo.hashSet, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 2:
				handleCollection(collectionsDemo.treeSet, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 3:
				handleCollection(collectionsDemo.arrayList, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 4:
				handleCollection(collectionsDemo.linkedList, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 5:
				handleMap(collectionsDemo.hashMap, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 6:
				handleMap(collectionsDemo.treeMap, UI.enterInt(COLLECTION_OPERATION_MENU));

				break;

			case 0:
				break;
		}
	}

	void handleCollection(Collection<Integer> collection, Integer option) {
		switch (option)
		{
			case 1:
				collection.add(UI.enterInt(ADD_MESSAGE));
				break;

			case 2:
				collection.remove(UI.enterInt(REMOVE_MESSAGE));
				break;

			case 3:
				var allElements = collection.stream()
						.map(Object::toString)
						.collect(Collectors.joining(", "));

				UI.printMessage(allElements.isEmpty() ? "Kolekcja nie zawiera elementów" : allElements);
				break;

			case 4:
				StringBuilder stringBuilder = new StringBuilder();

				collection.forEach(integer -> stringBuilder.append(String.format("%s ", integer)));

				UI.printInfoMessage(stringBuilder.toString());

				break;
		}

		chooseCollection();
	}

	void handleMap(Map<Integer, Integer> map, Integer option) {
		Integer key;
		switch (option)
		{
			case 1:
				key = UI.enterInt(KEY_MESSAGE);
				map.put(key, UI.enterInt(ADD_MESSAGE));
				break;

			case 2:
				map.remove(UI.enterInt(KEY_MESSAGE));
				break;

			case 3:
				var allElements = map.entrySet().stream()
						.map(element -> String.format("%s : %s", element.getKey(), element.getValue()))
						.collect(Collectors.joining(", "));

				UI.printMessage(allElements.isEmpty() ? "Kolekcja nie zawiera elementów" : allElements);
				break;

			case 4:
				StringBuilder stringBuilder = new StringBuilder();

				map.forEach((integer, integer2) -> stringBuilder.append(String.format("(%s : %s)", integer, integer2)));

				UI.printInfoMessage(stringBuilder.toString());

				break;
		}

		chooseCollection();
	}
	
	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
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
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
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
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
