package main.java.edu.miu.cs.cs525.reversi.action_adapters;

public class ActionEventFactory {
	
	public static ActionEvent ac;
	
	public static void getActionPerformed(String s) {
		if(s == null) System.out.println("String is null");
		else if(s.equalsIgnoreCase("BoardView_timer_actionAdapter")) {
			ac = new BoardView_timer_actionAdapter();
		};
	}

}
