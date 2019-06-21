package it.pgArnaldo.Esame;

import java.util.ArrayList;

public class MathUtil {
	public static boolean isPrime(long number) {
		
		if(number > 2 && number % 2 == 0)
			return false;
		
		long top = (int)Math.sqrt(number) + 1;
		
		for(long i = 3; i <= top; i+=2) 
			if( number % i == 0)
				return false;
		
		return true;
	}
	
	public static ArrayList<Long> primeDivisors(long number) {
		
		ArrayList<Long> divisors = new ArrayList<Long>();
		
		if(isPrime(number)) {
			divisors.add(number);
			return divisors;
		}
		if( number > 2 && number % 2 == 0) {
			divisors.add((long)2);
			number /= 2;
		}
		for(long i = 3; i < number; i+=2)
			if(isPrime(i) && (number % i == 0)) {
				divisors.add(i);
				number /= i;
				
		}
		
		return divisors;
	}
	
	public static long[] TwoCoprimesNumbersThatMultipliedGiveTheInputedNumber(long N) {
		
		long num1 = 0, num2 = 0, i, tmpN = N;
		
		ArrayList<Long> divisorsOfN = new ArrayList<Long>(primeDivisors(N));
		num1 = divisorsOfN.get(divisorsOfN.size()-1);
		
		for(i = 0; (tmpN % num1) == 0; i++)
			tmpN /= num1;
		
		num1 = (long)Math.pow(num1, i);
		num2 = N/num1;
		
		long[] theNumbers = {num1, num2};
		
		return theNumbers;
	}
	
	public static int MCD(int x, int y) {
		
		while( x != y){
			if( x > y) x -= y;
			else y -= x;
		}
		
		return x;
	}
}
