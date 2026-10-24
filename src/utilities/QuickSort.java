package utilities;

import java.util.Comparator;

import products.Product;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of products from largest to 
 * smallest using a Quick sort
 * </p>
 */
public class QuickSort {
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
		Product[] sorted = quickSort(array, comp);
		// Copy the sorted values back into the original array
		for(int i = 0; i < array.length; i++) {
			array[i] = sorted[i];
		}
	}

	/**
     * Finds a pivot point by finding the median of the first, last, and middle values
     * then splits array by putting all values greater than pivot in left, and all less
     * then values in the right array, then using quick sort on those arrays to sort them
     * the arrays are then recombined fully sorted
     *
     * @param nums the array of products to sort
     * @param comp the comparator to use, or null to use compareTo()
     * @return array sorted from largest to smallest
     */
	
	private static Product[] quickSort(Product[] nums, Comparator<Product> comp) {
		// Var to hold pivot point in array
		int pivot;
		// Array to hold final sorted values
		Product[] merged = new Product[nums.length];
		
		if(nums.length <= 1) {
			return nums;
		}
		
		// Find median for pivot
		if(compare(nums[nums.length/2], nums[0], comp) > 0 && compare(nums[0], nums[nums.length-1], comp) > 0 || compare(nums[nums.length/2], nums[0], comp) < 0 && compare(nums[0], nums[nums.length-1], comp) < 0) {
			pivot = 0;
		} else if(compare(nums[nums.length/2], nums[nums.length-1], comp) > 0 && compare(nums[nums.length-1], nums[0], comp) > 0 || compare(nums[nums.length/2], nums[nums.length-1], comp) < 0 && compare(nums[nums.length-1], nums[0], comp) < 0) {
			pivot = nums.length - 1;
		} else {
			pivot = nums.length/2;
		}
		
		// Vars to hold size of left and right arrays
		int lSize = 0;
		int rSize = 0;
		
		// Find size of arrays
		for(int i = 0; i < nums.length; i++) {
			if(i == pivot) {
				
			} else if(compare(nums[i], nums[pivot], comp) > 0) {
				lSize ++;
			} else {
				rSize ++;
			}
		}
		
		// Arrays to hold split values
		Product[] left = new Product[lSize];
		Product[] right = new Product[rSize];
		
		// Vars to keep track of how many values have been moved
		int lPlace = 0;
		int rPlace = 0;
		
		// Split array on the pivot point
		for(int i = 0; i < nums.length; i++) {
			if(i == pivot) {
			} else if(compare(nums[i], nums[pivot], comp) > 0) {
				left[lPlace] = nums[i];
				lPlace ++;
			} else {
				right[rPlace] = nums[i];
				rPlace ++;
			}
		}
		// Quick Sort the arrays
		left = quickSort(left, comp);
		right = quickSort(right, comp);
		
		// Var to keep track of how many values have been moved to merged array
		int mPlace = 0;
		
		// Recombine arrays
		for(int i = 0; i < left.length; i++) {
			merged[mPlace] = left[i];
			mPlace++;
		}
		merged[mPlace] = nums[pivot];
		mPlace++;
		
		for(int i = 0; i < right.length; i++) {
			merged[mPlace] = right[i];
			mPlace++;
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
