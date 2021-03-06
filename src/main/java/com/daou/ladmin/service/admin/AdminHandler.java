package com.daou.ladmin.service.admin;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daou.ladmin.config.Constants;
import com.daou.ladmin.service.admin.protocol.LadminProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
@Component
public class AdminHandler extends SimpleChannelInboundHandler<ByteBuf> {
	private static final Logger logger = LoggerFactory.getLogger(AdminHandler.class);

	@Autowired
	Map<String, LadminProtocol> ladminProtocolMap;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(LadminProtocol.getResponse(Constants.GREETING_MESSAGE));
	}

// NullPointerException occured. why?
//	static {
//		Map<String, LadminProtocol> m = SpringBean.getBeansOfType(LadminProtocol.class);
//		Optional<String> longestString = m.keySet().stream().max(Comparator.comparingInt(String::length));
//		System.out.println("longestString:" + longestString);
//	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
		String command = byteBuf.toString(StandardCharsets.UTF_8);

		int x = LadminProtocol.getProtocolNameLength();

		System.out.println("x: " + x);


//		if(command.isReadable()) {
//			System.out.println("oooooooooooooookkkkkkkkkkkkkkkkkkk");
//		}
//		Map<String, String> map = LadminUtils.parse(command.toString(Charset.forName("UTF-8")));

		Map<String, String> map = LadminProtocol.parse(command);

		if(map.isEmpty()) {
			ctx.writeAndFlush(LadminProtocol.getBadResponse(Constants.INVALID_COMMAND));
			return;
		}

		//String protocolName = map.get("protocol");
		//Map<String, LadminProtocol> protocolMap = Protocol.getMap();
		//LadminProtocol ladminProtocol = null;

//		try {
//			ladminProtocol = protocolMap.keySet()
//				.stream()
//				.filter(str -> protocolName.equals(str))
//				.map(str -> protocolMap.get(str))
//				.findAny()
//				.get()
//		} catch(NoSuchElementException e) {
//			logger.error("NoSuchLadminProtocol", e);
//			ctx.writeAndFlush(LadminUtils.getBadResponse(map.get("tag"), Constants.INVALID_COMMAND));
//			return;
//		}

		String protocolName = map.get("protocol");
		LadminProtocol ladminProtocol = null;

		Optional<LadminProtocol> l = ladminProtocolMap.keySet().stream()
				.filter(str -> protocolName.equals(str))
				.map(str -> ladminProtocolMap.get(str))
				.findAny();

		if(l.isPresent()) {
			ladminProtocol = l.get();
		} else {
			ctx.writeAndFlush(LadminProtocol.getBadResponse(map.get("tag"), Constants.INVALID_COMMAND));
			return;
		}

		// block until job is completed
		String response = ladminProtocol.execute(map);
		ChannelFuture channelFuture = ctx.writeAndFlush(response);

		if(ladminProtocol.isClose()) {
			channelFuture.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)	throws Exception {
		logger.error("", cause);
		ctx.writeAndFlush(LadminProtocol.getBadResponse(Constants.INVALID_COMMAND));
		return;
	}
}
