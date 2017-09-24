package com.daou.ladmin.service.log;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.stereotype.Component;
/*
 * archive
 * 	- process: 5, parent 1, child 4
 * 	- logdir: mta, mss, remote
 * dsn
 * 	- process: 2, parent 1, child 1
 * 	- logdir: mta, mss, remote
 * gonote
 * 	- process: 2, parent 1, child 1
 * 	- logdir: notify
 * gopush
 * 	- process: 2, parent 1, child 1
 * 	- logdir: notify
 * logmon
 * 	- process: 8, parent 1, child 7
 * 	- logdir: mta, mss, remote, imap, pop, catalina/webmail, webmail(사용 안하고 있음)
 */
@Component
//@Scope(value = "prototype")
public class LogReader implements Parsable, Traceable, Closeable, Runnable {
//	private final String logDir;
//
//	public LogReader(String logDir) {
//		this.logDir = logDir;
//	}

	//@Autowired
	//private Log log;

	@Override
	public void getTrace() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setTrace() {
		// TODO Auto-generated method stub
	}

	@Override
	public void parse() {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("x:" + Log.INSTANCE.getLog());
		while(true) {
			//System.out.println(Thread.currentThread().getName() + ":::::::::::::::::::::::::::");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
