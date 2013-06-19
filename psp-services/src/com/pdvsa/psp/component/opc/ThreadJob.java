package com.pdvsa.psp.component.opc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ThreadJob extends Job {
	private final Log logger = LogFactory.getLog(ThreadJob.class);
	protected Thread runningThread;

	public ThreadJob(final long timeout) {
		super(timeout);
	}

	@Override
	protected synchronized void interrupt() {
		if (!this.canceled && this.runningThread != null) {
			logger.info("Interrupting current job");
			this.canceled = true;
			this.runningThread.interrupt();
		}
	}

	@Override
	protected void run() throws Exception {
		this.runningThread = Thread.currentThread();
		try {
			perform();
		} catch (final Throwable e) {
			this.error = e;
		} finally {
			synchronized (this) {
				this.runningThread = null;
			}
			if (Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(0);
				} catch (final InterruptedException e) {}
			}
		}
	}

	protected abstract void perform() throws Exception;
}
