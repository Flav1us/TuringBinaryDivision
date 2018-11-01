package machine;

public class NoSuchInstruction extends Exception {
	private static final long serialVersionUID = 1L;
	public NoSuchInstruction(String message) {
		super(message);
	}
	public NoSuchInstruction(Instruction i) {
		super("Instruction not found: " + i.toString());
	}
	public NoSuchInstruction() {
		super("Instruction not found");
	}
}
