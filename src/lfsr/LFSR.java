package lfsr;

import java.util.BitSet;

/**
 * Linear Feedback Shift Register
 * @author trustkim
 * �⺻ �ڷᱸ���� BitSet�� ����Ͽ� ������. Java�� BitSet�� ����ϱ� �򰥸��� �����ϴ�.
 */
public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	private int regLen;	// Length of register (and also feedback coefficient)
	
	/* Generating methods */
	public LFSR(String aStr, String seedStr)
	{
		regLen = seedStr.length();	// �������� ���̴� seed ���̿� �°� ���� ������ ��
		a = fromString(aStr);		// initialize the feedback coefficient from aStr
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	private BitSet fromString(final String s)
	{	// String���� 2������ �Է¹޾� BitSet���� ��ȯ
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

	
	/* functioning methods */
	private boolean step()
	{ // LFSR �� �� ����Ʈ �ϴ� ����. linear function.
		boolean result; // ���� ������ ���
		result = (a.get(0) && reg.get(0));
		for(int i=1;i<regLen;i++)	// �������� ���� ��ŭ �ݺ�
		{
			result ^= (a.get(i)&&reg.get(i));
		}
		return result;
	}
	public String keyGen(int n)
	{	// LFSR�� �̿��Ͽ� ���� n���� Ű�� ����. seed�� �ֻ������� ä����.
		BitSet keyStream = reg.get(0, regLen);	// step �ϱ� ���� reg�� ����� seed�� ���� ������ ��
		System.out.println("feedback coefficiant: "+toString(a)+" ("+(a.size()/8)+")");
		for(int i=0;i<n-regLen;i++)
		{
			keyStream = leftShift(keyStream);
			if(step())
			{
				keyStream.set(0);
			}
			updateReg(keyStream);
		}
		return toString(keyStream);
	}
	private BitSet leftShift(BitSet bs)
	{	// Ű��Ʈ���� ��� ���ʽ���Ʈ��. ����ġ�� ���� ���� ������ ��Ʈ���� step()�� ��� ��Ʈ�� ä�� ����.
		BitSet result = new BitSet(bs.length()+1);
		for(int i=0;i<bs.length();i++)
		{
			if(bs.get(i))
				result.set(i+1);
		}
		return result;
	}
	private void updateReg(BitSet ks)
	{	// update register
		reg = new BitSet(regLen);
		for(int i=0;i<regLen;i++)
		{
			if(ks.get(i))
				reg.set(i);
		}
	}


	/* Printing methods */
	private String toString(BitSet bs)
	{	// BitSet��ü�� Long Ÿ������ �ٲ� 2���� �������� ��ȯ. ������Ʈ�� 0�̿��� ��¾ȵ� �����ؾ���.
		return Long.toString(bs.toLongArray()[0], 2);
	}
}