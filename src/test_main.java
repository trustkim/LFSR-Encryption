import lfsr.LFSR;

public class test_main {
	public static void main(String[] args)
	{
		String[] a = {
				"10100010", "10101010", "00000110", "00001110",
				"00100001", "00101001", "10000101", "10001101",
				"01010000", "01011000", "11110100", "11111100",
				"11010011", "11011011", "01110111", "01111111"
		};
		String seed = "10000110";
		for(int i=0;i<15;i++)
		{
			LFSR lfsr = new LFSR(a[i],seed);
			lfsr.testPrint();
		}
	}
}
