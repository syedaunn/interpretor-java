package advprog.lab2;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class interpretor {

	/**
	 * @author Aunn Raza
	 */

	Hashtable<String, variable> vars;

	public interpretor(){
		vars = new java.util.Hashtable<String, variable>();
	}

	public void run(ArrayList<String> file) throws Exception{
		int i=0;
		for (String line : file) {
			line = line.toLowerCase();
			System.out.println("Line->\t"+line);

			if(line.contains("let")){					//Let i.e. variable declaration
				if(!line.startsWith("let")){
					throw new Exception("Syntax Error ("+i+")");
				}
				String[]  tok = line.split(" ");
				if(this.isInteger(tok[1])){
					throw new Exception("Syntax Error ("+i+")");
				}
				//tok[1].
				if(line.contains("=")){					//Initialized Variable
					if(tok[3].contains("\"") || tok[3].contains("'")){//string
						int length = tok.length;
						if(length >3){
							for(int b =4; b<length;b++){
								tok[3]=tok[3]+" "+tok[b];
							}
							vars.put(tok[1],new variable<String>(tok[1],tok[3],1) );
						}
						else
							vars.put(tok[1],new variable<String>(tok[1],tok[3],1) );
					}else if(tok[3].contains(".")){	//float
						vars.put(tok[1], new variable<Float>(tok[1],Float.parseFloat(tok[3]),2));
					}else{	//Integer
						//System.out.println("---->"+tok[1]);
						vars.put(tok[1], new variable<Integer>(tok[1],Integer.parseInt(tok[3]),3));
					}					
				}else{									//Just declared
				//	System.out.println("empty inserted");
					this.vars.put(tok[1], new variable<Integer>(tok[1],0,0));
				}
			}else if(line.contains("print")){					//Print keyword
				if(!line.startsWith("print")){
					throw new Exception("Syntax Error ("+i+")");
				}
				if(line.contains("\"")){ //output string

					System.out.println(line.split("\"")[1]);

				}else{
					String[] toks = line.split(" ");
					if(vars.containsKey(toks[1])){
						System.out.println(vars.get(toks[1]).getValue());
					}
					else{

						throw new Exception("Syntax Error ("+i+"): Undeclared Vairable");
					}
				}


			}else{
				variable toBeResult;
			//	System.out.println("op");
				String[] eq = line.split("=");
				//	System.out.println("-->"+eq[0]);
				if((toBeResult = vars.get(eq[0].trim())) == null){
					throw new Exception("Syntax Error ("+i+"): Lvalue should be modifable variable");
				}else{
					for(String ke :vars.keySet()){
						if(eq[1].contains(ke)){
							eq[1]=eq[1].replace(ke, (CharSequence) vars.get(ke).getValue().toString());
						}
					}
					String ans;
					try {
						ans=evaluate(eq[1].trim());
					}catch (Exception e){
						throw new Exception("Syntax Error ("+i+"): Undeclared Variable");
					}
					eq[0]=eq[0].trim();

					toBeResult=vars.get(eq[0]);

					int type = toBeResult.getType();
					vars.remove(eq[0]);
					if(type ==3){
						vars.put(eq[0], new variable<Integer>(eq[0],(int) Float.parseFloat(ans),3));
					}
					if(type ==2)
						vars.put(eq[0], new variable<Float>(eq[0],Float.parseFloat(ans),2));
					if(type ==1)
						vars.put(eq[0], new variable<String>(eq[0],ans,1));
					if(type==0){
						try{
							vars.put(eq[0], new variable<Integer>(eq[0],(int) Float.parseFloat(ans),1));
						}catch(NumberFormatException e){
							vars.put(eq[0], new variable<String>(eq[0],ans,1));
						}

					}


				}
			}
			i++;
		}

	}
	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
		return false;
		}

	}
	private String evaluate(String equation) throws Exception{

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("JavaScript");        
		Object result1 = se.eval(equation);
		return result1.toString();

	}

}
