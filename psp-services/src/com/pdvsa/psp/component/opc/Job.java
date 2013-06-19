package com.pdvsa.psp.component.opc;

public abstract class Job {
	protected long timeout;
	protected boolean canceled = false;
	protected Throwable error = null;
	protected abstract void run() throws Exception;

	protected abstract void interrupt();

	public Job(final long timeout) {
		this.timeout = timeout;
	}

	public long getTimeout() {
		return this.timeout;
	}

	public boolean isCanceled() {
		return this.canceled;
	}

	public Throwable getError() {
		return this.error;
	}

}
