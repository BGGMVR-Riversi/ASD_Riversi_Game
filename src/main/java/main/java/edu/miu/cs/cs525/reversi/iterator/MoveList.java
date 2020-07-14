package main.java.edu.miu.cs.cs525.reversi.iterator;

import main.java.edu.miu.cs.cs525.reversi.memento.History;

// Concrete Aggregate
public class MoveList implements Collection {
	
	private History history = History.getInstance();

	@Override
	public Iterator getIterator() {
		return new MoveIterator();
	}
	
	private class MoveIterator implements Iterator {
		
		int index;
		String type;

		@Override
		public boolean hasNext() {
			if (index < history.size(type)) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()) {
				return (History) history;
			}
			return null;
		}

		@Override
		public void setType(String type) {
			this.type = type;
		}
		
	}

}
