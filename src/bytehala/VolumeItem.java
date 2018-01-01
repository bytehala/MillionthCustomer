package bytehala;

public class VolumeItem implements Item {
	
	private int id;
	private int price;
	private int volume;
	
	/**
	 * 
	 * @param id
	 * @param price
	 * @param length
	 * @param width
	 * @param height
	 * @param weight
	 */
	public VolumeItem(int id, int price, int length, int width, int height) {
		this.id = id;
		this.price = price;
		this.volume = length * width * height;
	}

	@Override
	public int getPhysicalAttribute() {
		return volume;
	}

	@Override
	public int getValue() {
		return price;
	}
	
	@Override
	public String toString() {
		return "id:" + id + " volume:" + volume + " price:" + price;
	}

}
