package fr.eni.encheres.ihm;

public class IHMException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public IHMException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public IHMException(String message, Throwable exception) {
		super(message, exception);
	}

	/**
	 * @param arg0
	 */
	public IHMException(String message) {
		super(message);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("IHM Exception - An error occured in ihm layer.");
		sb.append(super.getMessage());
		return sb.toString() ;
	}

}
