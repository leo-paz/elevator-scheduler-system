public class Floor {
	private int floorNumber;
	/**
	 * The default constructor sets the floor to 1.
	 */
	public Floor() {
		this.floorNumber = 1;
	}
	/**
	 * This constructor allows the user to set the floor number.
	 * @param floorNumber The floor of reference. 
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	/**
	 * Allows the user to access the current floor number.
	 * @return the current floor number.
	 */
	public int getFloorNumber() {
		return floorNumber;
	}
	/**
	 * Allows the user to set the current floor number.
	 * @param floorNumber the floor number desired.
	 */
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
}