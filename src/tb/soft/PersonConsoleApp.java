package tb.soft;

import java.util.Arrays;

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
			"    M E N U   G Ł Ó W N E  		\n" +
			"1 - Podaj dane nowej osoby 		\n" +
			"2 - Usuń dane osoby        		\n" +
			"3 - Wypisz dane osób z kolekcji    \n" +
			"4 - Wczytaj dane z pliku   		\n" +
			"5 - Zapisz dane do pliku   		\n" +
			"0 - Zakończ program        		\n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";
	private static final String COLLECTION_ADD_MENU =
			"   Jakiej kolekcji użyć?     \n" +
			"1 - TreeSet           		  \n" +
			"2 - HashSet	       		  \n" +
			"3 - ArrayList		  		  \n" +
			"4 - LinkedList     		  \n" +
			"5 - TreeSetMod     		  \n" +
			"6 - HashSetMod     		  \n" +
			"7 - ArrayListMod     		  \n" +
			"8 - LinkedListMod     		  \n" +
			"0 - Powrót do menu głównego  \n";


	private static final String COLLECTION_SORT_MENU =
			"   Jakiego sortowania użyć?  \n" +
			"1 - Domyślne          		  \n" +
			"2 - Po imieniu	       		  \n" +
			"3 - Po nazwisku	  		  \n" +
			"4 - Po wieku	     		  \n" +
			"5 - Po zawodzie     		  \n" +
			"0 - Powrót do menu głównego  \n";


	private static final String SET_DELETE_MENU =
			"   Jakiego usuwania użyć?	  			\n" +
			"1 - Pierwszy element  		  			\n" +
			"2 - Ostatni element   		  			\n" +
			"3 - Po indeksie (konwersja na List)	\n" +
			"0 - Powrót do menu głównego  \n";


	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();
	
	
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
		UI.printInfoMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				int temp = UI.enterInt(MENU + "==>> ");
				switch (temp) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					temp = UI.enterInt(COLLECTION_ADD_MENU + "==>> ");
					switch (temp){
						case 1:
							PersonsCollections.TreeSetPersons.add(currentPerson);
							break;
						case 2:
							PersonsCollections.HashSetPersons.add(currentPerson);
							break;
						case 3:
							PersonsCollections.ArrayListPersons.add(currentPerson);
							break;
						case 4:
							PersonsCollections.LinkedListPersons.add(currentPerson);
							break;
						case 5:
							PersonsCollections.TreeSetPersonsMod.add(new PersonMod(currentPerson));
							break;
						case 6:
							PersonsCollections.HashSetPersonsMod.add(new PersonMod(currentPerson));
							break;
						case 7:
							PersonsCollections.ArrayListPersonsMod.add(new PersonMod(currentPerson));
							break;
						case 8:
							PersonsCollections.LinkedListPersonsMod.add(new PersonMod(currentPerson));
							break;
						case 0:
							break;
					}
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					switch (UI.enterInt(COLLECTION_ADD_MENU + "==>> ")){
						case 1:
							UI.printMessage(PersonsCollections.showTreeSet());
							switch (UI.enterInt(SET_DELETE_MENU + "==>> ")){
								case 1:
									PersonsCollections.TreeSetPersons.pollFirst();
									break;
								case 2:
									PersonsCollections.TreeSetPersons.pollLast();
									break;
								case 3:
									PersonsCollections.removeFromTreeSetByIndex(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
									break;
								case 0:
									break;
							}
							break;
						case 2:
							UI.printMessage(PersonsCollections.showHashSet());
							PersonsCollections.removeFromHashSetByIndex(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 3:
							UI.printMessage(PersonsCollections.showArrayList());
							PersonsCollections.ArrayListPersons.remove(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 4:
							UI.printMessage(PersonsCollections.showLinkedList());
							PersonsCollections.ArrayListPersons.remove(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 5:
							UI.printMessage(PersonsCollections.showTreeSetMod());
							switch (UI.enterInt(SET_DELETE_MENU + "==>> ")){
								case 1:
									PersonsCollections.TreeSetPersonsMod.pollFirst();
									break;
								case 2:
									PersonsCollections.TreeSetPersonsMod.pollLast();
									break;
								case 3:
									PersonsCollections.removeFromTreeSetModByIndex(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
									break;
								case 0:
									break;
							}
							break;
						case 6:
							UI.printMessage(PersonsCollections.showHashSetMod());
							PersonsCollections.removeFromHashSetModByIndex(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 7:
							UI.printMessage(PersonsCollections.showArrayListMod());
							PersonsCollections.ArrayListPersonsMod.remove(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 8:
							UI.printMessage(PersonsCollections.showLinkedListMod());
							PersonsCollections.LinkedListPersonsMod.remove(UI.enterInt("Podaj indeks elementu który chcesz usunąć\n==>> "));
							break;
						case 0:
							break;
					}
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					switch (UI.enterInt(COLLECTION_ADD_MENU + "==>> ")){
						case 1:
							UI.printMessage(PersonsCollections.showTreeSet());
							break;
						case 2:
							UI.printMessage(PersonsCollections.showHashSet());
							break;
						case 3:
							UI.printMessage(PersonsCollections.showArrayList());
							break;
						case 4:
							UI.printMessage(PersonsCollections.showLinkedList());
							break;
						case 5:
							UI.printMessage(PersonsCollections.showTreeSetMod());
							break;
						case 6:
							UI.printMessage(PersonsCollections.showHashSetMod());
							break;
						case 7:
							UI.printMessage(PersonsCollections.showArrayListMod());
							break;
						case 8:
							UI.printMessage(PersonsCollections.showLinkedListMod());
							break;
						case 0:
							break;
					}
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
		UI.printInfoMessage( sb.toString() );
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
