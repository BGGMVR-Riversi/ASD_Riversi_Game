package main.java.edu.miu.cs.cs525.reversi.memento;

import java.util.ArrayList;
import java.util.List;

import main.java.edu.miu.cs.cs525.reversi.iterator.Iterator;
import main.java.edu.miu.cs.cs525.reversi.iterator.MoveList;

public class History {
	private List<BoardState> undoList = new ArrayList<>();
	private List<BoardState> redoList = new ArrayList<>();
	
	private static History historyInstance;
	
	private History() {}
	
    public static History getInstance(){
        if(historyInstance == null){
            synchronized (History.class){
                if(historyInstance == null){
                	historyInstance = new History();
                }
            }
        }
        return historyInstance;
    }
	
	public void pushToUndoList(BoardState state) {
		undoList.add(state);
	}
	
	public void pushToRedoList(BoardState state) {
		redoList.add(state);
	}
	
	public BoardState popFromUndoList() {
	    int lastIndex = undoList.size() - 1;
	    if (lastIndex <= -1) {
			return null;
		}
	    BoardState lastState = undoList.get(lastIndex);
	    undoList.remove(lastState);
	    pushToRedoList(lastState);

	    return lastState;
	}
	
	public BoardState popFromRedoList() {
	    int lastIndex = redoList.size() - 1;
	    if (lastIndex <= -1) {
			return null;
		}
	    BoardState lastState = redoList.get(lastIndex);
	    redoList.remove(lastState);
	    pushToUndoList(lastState);

	    return lastState;
	}
	
	public void undoAllFromUndoList() {
		int counter = 0;
		MoveList moveList = new MoveList();
		Iterator it = moveList.getIterator();
		it.setType("undo");
		while (it.hasNext()) {
			it.next();
			counter++;
			popFromUndoList();
		}
		System.out.println("undo " + counter + " moves");
	}
	
	public void redoAllFromRedoList() {
		int counter = 0;
		MoveList moveList = new MoveList();
		Iterator it = moveList.getIterator();
		it.setType("redo");
		while (it.hasNext()) {
			it.next();
			counter++;
			popFromRedoList();
		}
		System.out.println("redo " + counter + " moves");
	}
	
	public void clearRedoHistory() {
		redoList.removeAll(redoList);
	}
	
	public void clearUndoHistory() {
		undoList.removeAll(undoList);
	}
	
	public int size(String type) {
		if (type.equalsIgnoreCase("undo")) {
			return undoList.size();
		} else if (type.equalsIgnoreCase("redo")){
			return redoList.size();
		} else {
			System.out.println("No size for unknown type");
			return -1;
		}
		
	}
}
