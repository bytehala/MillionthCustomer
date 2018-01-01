package bytehala;

import java.util.ArrayList;
import java.util.List;

public class Solver {
	
	private int maxWeight;
	private List<Item> items;
	private int numItems;
	
	private int[][] T;

	public Solver(List<Item> items, int maxWeight) {
		this.items = items;
		this.maxWeight = maxWeight;
		this.numItems = items.size();
		T = new int[numItems][maxWeight + 1];
	}
	
	public void solve() {
		for(int y = 0; y < numItems; y++) {
			for(int x = 0; x < maxWeight + 1; x++) {
				Item item = items.get(y);
				if(x == 0) {
					T[y][x] = 0;
				} else {
					if(x < item.getWeight()) {
						if(y == 0) {
							T[y][x] = 0;
						} else {
							T[y][x] = T[y-1][x];
						}
					} else {
						if(y > 0) {
							T[y][x] = Math.max(item.getValue() + T[y-1][x - item.getWeight()], T[y-1][x]);
						} else {
							T[y][x] = item.getValue();
						}
					}
				}
			}
		}
		backTrace();
	}
	
	private void backTrace() {
		int x = maxWeight;
		int y = numItems - 1;
		
		int value;
		do {
			value = T[y][x];
			Item item = items.get(y);
			if((x - item.getWeight() >= 0) && value - item.getValue() == T[y][x-item.getWeight()]) { // take
				System.out.println(item);
				x = x - item.getWeight();
			}
			y--;
			
		} while(x > 0);
		
	}

	public static void main(String[] args) {
		List<Item> items = new ArrayList<>();
		items.add(new Item(3, 6));
		items.add(new Item(5, 7));
		items.add(new Item(4, 5));
		items.add(new Item(3, 4));
		items.add(new Item(1, 1));
		Solver s = new Solver(items, 7);
		s.solve();
	}

}
