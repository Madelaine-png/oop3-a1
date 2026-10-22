package products;

public class Jackets extends Product {

	public Jackets(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.7 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
