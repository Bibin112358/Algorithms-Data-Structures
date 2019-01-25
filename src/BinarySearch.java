
public class BinarySearch {
	
	//could be anything
	static boolean test(int[] A, int i, int v) {
		if(A[i] < v) return false;
		return true;
	}
	
	static int binary_search(int[] A, int p) {
		//algorithm guarantees that test(-1) && test(length) will not be called
		
		int b = -1, e = A.length; //test(b) = 0 && test(e) = 1
		
		while(e - b > 1) {
			int m = b + (e-b)/2; //to prevent Overflow
		
			if(test(A, m, p)) {
				e = m;
			}else {
				b = m;
			}
		}
		
		return e;
	}
}
