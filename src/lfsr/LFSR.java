package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"�� long ������ �ٲ� valueOf�� BitSet���� �ٲ� ����
		a = fromString(aStr);
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	
	private boolean step()
	{ // LFSR �� �� ����Ʈ �ϴ� ����
		boolean result; // ���� ������ Ű
		result = (a.get(0) && reg.get(0));
		for(int i=1;i<reg.length();i++)
		{
			result = !((a.get(i)&&reg.get(i))||result);
		}
		return result;
	}
	
	public String keyGen(int n)
	{	// LFSR�� �̿��Ͽ� ���� n���� Ű�� ����
		BitSet keyStream = reg.get(0, reg.length());	// step �ϱ� ���� reg�� ����� seed�� ���� ������ ��
		for(int i=0;i<n-reg.length();i++)
		{
			keyStream = rightShift(keyStream);
			if(step())
				keyStream.set(0);
		}
		return toString(keyStream);
	}
	
	private BitSet rightShift(BitSet bs)
	{
		BitSet result = new BitSet(bs.length()+1);
		for(int i=0;i<bs.length();i++)
		{
			if(bs.get(i))
				result.set(i+1);
		}
		return result;
	}
	
	private static BitSet fromString(final String s)
	{	// String���� 2������ �Է¹޾� BitSet���� ��ȯ
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }
	
	private static String toString(BitSet bs)
	{	// BitSet��ü�� Long Ÿ������ �ٲ� 2���� �������� ��ȯ
		return Long.toString(bs.toLongArray()[0], 2);
	}

	public void testPrint() {
		// �׽�Ʈ�� ���� ���
		System.out.println("feedback coefficiant: "+toString(a));
		System.out.println("seed: "+toString(reg));
	}
}