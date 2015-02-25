package advprog.lab2;

public class variable<T> {
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private T value;
	private int type;
	
	variable(String name, T value, int type){
		this.name = name;
		this.value = value;
		this.type=type;
	}
	variable(String name,int type){
		this.name = name;
		this.type= type;
		
	}
	/*public T add(T value){
		return (this.value + value);
		
		
	}*/
	
	
}
