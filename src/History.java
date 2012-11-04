import java.util.Stack;

import shape.Shape;


public class History {
	private static Stack<State> undo = new Stack<>();
	private static Stack<State> redo = new Stack<>();
	
	/**
	 * 
	 * @param prevState: A reference to the previous state of the object, if the object is new, this should be null.
	 * @param currState: A reference to the current state of the object, if the object is deleted, this should be null.
	 */
	public static void addState(Shape prevState, Shape currState) {
		undo.push(new State(prevState, currState));
		redo.empty();
	}
	
	/**
	 * 
	 * @return: returns the previous state of the program. If no previous states are available, it returns null.
	 */
	public static State Undo() {
		if (undo.isEmpty())
			return null;
		
		State temp = undo.pop();
		redo.push(new State(temp.getCurrState(), temp.getPrevState()));
		return temp;
	}
	
	/**
	 * 
	 * @return: returns the next state of the program. If no next states are available, it returns null.
	 */
	public static State Redo() {
		if (redo.isEmpty())
			return null;
		
		State temp = redo.pop();
		undo.push(new State(temp.getCurrState(), temp.getPrevState()));
		return temp;
	}
}
