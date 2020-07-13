package main.java.edu.miu.cs.cs525.reversi.command;

public class Invoker {
	Command command;
	
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void executeOperation() {
		command.execute();
	}
}
