package external;

import java.util.Comparator;

import products.Product;

/**
 * Compares two products by their updated price.
 */
public class UpdatedPriceCompare implements Comparator<Product>
{
	/**
	 * @param p1 the first product
	 * @param p2 the second product
	 * @return a positive value if p1 has the higher updated price, a negative value if it's lower, or zero if they are equal
	 */
	@Override
	public int compare( Product p1, Product p2 )
	{
		return Double.compare( p1.getUpdatedPrice(), p2.getUpdatedPrice() );
	}
}
