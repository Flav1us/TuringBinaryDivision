package machine;

public class Instruction {
	public static enum Move {L, R, stay}
	
	public Instruction(String start_state, String[] input_symbols, String new_state, String output_symbols[], Move move) {
		super();
		this.input_symbols = input_symbols;
		this.start_state = start_state;
		this.new_state = new_state;
		this.move = move;
		this.output_symbols = output_symbols;
	}
	public String[] input_symbols;
	public String start_state;
	
	public String new_state;
	public Move move; 
	public String[] output_symbols;
	
	private void validate() {
	}
}
