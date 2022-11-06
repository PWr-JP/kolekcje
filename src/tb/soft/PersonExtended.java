package tb.soft;

public class PersonExtended extends Person {

	public PersonExtended(Person person) throws PersonException {
		super(person.getFirstName(), person.getLastName());

		birthYear = person.getBirthYear();
		job = person.getJob();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		// can use this when both objects have the same class
		PersonExtended person = (PersonExtended) obj;

		if (getBirthYear() != person.getBirthYear())
			return false;

		if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
			return false;

		return getLastName().equals(person.getLastName());
	}

	@Override
	public int hashCode() {
		int result = getFirstName() != null ? getFirstName().hashCode() : 0;
		
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getBirthYear();
		result = 31 * result + getJob().hashCode();

		return result;
	}
}
