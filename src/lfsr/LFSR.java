package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a = new BitSet(8);	// feedback coefficient
	private BitSet ik = new BitSet(8);	// seed
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"�� long ������ �ٲ� valueOf�� BitSet���� �ٲ� ����
		a = fromString(aStr);
		ik = fromString(seedStr);
	}
	
//	public LFSR(byte[] aByte, byte[] seedByte)
//	{	// byte ������ �־��� ���� ���� ����
//		a = BitSet.valueOf(aByte);
//		ik = BitSet.valueOf(seedByte);
//	}
	
	private void step()
	{ // LFSR �� �� ����Ʈ �ϴ� ����
		
	}
	
	private void keyGen(int n)
	{	// LFSR�� �̿��Ͽ� ���� n���� Ű�� ����
		
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
		System.out.println(toString(a));
		System.out.println(toString(ik));
	}
}