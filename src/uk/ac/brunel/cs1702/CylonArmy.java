package uk.ac.brunel.cs1702;

import uk.ac.brunel.cs1702.Cylons.*;

public class CylonArmy {

	//FIELDS
	private Cylon[][] army;
	private Cylon[][] shipsArmy;

	public CylonArmy(int[] modelNoCount) throws CylonException {

		if (modelNoCount.length != Constants.NUMBER_OF_CYLON_MODELS) throw new CylonException();
		
		for(int thisModel : modelNoCount)
			if(thisModel < 1 || thisModel > Constants.MAX_NUMBER_OF_BODIES_PER_MODEL_IN_RESURRECTION_SHIP)
				throw new CylonException();

		army = new Cylon[modelNoCount.length][0];
		
		for (int i = 1; i <= Constants.MAX_NUMBER_OF_SHIPS; i++) {
			
			shipsArmy = ResurrectionShipFactory.getInstance().getNewShip(i).getCylonArmy(modelNoCount);
			
			for (int j = 0; j < shipsArmy.length; j++) {

				if (shipsArmy[j].length > 0) army[j] = shipsArmy[j];
				
			}

		}

	}
	
	public Cylon[][] getArmy() {
		// return this class's current two dimentional army array
		return army;
		
	}
}