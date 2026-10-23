package utilities;

import java.util.Comparator;
import products.Product;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of products from largest to 
 * smallest using a merge sort
 * </p>
 */
public class MergeSort {
	/**
     * Sorts the array from largest to smallest using compareTo()
     *
     * @param array the array of products to sort
     */
	public static void sort(Product[] array) {
		sort(array, null);
	}

	/**
     * Sorts the array from largest to smallest using the comparator
     *
     * @param array the array of products to sort
     * @param comp the comparator to use, or null to use compareTo()
     */
	public static void sort(Product[] array, Comparator<Product> comp) {
		Product[] sorted = mergeSort(array, comp);
		// Copy the sorted values back into the original array
		for(int i = 0; i < array.length; i++) {
			array[i] = sorted[i];
		}
	}

	/**
     * Divides the array in half and sorts each half with merge sort, then combine them back
     * together into one sorted array
     *
     * @param nums the array of products to sort
     * @param comp the comparator to use, or null to use compareTo()
     * @return array sorted from largest to smallest
     */
	private static Product[] mergeSort(Product[] nums, Comparator<Product> comp) {
		if(nums.length <= 1) {
			return nums;
		}
		
		// Half the length of the array
		int half = nums.length/2;
		// Arrays to divide nums into
		Product[] left = new Product[half];
		Product[] right = new Product[nums.length-half];
		// Final array with values sorted
		Product[] merged = new Product[nums.length];
		
		// Divide array in half
		for(int i = 0; i < nums.length; i++) {
			if(i < half) {
				left[i] = nums[i];
			} else {
				right[i-half] = nums[i];
			}
		}
		
		// Sort the halves
		left = mergeSort(left, comp);
		right = mergeSort(right, comp);
		
		// Vars to keep track of how many values have been moved
		int lPlace = 0;
		int rPlace = 0;
		
		// Merge arrays into one sorted array
		for(int i = 0; i < nums.length; i++) {
			if(rPlace == right.length) {
				merged[i] = left[lPlace];
				lPlace++;
			} else if(lPlace == left.length) {
				merged[i] = right[rPlace];
				rPlace++;
			} else {
				if(compare(left[lPlace], right[rPlace], comp) > 0) {
					merged[i] = left[lPlace];
					lPlace++;
				} else {
					merged[i] = right[rPlace];
					rPlace++;
				}
			}
		}
		
		return merged;
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
