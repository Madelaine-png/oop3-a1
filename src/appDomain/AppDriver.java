package appDomain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import external.OriginalPriceCompare;
import external.UpdatedPriceCompare;
import products.Dresses;
import products.Jackets;
import products.Jeans;
import products.Product;
import products.Shoes;
import products.Shorts;
import products.Skirts;
import products.TShirts;
import utilities.BubbleSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.QuickSort;
import utilities.SelectionSort;
// import utilities.?Sort;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
	private static final String USAGE = "Usage: java -jar Sort.jar -f<file_name> -t<compare_type> -s<sort_alg>\n"
			+ "\t-f  path to the data file, e.g. -fres/products1.txt\n"
			+ "\t-t  compare type: o (original price), u (updated price), d (calculated difference)\n"
			+ "\t-s  sort algorithm: b (bubble), s (selection), i (insertion), m (merge), q (quick), z (custom)\n"
			+ "Arguments are case insensitive and may be given in any order.";

	private static String fileName = null;
	private static char compareType = 0;
	private static char sortType = 0;

	/**
	 *  The main method is the entry point of the application.
	 *  
	 *  @param args The input to control the execution of the application.
	 */
	public static void main( String[] args )
	{
		if( !parseArgs( args ) )
		{
			System.out.println( USAGE );
			return;
		}

		Product[] products = readFile( fileName );
		if( products == null )
		{
			return;
		}

		Comparator<Product> comparator = getComparator( compareType );

		long start = System.nanoTime();
		sort( products, comparator, sortType );
		long end = System.nanoTime();

		display( products, compareType );
		System.out.println( getSortName( sortType ) + " run time was: " + ( end - start ) / 1_000_000 + " milliseconds" );
	}

	/**
	 * Parses and validates the command line arguments.
	 * returns true if all arguments are valid, false otherwise
	 */
	private static boolean parseArgs( String[] args )
	{
		if( args.length != 3 )
		{
			System.out.println( "Error: expected 3 arguments (-f, -t and -s) but received " + args.length + "." );
			return false;
		}

		for( String arg : args )
		{
			int start = 0;
			while( start < arg.length() && !Character.isLetterOrDigit( arg.charAt( start ) ) )
			{
				start++;
			}
			if( start == 0 || start >= arg.length() )
			{
				System.out.println( "Error: invalid argument \"" + arg + "\". Each argument must start with -f, -t or -s." );
				return false;
			}

			char option = Character.toLowerCase( arg.charAt( start ) );
			String value = arg.substring( start + 1 ).replace( "\"", "" ).trim();

			switch( option )
			{
				case 'f':
					if( fileName != null )
					{
						System.out.println( "Error: the -f option was entered more than once." );
						return false;
					}
					if( value.isEmpty() )
					{
						System.out.println( "Error: no file name was given after -f (e.g. -fres/products1.txt)." );
						return false;
					}
					fileName = value;
					break;
				case 't':
					if( compareType != 0 )
					{
						System.out.println( "Error: the -t option was entered more than once." );
						return false;
					}
					if( value.length() != 1 || "oud".indexOf( Character.toLowerCase( value.charAt( 0 ) ) ) == -1 )
					{
						System.out.println( "Error: invalid compare type \"" + value + "\". Use o, u or d after -t." );
						return false;
					}
					compareType = Character.toLowerCase( value.charAt( 0 ) );
					break;
				case 's':
					if( sortType != 0 )
					{
						System.out.println( "Error: the -s option was entered more than once." );
						return false;
					}
					if( value.length() != 1 || "bsimqz".indexOf( Character.toLowerCase( value.charAt( 0 ) ) ) == -1 )
					{
						System.out.println( "Error: invalid sort algorithm \"" + value + "\". Use b, s, i, m, q or z after -s." );
						return false;
					}
					sortType = Character.toLowerCase( value.charAt( 0 ) );
					break;
				default:
					System.out.println( "Error: unknown option \"" + arg + "\". Valid options are -f, -t and -s." );
					return false;
			}
		}

		if( fileName == null || compareType == 0 || sortType == 0 )
		{
			System.out.println( "Error: missing argument. The -f, -t and -s options are all required." );
			return false;
		}
		return true;
	}

	/**
	 * Reads the data file and creates an array of products.
	 * 
	 * @param path the path to the data file
	 * @return the array of products, or null if the file could not be read
	 */
	private static Product[] readFile( String path )
	{
		try( BufferedReader reader = new BufferedReader( new FileReader( path ) ) )
		{
			String line = reader.readLine();
			if( line == null )
			{
				System.out.println( "Error: the file \"" + path + "\" is empty." );
				return null;
			}

			int count = Integer.parseInt( line.trim() );
			Product[] products = new Product[count];

			for( int i = 0; i < count; i++ )
			{
				line = reader.readLine();
				if( line == null )
				{
					System.out.println( "Error: the file says it has " + count + " products but only " + i + " were found." );
					return null;
				}

				String[] parts = line.trim().split( "\\s+" );
				if( parts.length != 3 )
				{
					System.out.println( "Error: line " + ( i + 2 ) + " is not formatted correctly: \"" + line + "\"" );
					return null;
				}

				products[i] = createProduct( parts[0], Double.parseDouble( parts[1] ), Double.parseDouble( parts[2] ) );
				if( products[i] == null )
				{
					System.out.println( "Error: unknown product type \"" + parts[0] + "\" on line " + ( i + 2 ) + "." );
					return null;
				}
			}
			return products;
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "Error: the file \"" + path + "\" could not be found. Check the path after -f." );
		}
		catch( NumberFormatException e )
		{
			System.out.println( "Error: the file \"" + path + "\" contains an invalid number (" + e.getMessage() + ")." );
		}
		catch( IOException e )
		{
			System.out.println( "Error: could not read the file \"" + path + "\" (" + e.getMessage() + ")." );
		}
		return null;
	}

	/**
	 * Creates the product subclass that matches the given type name.
	 * 
	 * @param type     the product type name
	 * @param original the original price
	 * @param updated  the updated price
	 * @return the new product, or null if the type is unknown
	 */
	private static Product createProduct( String type, double original, double updated )
	{
		switch( type.toLowerCase() )
		{
			case "jeans":
				return new Jeans( original, updated );
			case "tshirts":
				return new TShirts( original, updated );
			case "skirts":
				return new Skirts( original, updated );
			case "dresses":
				return new Dresses( original, updated );
			case "shorts":
				return new Shorts( original, updated );
			case "jackets":
				return new Jackets( original, updated );
			case "shoes":
				return new Shoes( original, updated );
			default:
				return null;
		}
	}

	/**
	 * Returns the comparator for the compare type, or null to use compareTo().
	 * 
	 * @param type the compare type (o, u or d)
	 * @return the matching comparator, or null for the calculated difference
	 */
	private static Comparator<Product> getComparator( char type )
	{
		switch( type )
		{
			case 'o':
				return new OriginalPriceCompare();
			case 'u':
				return new UpdatedPriceCompare();
			default:
				return null;
		}
	}

	/**
	 * Sorts the products in descending order with the chosen algorithm.
	 * 
	 * @param products   the array to sort
	 * @param comparator the comparator to use, or null to use compareTo()
	 * @param type       the sort algorithm (b, s, i, m, q or z)
	 */
	private static void sort( Product[] products, Comparator<Product> comparator, char type )
	{
		switch( type )
		{
			case 'b':
				if( comparator == null ) BubbleSort.sort( products ); else BubbleSort.sort( products, comparator );
				break;
			case 's':
				if( comparator == null ) SelectionSort.sort( products ); else SelectionSort.sort( products, comparator );
				break;
			case 'i':
				if( comparator == null ) InsertionSort.sort( products ); else InsertionSort.sort( products, comparator );
				break;
			case 'm':
				if( comparator == null ) MergeSort.sort( products ); else MergeSort.sort( products, comparator );
				break;
			case 'q':
				if( comparator == null ) QuickSort.sort( products ); else QuickSort.sort( products, comparator );
				break;
			case 'z':
				// TODO: add the algorithm of choice
				break;
		}
	}

	/**
	 * Displays the sorted products based on how many there are.
	 * 
	 * @param products the sorted array
	 * @param type     the compare type (o, u or d)
	 */
	private static void display( Product[] products, char type )
	{
		int n = products.length;
		if( n == 0 )
		{
			System.out.println( "There are no products to display." );
			return;
		}

		if( n <= 10 )
		{
			for( int i = 0; i < n; i++ )
			{
				printProduct( ordinal( i + 1 ) + " element:", products[i], type );
			}
			return;
		}

		int step = ( n <= 1000 ) ? 100 : 1000;
		printProduct( "First element is:", products[0], type );
		for( int i = step; i < n; i += step )
		{
			printProduct( i + "-th element:", products[i - 1], type );
		}
		printProduct( "Last element is:", products[n - 1], type );
	}

	/**
	 * Prints one product with the value that matches the compare type.
	 * 
	 * @param label   the position label
	 * @param product the product to print
	 * @param type    the compare type (o, u or d)
	 */
	private static void printProduct( String label, Product product, char type )
	{
		String value;
		switch( type )
		{
			case 'o':
				value = "Original Price: " + product.getOriginalPrice();
				break;
			case 'u':
				value = "Updated Price: " + product.getUpdatedPrice();
				break;
			default:
				value = "Real Difference: " + product.calcRealDiff();
		}
		System.out.printf( "%-22s%-22s%s%n", label, product.getClass().getName(), value );
	}

	/**
	 * Returns a number with its ordinal suffix (1-st, 2-nd, 3-rd, 4-th...).
	 * 
	 * @param num the number
	 * @return the number with its suffix
	 */
	private static String ordinal( int num )
	{
		if( num % 100 >= 11 && num % 100 <= 13 )
		{
			return num + "-th";
		}
		switch( num % 10 )
		{
			case 1:
				return num + "-st";
			case 2:
				return num + "-nd";
			case 3:
				return num + "-rd";
			default:
				return num + "-th";
		}
	}

	/**
	 * Returns the full name of the sort algorithm.
	 * 
	 * @param type the sort algorithm (b, s, i, m, q or z)
	 * @return the algorithm name
	 */
	private static String getSortName( char type )
	{
		switch( type )
		{
			case 'b':
				return "Bubble Sort";
			case 's':
				return "Selection Sort";
			case 'i':
				return "Insertion Sort";
			case 'm':
				return "Merge Sort";
			case 'q':
				return "Quick Sort";
			default:
				return "";    // remember to fill in the algorithm of choice when done
		}
	}

}
 
