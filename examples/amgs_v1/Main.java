public class Main {
	public static void main(String[] args) {
		
		// observable
		GlobalVariables g1 = new GlobalVariables(1);
		// observer
		Window1 w1 = new Window1("test window 1", g1);
		g1.addObserver(w1);
		
	}
}
