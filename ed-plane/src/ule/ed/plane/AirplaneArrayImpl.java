package ule.ed.plane;

import java.util.ArrayList;
import java.util.List;

public class AirplaneArrayImpl implements IAirplane {

	private final int MAX_AGE_CHILDREN = 17;
	private String name;
	private int nRows;
	private int nColumns;

	private Passenger[][] seats;

	public AirplaneArrayImpl(String name, int nRows, int nColumns) {
		this.name = name;
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.seats = new Passenger[this.nRows][this.nColumns];

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getNumberOfRows() {
		return this.nRows;
	}

	@Override
	public int getNumberOfColumns() {
		return this.nColumns;
	}

	@Override
	public int getNumberOfPassengers() {

		int passengers = 0;

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if (this.seats[i][j] != null) {
					passengers += 1;
				}
			}
		}

		return passengers;
	}

	@Override
	public int getNumberOfChildren() {

		int children = 0;

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].getAge() <= this.MAX_AGE_CHILDREN)) {
					children += 1;
				}
			}
		}

		return children;
	}

	@Override
	public boolean isOccupiedSeat(int row, int column) {

		if (this.checkRightRowsAndColumns(row, column)) {
			return false;
		}

		return this.seats[row - 1][column - 1] != null;
	}

	@Override
	public int getNumberOfEmptySeats() {
		return (this.nRows * this.nColumns) - this.getNumberOfPassengers();
	}

	@Override
	public int getNumberOfRowsWithChildren() throws NoneChildrenInAvionException {

		int childrenRows = 0;

		if (this.getNumberOfChildren() == 0) {
			throw new NoneChildrenInAvionException();
		}

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].getAge() <= this.MAX_AGE_CHILDREN)) {
					childrenRows += 1;
					break;
				}
			}
		}

		return childrenRows;
	}

	@Override
	public int getNumberOfRowWithMoreChildren() throws NoneChildrenInAvionException {

		int rowWithMoreChildren = 0;
		int children = 0;
		int highestChildren = 0;

		if (this.getNumberOfChildren() == 0) {
			throw new NoneChildrenInAvionException();
		}

		for (int i = 0; i < this.getNumberOfRows(); i++) {

			children = 0;

			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].getAge() <= this.MAX_AGE_CHILDREN)) {
					children += 1;
				}
			}

			if (children > highestChildren) {
				highestChildren = children;
				rowWithMoreChildren = i + 1;
			}
		}
		return rowWithMoreChildren;
	}

	@Override
	public int getNumberOfColumnsWithChildren() throws NoneChildrenInAvionException {

		int childrenColumns = 0;

		if (this.getNumberOfChildren() == 0) {
			throw new NoneChildrenInAvionException();
		}

		for (int i = 0; i < this.getNumberOfColumns(); i++) {
			for (int j = 0; j < this.getNumberOfRows(); j++) {
				if ((this.seats[j][i] != null) && (this.seats[j][i].getAge() <= this.MAX_AGE_CHILDREN)) {
					childrenColumns += 1;
					break;
				}
			}
		}

		return childrenColumns;

	}

	@Override
	public int getNumberOfColumnWithMoreChildren() throws NoneChildrenInAvionException {

		int columnWithMoreChildren = 0;
		int children = 0;
		int highestChildren = 0;

		if (this.getNumberOfChildren() == 0) {
			throw new NoneChildrenInAvionException();
		}

		for (int i = 0; i < this.getNumberOfColumns(); i++) {

			children = 0;

			for (int j = 0; j < this.getNumberOfRows(); j++) {
				if ((this.seats[j][i] != null) && (this.seats[j][i].getAge() <= this.MAX_AGE_CHILDREN)) {
					children += 1;
				}
			}

			if (children > highestChildren) {
				highestChildren = children;
				columnWithMoreChildren = i + 1;
			}
		}
		return columnWithMoreChildren;
	}

	@Override
	public List<Passenger> getListOfChildPassengers() {

		List<Passenger> listaPassengers = new ArrayList<Passenger>();

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].getAge() <= this.MAX_AGE_CHILDREN)) {
					listaPassengers.add(this.seats[i][j]);
				}
			}
		}

		return listaPassengers;

	}

	@Override
	public List<Passenger> getListOfAdultsPassengers() {

		List<Passenger> listaPassengers = new ArrayList<Passenger>();

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].getAge() > this.MAX_AGE_CHILDREN)) {
					listaPassengers.add(this.seats[i][j]);
				}
			}
		}

		return listaPassengers;
	}

	@Override
	public List<String> getListOfEmptySeats() {

		List<String> listaEmptySeats = new ArrayList<String>();

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if (this.seats[i][j] == null) {
					listaEmptySeats.add("(" + (i + 1) + "," + (j + 1) + ")");
				}
			}
		}

		return listaEmptySeats;
	}

	@Override
	public List<String> getListOfOccupiedSeats() {

		List<String> listaOccupiedSeats = new ArrayList<String>();

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if (this.seats[i][j] != null) {
					listaOccupiedSeats.add("(" + (i + 1) + "," + (j + 1) + ")");
				}
			}
		}

		return listaOccupiedSeats;
	}

	@Override
	public Passenger getPassenger(int row, int column) {
		if (this.checkRightRowsAndColumns(row, column)) {
			return null;
		} else {
			return this.seats[row - 1][column - 1];
		}
	}

	private boolean isPassengerRepeated(Passenger other) {

		for (int i = 0; i < this.getNumberOfRows(); i++) {
			for (int j = 0; j < this.getNumberOfColumns(); j++) {
				if ((this.seats[i][j] != null) && (this.seats[i][j].equals(other))) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkRightRowsAndColumns(int row, int column) {
		return (row < 1) || (column < 1) || (row > this.getNumberOfRows()) || (column > this.getNumberOfColumns());
	}

	private boolean checkAvailabilityOfNearbySeats(int row, int column) {

		boolean upClear = false;
		boolean downClear = false;
		boolean leftClear = false;
		boolean rightClear = false;

		if ((row - 1) == 0) {
			upClear = true;
		} else {
			upClear = this.getPassenger(row - 1, column) == null;
		}

		if (row == this.getNumberOfRows()) {
			downClear = true;
		} else {
			downClear = this.getPassenger(row + 1, column) == null;
		}

		if ((column - 1) == 0) {
			leftClear = true;
		} else {
			leftClear = this.getPassenger(row, column - 1) == null;
		}

		if (column == this.getNumberOfColumns()) {
			rightClear = true;
		} else {
			rightClear = this.getPassenger(row, column + 1) == null;
		}

		return (upClear && downClear && leftClear && rightClear);
	}

	@Override
	public boolean addPassenger(int row, int column, String nif, String name, int age) {

		Passenger passenger = new Passenger(nif, name, age);

		if (this.checkRightRowsAndColumns(row, column)) {
			return false;
		}

		if (!(this.isOccupiedSeat(row, column)) && !(this.isPassengerRepeated(passenger))
				&& this.checkAvailabilityOfNearbySeats(row, column)) {
			this.seats[row - 1][column - 1] = passenger;
			return true;
		}

		return false;

	}

	@Override
	public boolean addPassenger(String nif, String name, int age) {

		for (int i = 1; i <= this.getNumberOfRows(); i++) {
			for (int j = 1; j <= this.getNumberOfColumns(); j++) {
				if (this.addPassenger(i, j, nif, name, age)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean emptySeat(int row, int column) {

		if (this.checkRightRowsAndColumns(row, column)) {
			return false;
		}

		if (this.isOccupiedSeat(row, column)) {
			this.seats[row - 1][column - 1] = null;
			return true;
		} else {
			return false;
		}

	}

	public String toString() {

		StringBuilder cadena = new StringBuilder();

		cadena.append("[");

		for (int i = 0; i < this.getNumberOfRows(); i++) {

			for (int j = 0; j < this.getNumberOfColumns(); j++) {

				if (this.seats[i][j] == null)
					cadena.append("(null)");
				else {
					cadena.append(this.seats[i][j].toString());
				}

			}

			cadena.append("\n");
		}

		cadena.append("]");

		return cadena.toString();
	}

}
