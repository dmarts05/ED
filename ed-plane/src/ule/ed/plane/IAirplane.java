package ule.ed.plane;

import java.util.List;

public interface IAirplane {
	
	// Devuelve el nombre del restaurante
	public String getName();
	
	// Devuelve el número de filas 
	public int getNumberOfRows();
	
	// Devuelve el número de columnas 
	public int getNumberOfColumns();
		
	//Devuelve el número de pasajeros que son menores de 18 años
	public int getNumberOfChildren();
			
    //Devuelve el número de pasajeros que hay en el avión
	public int getNumberOfPassengers();
		
	//Calcula el número de asientos vacíos (no ocupados)
	public int getNumberOfEmptySeats();

	//Devuelve el número de filas que tienen algún niño
	//Dispara la excepción si no hay niños en el avión
	public int getNumberOfRowsWithChildren() throws NoneChildrenInAvionException;
	
	//Devuelve el número de fila que tiene más niños
	//Dispara la excepción si no hay niños en el avión
	public int getNumberOfRowWithMoreChildren() throws NoneChildrenInAvionException;
		
	//Devuelve el número de columnas que tienen algún niño
	//Dispara la excepción si no hay niños en el avión
	public int getNumberOfColumnsWithChildren() throws NoneChildrenInAvionException;
		
	//Devuelve el número de columna que tiene más niños
	//Dispara la excepción si no hay niños en el avión
	public int getNumberOfColumnWithMoreChildren() throws NoneChildrenInAvionException;
	
	//Devuelve verdadero si el asiento correspondiente a la fila y columna está ocupado
	// false en caso contrario
	public boolean isOccupiedSeat(int row, int column);
		
			
	// Devuelve una lista de los pasajeros que son niños
	public List<Passenger> getListOfChildPassengers();
	
	// Devuelve una lista de los pasajeros que son adultos
	public List<Passenger> getListOfAdultsPassengers();
		
	// Devuelve una lista de los asientos que están libres.
	// Cada asiento libre será un String formado por el número de fila, una coma y el número de columna (SIN ESPACIOS Y ENTRE PARÉNTESIS)
	// Ejemplo: (3,4)
	public List<String> getListOfEmptySeats();
	
	// Devuelve una lista de los asientos que están ocupados.
	// Cada asiento ocupado será un String formado por el número de fila, un guión y el número de columna (SIN ESPACIOS)
	public List<String> getListOfOccupiedSeats();
		
	// Devuelve el pasajero que hay en el asiento correspondiente a la fila y columna indicados 
	// Devuelve null el asiento no está ocupado
	// Las filas y columnas empiezan en el número 1
	public Passenger getPassenger(int row, int column);
	
	// Añade un pasajero (si no está en el avión) a un asiento especificado con una fila y una columna 
	// Si la fila o columna no son válidas devuelve false
	// Si el asiento ya está ocupado devuelve false
	// Si el asiento tiene un pasajero en el asiento anterior o posterior o a su derecha o a su izquierda devuelve false
	// Si puede insertar el pasajero devuelve true
	public boolean addPassenger(int row, int column, String nif, String name, int edad);
	
	// Añade un pasajero (si no está en el avión) en el primer asiento disponible del avión  teniendo en cuenta
	// que no se puede asignar un asiento que cumpla alguna las siguientes carácterísticas:
	//  - la fila o columna no son válidas 
	//  - el asiento esté ocupado
	//  - el asiento de la fila anterior e igual columna esté ocupado
	//  - el asiento de la fila posterior e igual columna esté ocupado
	//  - el asiento de la izquierda o derecha esté ocupado
	// Si puede asignar un asiento al pasajero devuelve true
	// en caso contrario devuelve false
	// Comprueba por filas hasta encontrar el primer asiento libre que pueda ocuparse
	public boolean addPassenger(String nif, String name, int edad);	

	// Elimina el pasajero del asiento con la fila y columna indicados. <br> 
    // Si el asiento con esa fila y columna no está ocupado, 
    // o si el número de fila o columna no es válido, devuelve false.<br>
    // Si se pudo vaciar el asiento devuelve true
	// 
	// Los números de fila y columna empiezan en '1'.
	public boolean emptySeat(int row, int column);

	// Convierte en String toda la información del avión
	public String toString();
	
}
