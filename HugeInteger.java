package hugeinteger;

public class HugeInteger{
	private int[] integer = new int[40];     //0
	private boolean sign;
	private int length;
	public HugeInteger(String str) {
		if (str.contains("-")) {
			sign = false;
			str = str.substring(1);
		}
		else sign = true;
		if (str.length() > 40) str = str.substring(str.length() - 40);
		length = str.length();
		int j = 0;
		for (int i = 39; i > 39 - length; i--) {     //from the end
			integer[i] = Integer.valueOf(str.substring(length - 1 - j, length-j));	
			j++;	
		}
	}
	private boolean isPositive(int[] array) {
		boolean sign2 = true;      //include 0
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				break;
			}
			else if (array[i] < 0) {
				sign2 = false;    
				break;
			}
		}
		return sign2;
	}
	private int[] carryPositive(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			while (array[i] > 9) {
				array[i] -= 10;
				array[i-1] += 1;
			}
		}		
		while (array[0] > 9) array[0] -= 10;
		return array;
	}
	private int[] carryNegative(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			while (array[i] > 0) {
				array[i] -= 10;
				array[i-1] += 1;
			}
		}		
		return array;
	}
	private String arrayToString(int[] array) {
		int startPoint = 0;
		String s = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0) startPoint += 1;
			else break;
		}
		for (; startPoint < array.length; startPoint++)
			s += Math.abs(array[startPoint]);
		if (s == "") s = "0";
		return s;
	}
	private HugeInteger addSame(HugeInteger a) {
		int[] integerAS = new int[40];
		String s = "";
		for (int i = 39; i >= 0; i--) 
			integerAS[i] = integer[i] + a.integer[i];
		integerAS = carryPositive(integerAS);
		s += arrayToString(integerAS);
		HugeInteger A = new HugeInteger(s);
		return A;
	}
	public HugeInteger add(HugeInteger a) {
		int[] integerA = new int[40];
		HugeInteger A;
		if (sign == a.sign) A = addSame(a);
		else if (sign == false) A = a.subSame(this);
		else A = subSame(a);
		return A;
	}
	private HugeInteger subSame(HugeInteger s) {
		int[] integerS = new int[40];
		String str = "";
		for (int i = 39; i >= 0; i--) 
			integerS[i] = integer[i] - s.integer[i];
		if (isPositive(integerS)) integerS = carryPositive(integerS);
		else { 
			integerS = carryNegative(integerS);
			str = "-";
		}
		str += arrayToString(integerS);
		HugeInteger b = new HugeInteger(str);
		return b;
	}
	public HugeInteger sub(HugeInteger s) {
		int[] integerA = new int[40];
		HugeInteger S;
		if (sign == s.sign) {
			S = subSame(s);
			S.sign = !(sign ^ s.sign);
		}
		else if (sign == false) {
			S = addSame(s);
			S.sign = false;
		}
		else S = addSame(s);
		return S;
	}
	public void input(String str) {
		if (str.contains("-")) {
			sign = false;
			str = str.substring(1);
		}
		else sign = true;
		if (str.length() > 40) str = str.substring(str.length() - 40);
		length = str.length();
		int j = 0;
		for (int i = 0; i < 40; i++)
			integer[i] = 0;
		for (int i = 39; i > 39 - length; i--) {     //from the end
			integer[i] = Integer.valueOf(str.substring(length-1-j, length-j));	
			j++;	
		}
	}
	public void output() {
		String s = "";
		if (sign) s = arrayToString(integer);
		else s = "-" + arrayToString(integer);
		System.out.println(s);
	}
	public boolean isGreaterThan(HugeInteger a) {
		if ((a.sub(this)).sign) return false;     
		else return true;
	}
	public boolean isLessThan(HugeInteger a) {
		if ((this.sub(a)).sign) return false;
		else return true;
	}
	public boolean isEqualTo(HugeInteger a) {
		if (isGreaterThan(a)||isLessThan(a)) return false;
		else return true;
	}
	public boolean isNotEqualTo(HugeInteger a) {
		if (isEqualTo(a)) return false;
		else return true;
	}
	public boolean isGreaterThanOrEqualTo(HugeInteger a) {
		if ((this.sub(a)).sign) return true;
		else return false;
	}
	public boolean isLessThanOrEqualTo(HugeInteger a) {
		if ((a.sub(this)).sign) return true;     
		else return false;
	}
}