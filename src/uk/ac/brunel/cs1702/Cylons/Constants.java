package uk.ac.brunel.cs1702.Cylons;

public final class Constants {
	
	//YOU CAN CHANGE THE VALUE OF THESE CONSTANTS TO TEST YOUR CODE WITH DIFFERENT PARAMETERS
	//MAKE SURE THAT THE NUMBER_OF_CYLON_MODELS IS A MULTIPLE OF MAX_NUMBER_OF_SHIPS
	public static int MAX_NUMBER_OF_SHIPS = 6;
	public static int NUMBER_OF_CYLON_MODELS = 12;
	public static int MAX_NUMBER_OF_BODIES_PER_MODEL_IN_RESURRECTION_SHIP = 70;
	
	private Constants() throws CylonException{
		throw new CylonException();
	}

}
