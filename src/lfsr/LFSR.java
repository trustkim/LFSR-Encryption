package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	private int regLen;	// Length of register (and also feedback coefficient)
	
	/* Generating methods */
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"을 long 정수로 바꿔 valueOf로 BitSet으로 바꿔 저장
		regLen = seedStr.length();
		a = fromString(aStr);
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	private BitSet fromString(final String s)
	{	// String으로 2진수를 입력받아 BitSet으로 변환
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

	
	/* functioning methods */
	private boolean step()
	{ // LFSR 한 번 쉬프트 하는 과정
		boolean result; // 새로 생성한 키
		result = (a.get(0) && reg.get(0));
		//System.out.println("[debug] before step: "+result);
		//System.out.println(toString(reg));
		for(int i=1;i<regLen;i++)	// 레지스터 길이 만큼 반복
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
	{	// LFSR을 이용하여 길이 n개의 키를 생성
		BitSet keyStream = reg.get(0, regLen);	// step 하기 전에 reg에 저장된 seed를 먼저 복사해 둠
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
	{	// BitSet객체를 Long 타입으로 바꿔 2진수 형식으로 반환
		return Long.toString(bs.toLongArray()[0], 2);
	}
	public void testPrint() {
		// 테스트를 위한 출력
		System.out.println("feedback coefficiant: "+toString(a));
		System.out.println("seed: "+toString(reg));
	}
}