package tb.soft;

public class PersonMod extends Person{

    public PersonMod(Person person) throws PersonException {
        super(person.getFirstName(), person.getLastName());
        setBirthYear(person.getBirthYear());
        setJob(person.getJob());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime * getFirstName().hashCode();
        result = prime * result + getLastName().hashCode();
        result = prime * result + getBirthYear();
        result = prime * result + getJob().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        PersonMod person = (PersonMod) o;

        if (getBirthYear() != person.getBirthYear()){
            return false;
        }

        if (getFirstName().equals(person.getFirstName())){
            return true;
        }
        if (getJob().equals(person.getJob())){
            return true;
        }
        return getLastName().equals(person.getLastName());
    }
}
