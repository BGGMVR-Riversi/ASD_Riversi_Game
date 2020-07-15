package main.java.edu.miu.cs.cs525.reversi.action_adapters;

public class ReversiActionEventFactory {
	
	public static ReversiActionEvent ac;
	
	public static void getActionPerformed(String s) {
		if(s == null) System.out.println("String is null");
		// BoardView_Timer_Action
		else if(s.equalsIgnoreCase("BoardView_Timer_Action")) {
			ac = new BoardView_Timer_Action();
		}
		// BoardView_PBMTimer_Action
		else if(s.equalsIgnoreCase("BoardView_PBMTimer_Action")) {
			ac = new BoardView_PBMTimer_Action();
		}
		// ChoosePlayerOkAction
		else if(s.equalsIgnoreCase("ChoosePlayerOkAction")) {
			ac = new ChoosePlayerOkAction();
		}
		// ChoosePlayerCancelAction
		else if(s.equalsIgnoreCase("ChoosePlayerCancelAction")) {
			ac = new ChoosePlayerCancelAction();
		}
		else if(s.equalsIgnoreCase("ChoosePlayerRadioComputerPlayerAction")) {
			ac = new ChoosePlayerRadioComputerPlayerAction();
		}
		else if(s.equalsIgnoreCase("ChoosePlayerRadioNetPlayerAction")) {
			ac = new ChoosePlayerRadioNetPlayerAction();
		};
	}

}
