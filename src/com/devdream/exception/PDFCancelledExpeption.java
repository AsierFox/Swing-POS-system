package com.devdream.exception;

public class PDFCancelledExpeption extends Exception {

	private static final long serialVersionUID = -8497174213754708593L;
	
	private static final String PDF_OPERATION_CANCELLED = "PDF generation cancelled.";
	
	@Override
	public String getMessage() {
		return PDF_OPERATION_CANCELLED;
	}
	
}
