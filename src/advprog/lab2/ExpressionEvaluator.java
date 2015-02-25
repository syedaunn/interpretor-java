package advprog.lab2;

import java.util.*;
import javax.script.*;
public class ExpressionEvaluator {
	Stack<Integer> operands;
	Stack<Character> operators;
	String input;
	
	/*public static void main(String[] args) {

		ExpressionEvaluator stack=new ExpressionEvaluator("(2+7*3+5)");
		stack.stringOperation();
		System.out.println(stack.operands.pop());
	}*/
	 public static void main(String []args){

	        try {


	        String xyz = "3*3+3";
	        String kkk = "(100 % 6)* 7";

	        ScriptEngineManager manager = new ScriptEngineManager();
	        ScriptEngine se = manager.getEngineByName("JavaScript");        
	        Object result1 = se.eval(xyz);
	        Object result2 = se.eval(kkk);


	        System.out.println("result1: "+result1.toString());
	        System.out.println("result2: "+result2);


	        } 
	        catch (Exception e) {
	         e.printStackTrace();
	        }
	     }
	public ExpressionEvaluator(String in){
		input=in;
		operands= new Stack<Integer>();
		operators = new Stack<Character>();
	}

	public void stringOperation(){
		for(int i =0; i< input.length();i++){
			
			char temp =input.charAt(i);
			if(temp== '('){
				continue;
			}
			else if(Character.isDigit(temp)){
				operands.push(Integer.parseInt(input.substring(i, i+1)));
			}
			else if(temp == '+' || temp == '-' || temp == '*' || temp == '/'){
				operators.push(temp);
			}
			else if(temp== ')'){
				int a[] = {operands.pop(),operands.pop()};
				char operator = operators.pop();
				if(operator == '+'){
					operands.push(a[0]+ a[1]);
				}
				if(operator == '-'){
					operands.push(a[0]- a[1]);
				}
				if(operator == '*'){
					operands.push(a[0]* a[1]);
				}
				if(operator == '/'){
					operands.push(a[0]/ a[1]);
				}
			}
			else{
				int a[] = {operands.pop(),operands.pop()};
				char operator = operators.pop();
				if(operator == '+'){
					operands.push(a[0]+ a[1]);
				}
				if(operator == '-'){
					operands.push(a[0]- a[1]);
				}
				if(operator == '*'){
					operands.push(a[0]* a[1]);
				}
				if(operator == '/'){
					operands.push(a[0]/ a[1]);
				}
				
			}
			
		}
				
	}

	
	public int evaluate(String expression) {
        char[] tokens = expression.toCharArray();
 
         
        Stack<Integer> values = new Stack<Integer>();
 
      
        Stack<Character> ops = new Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
             
            if (tokens[i] == ' ')
                continue;
 
           
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
 
           
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
           
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/')
            {
               
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
               
                ops.push(tokens[i]);
            }
        }
 
        
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        
        return values.pop();
    }
 
	 public static boolean hasPrecedence(char op1, char op2)
	    {
	        if (op2 == '(' || op2 == ')')
	            return false;
	        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
	            return false;
	        else
	            return true;
	    }
	 
	   
	    public static int applyOp(char op, int b, int a)
	    {
	        switch (op)
	        {
	        case '+':
	            return a + b;
	        case '-':
	            return a - b;
	        case '*':
	            return a * b;
	        case '/':
	            if (b == 0)
	                throw new
	                UnsupportedOperationException("Cannot divide by zero");
	            return a / b;
	        }
	        return 0;
	    }
		

	
}