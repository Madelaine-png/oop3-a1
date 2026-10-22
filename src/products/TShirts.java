package products;

public class TShirts extends Product {

	public TShirts(double original, double updated) {
		super(original, updated);
	}

	@Override
	public double calcRealDiff() {
		return 0.2 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
