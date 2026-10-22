package products;

public class Dresses extends Product {

	public Dresses(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.6 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
