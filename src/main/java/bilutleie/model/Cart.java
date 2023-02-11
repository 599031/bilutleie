
package bilutleie.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;	// SLETTES TIL SLUTT


public class Cart {
	
	
	@Value("${app.message.prove}")				// SLETTES TIL SLUTT
	private String MELDING;						// SLETTES TIL SLUTT
	
	
	private List<CartItem> items = new ArrayList<>();
	
	
	public void addItem(CartItem item) {
		if (items.contains(item)) {
			int foundAtIndex = items.indexOf(item);
			items.get(foundAtIndex).changeQuantity(item.getQuantity());
		} else {
			items.add(item);
		}
	}
	
	
	public List<CartItem> getItems() {
		return items;
	}
	
	
	public int getTotal() {
		return items.stream().mapToInt(i -> i.getPrice() * i.getQuantity()).sum();
	}
	
	
	public void skrivUt() {					// SLETTES TIL SLUTT
		System.err.println(MELDING);
	}
	
}

