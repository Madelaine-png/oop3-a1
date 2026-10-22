package products;

public class Jeans extends Product {

	public Jeans(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.5 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
