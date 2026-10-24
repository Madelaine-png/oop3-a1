package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Utility class providing Heap Sort implementations for Product arrays.
 * Sorts items in DESCENDING order as required by the AppDriver setup.
 */
public class HeapSort {

    // --- 1. NATURAL COMPARABLE SORT (No Comparator provided) ---
    public static void sort(Product[] products) {
        int n = products.length;

        // Build max-heap (rearranged array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(products, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            Product temp = products[0];
            products[0] = products[i];
            products[i] = temp;

            // call max heapify on the reduced heap
            heapify(products, i, 0);
        }
    }

    private static void heapify(Product[] products, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child index
        int right = 2 * i + 2; // right child index

        // Invert comparison logic (< 0) to ensure final array is DESCENDING
        if (left < n && products[left].compareTo(products[largest]) < 0) {
            largest = left;
        }

        if (right < n && products[right].compareTo(products[largest]) < 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            Product swap = products[i];
            products[i] = products[largest];
            products[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(products, n, largest);
        }
    }

    // --- 2. CUSTOM COMPARATOR SORT (Comparator provided) ---
    public static void sort(Product[] products, Comparator<Product> comparator) {
        int n = products.length;

        // Build max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(products, n, i, comparator);
        }

        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            Product temp = products[0];
            products[0] = products[i];
            products[i] = temp;

            heapify(products, i, 0, comparator);
        }
    }

    private static void heapify(Product[] products, int n, int i, Comparator<Product> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Invert comparison logic (< 0) to ensure final array is DESCENDING
        if (left < n && comparator.compare(products[left], products[largest]) < 0) {
            largest = left;
        }

        if (right < n && comparator.compare(products[right], products[largest]) < 0) {
            largest = right;
        }

        if (largest != i) {
            Product swap = products[i];
            products[i] = products[largest];
            products[largest] = swap;

            heapify(products, n, largest, comparator);
        }
    }
}
