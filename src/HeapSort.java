import java.util.List;

public class HeapSort {
	private static int N;
    public static void sort(List<ThuaDat> thuaDat){       
		heapMethod(thuaDat);        
        for (int i = N; i > 0; i--){
            swap(thuaDat,0, i);
            N = N-1;
            heap(thuaDat, 0);
        }
    }     
    public static void heapMethod(List<ThuaDat> thuaDat){
    	N = thuaDat.size() - 1;
        for (int i = N/2; i >= 0; i--)
            heap(thuaDat, i);        
    }
    public static void heap(List<ThuaDat> thuaDat, int i){ 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && splitAddress(thuaDat, left) > splitAddress(thuaDat, i))
            max = left;
		if (right <= N && splitAddress(thuaDat, right)> splitAddress(thuaDat, max))        
            max = right;
        if (max != i){
            swap(thuaDat, i, max);
            heap(thuaDat, max);
        }
    }    
    public static int splitAddress(List<ThuaDat> thuaDat, int n) {
    	String chuoi = thuaDat.get(n).getDiaChi();
    	int t = 0;
    	if(chuoi.contains("/")) {
    		String[] s = chuoi.split("/");
    		t = Integer.parseInt(s[0].toString());
    	}else {
    		String [] s = chuoi.split(" ");
    		t = Integer.parseInt(s[0].toString());
    	}
    	return t;
    }
    public static void swap(List<ThuaDat> thuaDat, int i, int j){
        ThuaDat temp = thuaDat.get(i);
        thuaDat.set(i, thuaDat.get(j));
        thuaDat.set(j, temp);
    }        
}
