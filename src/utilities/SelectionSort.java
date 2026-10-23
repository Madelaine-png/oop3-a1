package utilities;

/**
 * A sorting utility
 * <p>
 * This class implements a sorting algorithm to sort an array of numbers from largest to 
 * smallest using a selection sort
 * </p>
 */
public class SelectionSort {
	/**
     * Continuously swaps the largest unsorted value with the first
     * unsorted value in the array until it's sorted
     *
     * @param nums the array of numbers to sort
     * @return array sorted from largest to smallest
     */
	
	public Integer[] sort(Integer[] nums) {
		
		// Var to track largest value's index
		Integer largest;
		// Var to hold largest value while swapping
		Integer hold;
		
		for(int i = 0; i < nums.length-1; i++) {
			// Find largest value
			largest = i;
			for(int j = i; j < nums.length-1; j++) {
				if(nums[j] > nums[largest]) {
					largest = j;
				}
			}
			
			// Swap largest with first unsorted value
			hold = nums[largest];
			nums[largest] = nums[i];
			nums[i] = hold;
		}
		return nums;
	}
}
