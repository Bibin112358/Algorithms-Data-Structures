public class Mergesort {
	
	static void sort(int[] A) {
		if(A == null) return;
		
		sort(A, 0, A.length);
	}
	
	static void sort(int A[], int b, int e) { //e is exclusive => e-b == length
		
		if(e - b <= 1) return; //size 1 is already sorted
		
		int m = (b+e)/2;
		
		sort(A, b, m);
		sort(A, m, e);
		
		merge(A, b, m, e);
	}
		
	static void merge(int[] A, int b, int m, int e) {
		
		//copy left and right partition
		int n1 = m-b, n2 = e-m;
		
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for(int i=0; i < n1; i++)	L[i] = A[b+i];
		for(int i=0; i < n2; i++)	R[i] = A[m+i];
		
		int l=0; //left pointer
		int r=0; //right pointer
		int k=b; //solution pointer
		
		//merge
		while(l < n1 || r < n2) {
			if(l < n1 && (r == n2 || L[l] < R[r])) {
				A[k++] = L[l++];
			}else {
				A[k++] = R[r++];
			}
		}
	}
}
