package products;

public class Shorts extends Product {

	public Shorts(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.3 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
