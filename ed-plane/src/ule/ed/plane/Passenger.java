package ule.ed.plane;

public class Passenger {
	private String nif;
	private String name;
	private int age;

	public Passenger(String nif, String name, int age) {
		this.nif = nif;
		this.name = name;
		this.age = age;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// devolver un String con el nif, nombre y edad separados por un guión y sin
	// espacios
	// Por ejemplo: "10203040X-Ana-30"
	public String toString() {
		// devolver un String con el nif, nombre y edad separados por un guión y sin
		// espacios (entre paréntesis)
		// Por ejemplo: "(10203040X-Ana-30)"
		return "(" + this.getNif() + "-" + this.getName() + "-" + this.getAge() + ")";
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof Passenger) {
			Passenger other = (Passenger) obj;
			return (this.getNif().equals(other.getNif()));
		}

		return false;
	}
}
