package products;

public class Skirts extends Product {

	public Skirts(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.1 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
