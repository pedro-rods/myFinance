package exception;

public class RunTimeExceptionHandler extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RunTimeExceptionHandler() {
		super();
	}

	public RunTimeExceptionHandler(String msg) {
		super(msg);
	}
}