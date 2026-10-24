package utilities;

import java.util.Comparator;
import products.Product;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of products from largest to 
 * smallest using a selection sort
 * </p>
 */
public class SelectionSort {
	/**
     * Sorts the array from largest to smallest using compareTo()
     *
     * @param array the array of products to sort
     */
	public static void sort(Product[] array) {
		sort(array, null);
	}

	/**
     * Continuously swaps the largest unsorted value with the first
     * unsorted value in the array until it's sorted
     *
     * @param array the array of products to sort
     * @param comp the comparator to use, or null to use compareTo()
     */
	public static void sort(Product[] array, Comparator<Product> comp) {
		
		// Var to track largest value's index
		int largest;
		// Var to hold largest value while swapping
		Product hold;
		
		for(int i = 0; i < array.length-1; i++) {
			// Find largest value
			largest = i;
			for(int j = i + 1; j < array.length; j++) {
				if(compare(array[j], array[largest], comp) > 0) {
					largest = j;
				}
			}
			
			// Swap largest with first unsorted value
			hold = array[largest];
			array[largest] = array[i];
			array[i] = hold;
		}
	}

	/**
     * Compares two products with the comparator, or with compareTo() if it is null
     *
     * @param p1 the first product
     * @param p2 the second product
     * @param comp the comparator to use, or null to use compareTo()
     * @return positive if p1 is larger, negative if smaller, 0 if equal
     */
	private static int compare(Product p1, Product p2, Comparator<Product> comp) {
		if(comp == null) {
			return p1.compareTo(p2);
		}
		return comp.compare(p1, p2);
	}
}
