package machine;

public class NoSuchInstruction extends Exception {
	private static final long serialVersionUID = 1L;
	public NoSuchInstruction(String message) {
		super(message);
	}
	public NoSuchInstruction(Instruction ins) {
		StringBuilder msg = new StringBuilder();
		msg.append("Instruction not found: ");
		msg.append(ins.start_state + "\t");
		for(int i = 0; i < ins.input_symbols.length; i++) {
			msg.append(" " + ins.input_symbols[i]);
		}
		this.initCause(new NoSuchInstruction(msg.toString()));
	}
	public NoSuchInstruction() {
		super("Instruction not found");
	}

}
