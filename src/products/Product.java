package products;

public abstract class Product {
	private int originalPrice;
	private int updatedPrice;
	
	public Product(int original, int updated) {
		setOriginalPrice(original);
		setUpdatedPrice(updated);
	}
	
	public abstract int calcRealDiff();

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getUpdatedPrice() {
		return updatedPrice;
	}

	public void setUpdatedPrice(int updatedPrice) {
		this.updatedPrice = updatedPrice;
	}
}
