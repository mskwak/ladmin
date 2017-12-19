package com.daou.ladmin.service.log;

import java.io.Closeable;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daou.ladmin.repository.MemberRepository;
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
public class LogReader implements Parsable, Traceable, Closeable, Runnable {
//	private final String logDir;
//
//	public LogReader(String logDir) {
//		this.logDir = logDir;
//	}

	//@Autowired
	//private Log log;

	@Autowired
	private MemberRepository memberRepository;

//	@Autowired
//	private Member member;

//	@Override
//	public void getTrace() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void setTrace() {
//		// TODO Auto-generated method stub
//	}

	@Override
	public void parse() {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	@Transactional
	public void run() {
		String log = Log.INSTANCE.getLog();

		while(true) {
//			System.out.println("x:" + log);
//			getTrace(log);
//			parse();
//			setTrace(log);
			System.out.println(Thread.currentThread().getName() + ":::::::::::::::::::::::::::" + log);
//
//			for(long i = 1; i < 30; i++) {
//				Member member = new Member();
//				member.setId(i);
//				member.setUserName("mskw");
//				member.setEmailId("mskw@daou.co.kr");
//				memberRepository.save(member);
//				TimeUtils.sleepSecond(1);
//			}
//
//			break;
//			//Xxx x = new Xxx();
//			//x.xxx();
		}
	}
}
