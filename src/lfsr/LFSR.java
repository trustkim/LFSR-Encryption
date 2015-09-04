package lfsr;

import java.util.BitSet;

/**
 * Linear Feedback Shift Register
 * @author trustkim
 * 기본 자료구조를 BitSet을 사용하여 구현함. Java의 BitSet은 사용하기 헷갈리고 불편하다.
 */
public class LFSR {
	private BitSet a;	// feedback coefficient
	private BitSet reg;	// register
	private int regLen;	// Length of register (and also feedback coefficient)
	
	/* Generating methods */
	public LFSR(String aStr, String seedStr)
	{
		regLen = seedStr.length();	// 레지스터 길이는 seed 길이에 맞게 최초 세팅해 줌
		a = fromString(aStr);		// initialize the feedback coefficient from aStr
		reg = fromString(seedStr);	// initialize the registers from seedStr
	}
	private BitSet fromString(final String s)
	{	// String으로 2진수를 입력받아 BitSet으로 변환
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

	
	/* functioning methods */
	private boolean step()
	{ // LFSR 한 번 쉬프트 하는 과정. linear function.
		boolean result; // 현재 스텝의 결과
		result = (a.get(0) && reg.get(0));
		for(int i=1;i<regLen;i++)	// 레지스터 길이 만큼 반복
		{
			result ^= (a.get(i)&&reg.get(i));
		}
		return result;
	}
	public String keyGen(int n)
	{	// LFSR을 이용하여 길이 n개의 키를 생성. seed가 최상위부터 채워짐.
		BitSet keyStream = reg.get(0, regLen);	// step 하기 전에 reg에 저장된 seed를 먼저 복사해 둠
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
	{	// 키스트림을 모두 왼쪽쉬프트함. 가중치가 가장 낮은 최하위 비트에는 step()의 결과 비트를 채워 넣음.
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
	{	// BitSet객체를 Long 타입으로 바꿔 2진수 형식으로 반환. 상위비트가 0이오면 출력안됨 주의해야함.
		return Long.toString(bs.toLongArray()[0], 2);
	}
}