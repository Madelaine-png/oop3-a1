package utilities;

import java.util.Comparator;

/**
 * <p>
 * Provides static Insertion Sort implementations that arrange an array of
 * objects in <b>descending</b> order (largest to smallest).
 * </p>
 * <p>
 * Insertion sort builds the sorted result one element at a time. The left
 * part of the array is always kept sorted; each new element (the "key") is
 * taken from the unsorted part and every smaller element in the sorted part
 * is shifted one position to the right until the correct spot for the key is
 * found, where it is then inserted.
 * </p>
 * <p>
 * Complexity: worst and average case O(n<sup>2</sup>) comparisons and shifts;
 * best case O(n) when the array is already in descending order. Sorting is
 * done in place using O(1) extra memory, and the sort is stable.
 * </p>
 */
public final class InsertionSort
{
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private InsertionSort()
	{
	}

	/**
	 * Sorts the given array in descending order using the natural ordering of
	 * its elements, as defined by their {@link Comparable#compareTo(Object)}
	 * method (for products, the calculated difference from calcRealDiff()).
	 *
	 * @param <T>   the type of the elements, which must be comparable to
	 *              each other
	 * @param array the array to sort in place; a {@code null} array or one with
	 *              fewer than two elements is left unchanged
	 */
	public static <T extends Comparable<T>> void sort( T[] array )
	{
		if( array == null || array.length < 2 )
		{
			return;
		}

		for( int i = 1; i < array.length; i++ )
		{
			T key = array[i];
			int j = i - 1;

			// Shift smaller elements to the right to make room for descending order
			while( j >= 0 && array[j].compareTo( key ) < 0 )
			{
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = key;
		}
	}

	/**
	 * Sorts the given array in descending order using the ordering defined by
	 * the supplied {@link Comparator} (for example, comparing products by
	 * original price or updated price).
	 *
	 * @param <T>        the type of the elements in the array
	 * @param array      the array to sort in place; a {@code null} array or one
	 *                   with fewer than two elements is left unchanged
	 * @param comparator the comparator that determines the ordering of the
	 *                   elements; must not be {@code null}
	 * @throws IllegalArgumentException if {@code comparator} is {@code null}
	 */
	public static <T> void sort( T[] array, Comparator<T> comparator )
	{
		if( comparator == null )
		{
			throw new IllegalArgumentException( "Comparator must not be null." );
		}
		if( array == null || array.length < 2 )
		{
			return;
		}

		for( int i = 1; i < array.length; i++ )
		{
			T key = array[i];
			int j = i - 1;

			while( j >= 0 && comparator.compare( array[j], key ) < 0 )
			{
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = key;
		}
	}
}
