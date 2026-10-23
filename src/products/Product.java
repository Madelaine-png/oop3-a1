package products;

public abstract class Product implements Comparable<Product> {
	private double originalPrice;
	private double updatedPrice;
	
	public Product(double original, double updated) {
		setOriginalPrice(original);
		setUpdatedPrice(updated);
	}
	
	public abstract double calcRealDiff();

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double original) {
		this.originalPrice = original;
	}

	public double getUpdatedPrice() {
		return updatedPrice;
	}

	public void setUpdatedPrice(double updated) {
		this.updatedPrice = updated;
	}
	
	@Override
	public int compareTo(Product that) {
		return Double.compare(this.calcRealDiff(), that.calcRealDiff());
	}
}
