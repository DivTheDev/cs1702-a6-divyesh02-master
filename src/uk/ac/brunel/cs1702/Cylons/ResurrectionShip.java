package uk.ac.brunel.cs1702.Cylons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResurrectionShip {
	//FIELDS
	private int shipNo;
	private List<Integer> models = new ArrayList<Integer>();
	private Map<Integer, Integer> bodiesPerModel = new HashMap<Integer, Integer>();

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected ResurrectionShip(int shipID){
		this.shipNo = shipID;
		int numberOfModels = Constants.NUMBER_OF_CYLON_MODELS / Constants.MAX_NUMBER_OF_SHIPS;
		int modelStart = (shipID - 1) * numberOfModels;
		for (int i = modelStart; i < modelStart + numberOfModels; i++) {
			models.add(i + 1);
			bodiesPerModel.put(i + 1,
					Constants.MAX_NUMBER_OF_BODIES_PER_MODEL_IN_RESURRECTION_SHIP);
		}
	} 
	
	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected int getBodyCount(int modelNumb) {
		if (models.contains(modelNumb)) {
			return bodiesPerModel.get(modelNumb);
		}
		return 0;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public Cylon[][] getCylonArmy(int[] modelNumbCount) throws CylonException{ 
		if (modelNumbCount.length != Constants.NUMBER_OF_CYLON_MODELS) {
			throw new CylonException(); 
		}

		Cylon[][] armyToReturn = new Cylon[modelNumbCount.length][0];
		for (int i = 0; i < modelNumbCount.length; i++) {
			int modelNumb = i + 1;
			if (models.contains(modelNumb)) {
				armyToReturn[i] = handleCylonArmyGetterForModel(modelNumb, modelNumbCount[i]);
			}
		}
		return armyToReturn;
	}

	private Cylon[] handleCylonArmyGetterForModel(int modelNumb, int wantToGet)
			throws CylonException {
		Cylon[] armyToReturn = new Cylon[0];
		if (wantToGet < 0 || !hasEnoughBodies(modelNumb, wantToGet)) {
			throw new CylonException();
		}
		armyToReturn = createCylonArmyWithModel(modelNumb, wantToGet);
		updateBodiesForModel(modelNumb, wantToGet);
		return armyToReturn;
	}

	private boolean hasEnoughBodies(int modelNumb, int wantToGet) {
		int availableForModel = bodiesPerModel.get(modelNumb);
		if (availableForModel >= wantToGet) {
			return true;
		}
		return false;
	}

	private Cylon[] createCylonArmyWithModel(int modelNumb, int wantToGet) throws CylonException {
		Cylon[] returnArmy = new Cylon[wantToGet];
		for (int i = 0; i < wantToGet; i++) {
			returnArmy[i] = new Cylon(modelNumb);
		}
		return returnArmy;
	}

	private void updateBodiesForModel(int modelNumb, int wantToGet) {
		int accessibleForModel = bodiesPerModel.get(modelNumb);
		bodiesPerModel.put(modelNumb, accessibleForModel - wantToGet);
	}

	protected int resurrect(Cylon cylon) throws CylonException{
		if (cylon.isInfected()) {
			for (int i = 0; i < models.size(); i++) {
				bodiesPerModel.put(models.get(i), 0);
			}
		}
		int modelNumb = cylon.getModel();
		int bodiesLeftForModel = bodiesPerModel.get(modelNumb);
		if (bodiesLeftForModel > 0) {
			bodiesPerModel.put(modelNumb, bodiesLeftForModel - 1);
			return modelNumb;
		}
		throw new CylonException();
	}

	protected int getShipID(){
		return this.shipNo;
	}

	protected boolean hasModel(int modelNumb) {
		return models.contains(modelNumb);
	}
}