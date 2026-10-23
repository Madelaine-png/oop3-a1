package utilities;

import java.util.Comparator;
// import problemdomain.Product; // make sure to import your Product class if it's in another package

/**
 * <p>
 * Provides static Bubble Sort implementations that arrange an array of
 * objects in <b>descending</b> order (largest to smallest).
 * </p>
 * <p>
 * Bubble sort repeatedly steps through the array, compares each pair of
 * adjacent elements and swaps them when they are in the wrong order. After
 * every pass the smallest remaining element has "bubbled" to the end of the
 * unsorted portion, so each pass can stop one position earlier than the last.
 * If a full pass makes no swaps the array is already sorted and the algorithm
 * stops early.
 * </p>
 * <p>
 * Complexity: worst and average case O(n<sup>2</sup>) comparisons and swaps;
 * best case O(n) when the array is already in descending order. Sorting is
 * done in place using O(1) extra memory, and the sort is stable.
 * </p>
 * <p>
 * No timing or output is performed here; benchmarking is the responsibility
 * of the calling code.
 * </p>
 */
public final class BubbleSort
{
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private BubbleSort()
	{
	}

	/**
	 * Sorts the given array in descending order using the natural ordering of
	 * its elements, as defined by their {@link Comparable#compareTo(Object)}
	 * method (for products, the calculated difference from calcRealDiff()).
	 *
	 * @param array the array to sort in place; a {@code null} array or one with
	 *              fewer than two elements is left unchanged
	 */
	public static void sort( Product[] array )
	{
		if( array == null || array.length < 2 )
		{
			return;
		}

		int n = array.length;
		boolean swapped;

		for( int i = 0; i < n - 1; i++ )
		{
			swapped = false;

			for( int j = 0; j < n - 1 - i; j++ )
			{
				// Descending: swap when the left element is smaller than the right one
				if( array[j].compareTo( array[j + 1] ) < 0 )
				{
					Product temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}

			if( !swapped )
			{
				break;
			}
		}
	}

	/**
	 * Sorts the given array in descending order using the ordering defined by
	 * the supplied {@link Comparator} (for example, comparing products by
	 * original price or updated price).
	 *
	 * @param array      the array to sort in place; a {@code null} array or one
	 *                   with fewer than two elements is left unchanged
	 * @param comparator the comparator that determines the ordering of the
	 *                   elements; must not be {@code null}
	 * @throws IllegalArgumentException if {@code comparator} is {@code null}
	 */
	public static void sort( Product[] array, Comparator<Product> comparator )
	{
		if( comparator == null )
		{
			throw new IllegalArgumentException( "Comparator must not be null." );
		}
		if( array == null || array.length < 2 )
		{
			return;
		}

		int n = array.length;
		boolean swapped;

		for( int i = 0; i < n - 1; i++ )
		{
			swapped = false;

			for( int j = 0; j < n - 1 - i; j++ )
			{
				if( comparator.compare( array[j], array[j + 1] ) < 0 )
				{
					Product temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}

			if( !swapped )
			{
				break;
			}
		}
	}
}
