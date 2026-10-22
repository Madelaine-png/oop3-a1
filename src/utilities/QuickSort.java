package sortUtils;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of numbers from largest to 
 * smallest using a Quick sort
 * </p>
 */
public class QuickSort {
	/**
     * Finds a pivot point by finding the median of the first, last, and middle values
     * then splits array by putting all values greater than pivot in left, and all less
     * then values in the right array, then using quick sort on those arrays to sort them
     * the arrays are then recombined fully sorted
     *
     * @param nums the array of numbers to sort
     * @return array sorted from largest to smallest
     */
	
	public Integer[] sort(Integer[] nums) {
		// Var to hold pivot point in array
		Integer pivot;
		// Array to hold final sorted values
		Integer[] merged = new Integer[nums.length];
		
		if(nums.length <= 1) {
			return nums;
		}
		
		// Find median for pivot
		if(nums[nums.length/2] > nums[0] && nums[0] > nums[nums.length-1] || nums[nums.length/2] < nums[0] && nums[0] < nums[nums.length-1]) {
			pivot = 0;
		} else if(nums[nums.length/2] > nums[nums.length-1] && nums[nums.length-1] > nums[0] || nums[nums.length/2] < nums[nums.length-1] && nums[nums.length-1] < nums[0]) {
			pivot = nums.length - 1;
		} else {
			pivot = nums.length/2;
		}
		
		// Vars to hold size of left and right arrays
		Integer lSize = 0;
		Integer rSize = 0;
		
		// Find size of arrays
		for(int i = 0; i < nums.length; i++) {
			if(i == pivot) {
				
			} else if(nums[i] > nums[pivot]) {
				lSize ++;
			} else {
				rSize ++;
			}
		}
		
		// Arrays to hold split values
		Integer[] left = new Integer[lSize];
		Integer[] right = new Integer[rSize];
		
		// Vars to keep track of how many values have been moved
		Integer lPlace = 0;
		Integer rPlace = 0;
		
		// Split array on the pivot point
		for(int i = 0; i < nums.length; i++) {
			if(i == pivot) {
			} else if(nums[i] > nums[pivot]) {
				left[lPlace] = nums[i];
				lPlace ++;
			} else {
				right[rPlace] = nums[i];
				rPlace ++;
			}
		}
		// Quick Sort the arrays
		left = sort(left);
		right = sort(right);
		
		// Var to keep track of how many values have been moved to merged array
		Integer mPlace = 0;
		
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
}
