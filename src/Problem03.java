import lfsr.LFSR;

public class Problem03 {
	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		Problem03 pr03 = new Problem03();
		String[] a = {
				"01000101", "01010101", "01100000", "01110000",
				"10000100", "10010100", "10100001", "10110001",
				"00001010", "00011010", "00101111", "00111111",
				"11001011", "11011011", "11101110", "11111110"
		};
		String[] keyPredict = null;
		String seed = "01100001";
		for(int i=0;i<a.length;i++)
		{
			LFSR lfsr = new LFSR(a[i],seed);
			//lfsr.testPrint();
			keyPredict[i] = lfsr.keyGen(48);
			System.out.println("Key: "+keyPredict[i]);
		}
		
		pr03.solve(keyPredict);
	}
	private void solve(String[] test)
	{
		
	}
}
