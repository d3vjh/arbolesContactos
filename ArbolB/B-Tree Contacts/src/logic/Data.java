package logic;

public class Data {
	protected long number; //8 bytes para el grado 8
	protected String name; 
	protected String lastName;
	protected String city;

	public Data(long number, String name, String lastName, String city) {
		this.number=number;
		this.name=name;
		this.lastName=lastName;
		this.city=city;
	}
}