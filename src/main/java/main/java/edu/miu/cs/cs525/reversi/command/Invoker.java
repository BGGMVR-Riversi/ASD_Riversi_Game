package main.java.edu.miu.cs.cs525.reversi.command;

public class Invoker {
	Command command;
	
	private Invoker(Command command) {
		this.command = command;
	}

	public static Invoker invokerFactory(Command command){
		return new Invoker(command);
	}
	
	public void executeOperation() {
		command.execute();
	}
}
