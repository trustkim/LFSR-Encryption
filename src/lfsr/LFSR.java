package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"을 long 정수로 바꿔 valueOf로 BitSet으로 바꿔 저장
		a = fromString(aStr);
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	
	private boolean step()
	{ // LFSR 한 번 쉬프트 하는 과정
		boolean result; // 새로 생성한 키
		result = (a.get(0) && reg.get(0));
		for(int i=1;i<reg.length();i++)
		{
			result = !((a.get(i)&&reg.get(i))||result);
		}
		return result;
	}
	
	public String keyGen(int n)
	{	// LFSR을 이용하여 길이 n개의 키를 생성
		BitSet keyStream = reg.get(0, reg.length());	// step 하기 전에 reg에 저장된 seed를 먼저 복사해 둠
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
	{	// String으로 2진수를 입력받아 BitSet으로 변환
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }
	
	private static String toString(BitSet bs)
	{	// BitSet객체를 Long 타입으로 바꿔 2진수 형식으로 반환
		return Long.toString(bs.toLongArray()[0], 2);
	}

	public void testPrint() {
		// 테스트를 위한 출력
		System.out.println("feedback coefficiant: "+toString(a));
		System.out.println("seed: "+toString(reg));
	}
}