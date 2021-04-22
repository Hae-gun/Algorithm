package sandbox;

public class EnumTest {
	public static void main(String[] args) {
		
	}
}





enum Operation{
	SUM,SUB,MUL,DIV;
	
	public int doAction(int num1, int num2) {
		int result = 0;
		switch (this) {
		case SUM:
			result =  num1+num2;
			break;
			
		case SUB:
			result =  num1-num2;
			break;
			
		case MUL:
			result =  num1*num2;
			break;
			
		case DIV:
			result =  num1/num2;
			break;

		default:
			break;
		}
		
		return result;
	}	
}