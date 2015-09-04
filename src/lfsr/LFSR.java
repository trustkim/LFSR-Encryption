package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	private int regLen;	// Length of register (and also feedback coefficient)
	
	/* Generating methods */
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"�� long ������ �ٲ� valueOf�� BitSet���� �ٲ� ����
		regLen = seedStr.length();
		a = fromString(aStr);
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	private BitSet fromString(final String s)
	{	// String���� 2������ �Է¹޾� BitSet���� ��ȯ
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

	
	/* functioning methods */
	private boolean step()
	{ // LFSR �� �� ����Ʈ �ϴ� ����
		boolean result; // ���� ������ Ű
		result = (a.get(0) && reg.get(0));
		//System.out.println("[debug] before step: "+result);
		//System.out.println(toString(reg));
		for(int i=1;i<regLen;i++)	// �������� ���� ��ŭ �ݺ�
		{
			//System.out.println("last result: "+result+", now: "+(a.get(i)&&reg.get(i)));
			//System.out.println("a["+i+"] AND reg["+i+"] = "+a.get(i)+" AND "+reg.get(i)+" = "+(a.get(i)&&reg.get(i)));
			result ^= (a.get(i)&&reg.get(i));
			//System.out.println("[debug] step: ["+i+"]: "+result);
		}
		//System.out.println(result);
		return result;
	}
	public String keyGen(int n)
	{	// LFSR�� �̿��Ͽ� ���� n���� Ű�� ����
		BitSet keyStream = reg.get(0, regLen);	// step �ϱ� ���� reg�� ����� seed�� ���� ������ ��
		System.out.println("feedback coefficiant: "+toString(a)+" ("+(a.size()/8)+")");
		for(int i=0;i<n-regLen;i++)
		{
			keyStream = leftShift(keyStream);
			if(step())
			{
				keyStream.set(0);
			}
			//System.out.println(toString(keyStream));
			updateReg(keyStream);
		}
		return toString(keyStream);
	}
	private BitSet leftShift(BitSet bs)
	{
		BitSet result = new BitSet(bs.length()+1);
		for(int i=0;i<bs.length();i++)
		{
			if(bs.get(i))
				result.set(i+1);
		}
		//System.out.println("after leftShift: "+toString(result));
		return result;
	}
	private void updateReg(BitSet ks)
	{
		reg = new BitSet(regLen);
		for(int i=0;i<regLen;i++)
		{
			if(ks.get(i))
				reg.set(i);
		}
		//System.out.println("updated: "+toString(reg));
	}

	
	/* Printing methods */
	private String toString(BitSet bs)
	{	// BitSet��ü�� Long Ÿ������ �ٲ� 2���� �������� ��ȯ
		return Long.toString(bs.toLongArray()[0], 2);
	}
	public void testPrint() {
		// �׽�Ʈ�� ���� ���
		System.out.println("feedback coefficiant: "+toString(a));
		System.out.println("seed: "+toString(reg));
	}
}