import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] A = {12, 11, 13, 5, 6};
		
		Mergesort.sort(A);
		
		System.out.println(Arrays.toString(A));
		
		int o = BinarySearch.binary_search(A, 10);
		System.out.println(o);
		
		o = BinarySearch.binary_search(A, 12);
		System.out.println(o);
	}

}
