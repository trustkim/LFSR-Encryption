package lfsr;

import java.util.BitSet;

public class LFSR {
	private BitSet a = new BitSet(8);	// feedback coefficient
	private BitSet ik = new BitSet(8);	// seed
	public LFSR(String aStr, String seedStr)
	{	// "10000011", "10100101"을 long 정수로 바꿔 valueOf로 BitSet으로 바꿔 저장
		a = fromString(aStr);
		ik = fromString(seedStr);
	}
	
//	public LFSR(byte[] aByte, byte[] seedByte)
//	{	// byte 정수로 주어진 값을 각각 저장
//		a = BitSet.valueOf(aByte);
//		ik = BitSet.valueOf(seedByte);
//	}
	
	private void step()
	{ // LFSR 한 번 쉬프트 하는 과정
		
	}
	
	private void keyGen(int n)
	{	// LFSR을 이용하여 길이 n개의 키를 생성
		
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
		System.out.println(toString(a));
		System.out.println(toString(ik));
	}
}