package uk.ac.brunel.cs1702.Cylons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResurrectionShipFactory implements ShipFinder {
	//FIELDS
	private String shipsCreatedCheck = "";
	List<ResurrectionShip> shipsCreated = new ArrayList<ResurrectionShip>();

	private static ResurrectionShipFactory instance;

	private ResurrectionShipFactory(){}

	public static ResurrectionShipFactory getInstance(){
		if (instance == null) {
			instance = new ResurrectionShipFactory();
		}
		return instance;
	}

	public ResurrectionShip getNewShip(int shipID) throws CylonException {
		if((shipID <= 0) || (shipID > Constants.MAX_NUMBER_OF_SHIPS)) throw new CylonException();
		
		if(!shipsCreatedCheck.contains(Integer.toString(shipID)))
			shipsCreatedCheck += Integer.toString(shipID) + " ";
		else
			throw new CylonException();
		
		ResurrectionShip currentShip = new ResurrectionShip(shipID);
		shipsCreated.add(currentShip);
		return currentShip;
	}

	public ResurrectionShip findYourShip(Cylon cylon) {
		Optional<ResurrectionShip> ship = shipsCreated.stream().filter(s -> s.hasModel(cylon.getModel())).findFirst();
		if (ship.isPresent()) {
			return ship.get();
		}
		return null;
	}
}

//	public ResurrectionShip findYourShip(Cylon cylon) {
//		// returns the correct ID of the ship that serves the given Cylon in the parameter, for resurrection.
//		int cylonsPerShip = Constants.NUMBER_OF_CYLON_MODELS / Constants.MAX_NUMBER_OF_SHIPS;
//		
//		// formulae to return the shipID the cylon belongs to
//		int returnShipID = cylon.getModel()-1 / cylonsPerShip;
//		
//		return new ResurrectionShip(returnShipID+1);
//	}