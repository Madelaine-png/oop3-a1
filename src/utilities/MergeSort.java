package sortUtils;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of numbers from largest to 
 * smallest using a merge sort
 * </p>
 */
public class MergeSort {
	/**
     * Divides the array in half and sorts each half with merge sort, then combine them back
     * together into one sorted array
     *
     * @param nums the array of numbers to sort
     * @return array sorted from largest to smallest
     */
	
	public Integer[] sort(Integer[] nums) {
		if(nums.length == 1) {
			return nums;
		}
		
		// Half the length of the array
		Integer half = nums.length/2;
		// Arrays to divide nums into
		Integer[] left = new Integer[half];
		Integer[] right = new Integer[nums.length-half];
		// Final array with values sorted
		Integer[] merged = new Integer[nums.length];
		
		// Divide array in half
		for(int i = 0; i < nums.length; i++) {
			if(i < half) {
				left[i] = nums[i];
			} else {
				right[i-half] = nums[i];
			}
		}
		
		// Sort the halves
		left = sort(left);
		right = sort(right);
		
		// Vars to keep track of how many values have been moved
		Integer lPlace = 0;
		Integer rPlace = 0;
		
		// Merge arrays into one sorted array
		for(int i = 0; i < nums.length; i++) {
			if(rPlace == right.length) {
				merged[i] = left[lPlace];
				lPlace++;
			} else if(lPlace == left.length) {
				merged[i] = right[rPlace];
				rPlace++;
			} else {
				if(left[lPlace] > right[rPlace]) {
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
}
