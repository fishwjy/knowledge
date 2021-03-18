import java.io.Serializable; 

// javap -v Test.class

public class Test implements Serializable, Cloneable{ 
	  private int num = 1; 
	  String str = "abc"; 
	  
	  public int add(int i) { 
	      num = num + i; return num; 
	}
}