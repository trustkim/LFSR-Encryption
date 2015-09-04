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
		String keyPredict[] = new String[a.length];
		String seed = "01100001";
		System.out.println("========== keyGen ==========");
		for(int i=0;i<a.length;i++)
		{
			LFSR lfsr = new LFSR(a[i],seed);
			//lfsr.testPrint();
			keyPredict[i] = lfsr.keyGen(48);
			System.out.println((i+1)+"th Key: "+keyPredict[i]);
		}
		
		System.out.println("========== verify ==========");
		pr03.verify(keyPredict);
	}
	private void verify(String[] test)
	{
		for(int i=0;i<test.length;i++)
		{
			if(isPromissing(test[i]))
				System.out.println((i+1)+" is PROMISSING!");
		}
	}
	private boolean isPromissing(String keys)
	{
		return keys.substring(30, 34).equals("0000");
	}
}
