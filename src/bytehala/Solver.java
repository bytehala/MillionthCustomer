package bytehala;

import java.util.ArrayList;
import java.util.List;

public class Solver {
	
	/**
	 * The limit of the Item.physicalAttribute, e.g. maximum weight a knapsack can carry
	 */
	private int limit;
	private List<Item> items;
	private int numItems;
	
	private int[][] T;

	public Solver(List<Item> items, int limit) {
		this.items = items;
		this.limit = limit;
		this.numItems = items.size();
		T = new int[numItems][limit + 1];
	}
	
	public void solve() {
		for(int y = 0; y < numItems; y++) {
			for(int x = 0; x < limit + 1; x++) {
				Item item = items.get(y);
				if(x == 0) {
					T[y][x] = 0;
				} else {
					if(x < item.getPhysicalAttribute()) {
						if(y == 0) {
							T[y][x] = 0;
						} else {
							T[y][x] = T[y-1][x];
						}
					} else {
						if(y == 0) {
							T[y][x] = item.getValue();
						} else {
							T[y][x] = Math.max(item.getValue() + T[y-1][x - item.getPhysicalAttribute()], T[y-1][x]);
						}
					}
				}
			}
		}
		backTrace();
	}
	
	private void backTrace() {
		int x = limit;
		int y = numItems - 1;
		
		int value;
		do {
			value = T[y][x];
			Item item = items.get(y);
			if((x - item.getPhysicalAttribute() >= 0) && value - item.getValue() == T[y][x-item.getPhysicalAttribute()]) { // take
				System.out.println(item);
				x = x - item.getPhysicalAttribute();
			}
			y--;
			
		} while(x > 0);
		
	}

	public static void main(String[] args) {
		List<Item> items = new ArrayList<>();
		items.add(new WeighedItem(3, 6));
		items.add(new WeighedItem(5, 7));
		items.add(new WeighedItem(4, 5));
		items.add(new WeighedItem(3, 4));
		items.add(new WeighedItem(1, 1));
		Solver s = new Solver(items, 7);
		s.solve();
	}

}
