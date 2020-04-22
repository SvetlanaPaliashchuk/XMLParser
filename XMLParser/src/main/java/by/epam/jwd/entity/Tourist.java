package by.epam.jwd.entity;

public class Tourist {
	private String surname;
	private String name;
	private String passport;

	public Tourist() {
	}

	public Tourist(String surname, String name, String passport) {
		super();
		this.name = name;
		this.surname = surname;
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tourist other = (Tourist) obj;
		return ((name != null && name.equals(other.getName())))
				&& ((surname != null && surname.equals(other.getSurname())))
				&& ((passport != null && passport.equals(other.getPassport())));
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [surname=" + surname + ", name=" + name + ", passport=" + passport + "]";
	}

}
