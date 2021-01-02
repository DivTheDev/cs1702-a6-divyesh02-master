package uk.ac.brunel.cs1702.Cylons;

public class Cylon {
	private int modelNumber;
	private int resCount = 0;
	private boolean dead = false;
	private boolean infected = false;

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected Cylon(int modelNo) throws CylonException{
		if (modelNo < 1 || modelNo > Constants.NUMBER_OF_CYLON_MODELS) throw new CylonException();
		modelNumber = modelNo;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public int getModel() {
		return modelNumber;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public int getResurrectionCount() {
		return resCount;
	} 

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public boolean isDeadForever() {
		return dead;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public void killed() throws CylonException {
		try {
			ResurrectionShip ship = ResurrectionShipFactory.getInstance().findYourShip(this);
			if (ship != null) {
				ship.resurrect(this);
				resCount++;
			}
			else throw new CylonException();
		}
		catch (CylonException e) {
			dead = true;
		}
	}
	
	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public void setInfected(){
		infected = true;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public boolean isInfected(){
		if(infected == true) return true;
		else return false;
	}
}