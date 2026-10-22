package products;

public class Shoes extends Product {

	public Shoes(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.4 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
