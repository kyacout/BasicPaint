package undoRedo;
import shape.Shape;

	public class State {
		private Shape prevState;
		private Shape currState;
		
		public State(Shape state1, Shape state2) {
			this.prevState = state1;
			this.currState = state2;
		}
		
		public Shape getPrevState() {
			return prevState;
		}
		
		public Shape getCurrState() {
			return currState;
		}
	}