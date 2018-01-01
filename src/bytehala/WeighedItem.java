package bytehala;

public class WeighedItem implements Item {
		
		private int weight;
		private int value;

		public WeighedItem(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		@Override
		public int getPhysicalAttribute() {
			return weight;
		}
		
		@Override
		public int getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return "w:" + weight + " v:" + value;
		}
		
		
	}