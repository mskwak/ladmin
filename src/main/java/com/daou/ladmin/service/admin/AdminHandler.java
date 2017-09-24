package com.daou.ladmin.service.admin;

import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.daou.ladmin.config.Constants;
import com.daou.ladmin.service.admin.protocol.LadminProtocol;
import com.daou.ladmin.service.admin.protocol.Protocol;
import com.daou.ladmin.util.LadminUtils;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
@Component
public class AdminHandler extends SimpleChannelInboundHandler<String> {
	private static final Logger logger = LoggerFactory.getLogger(AdminHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(LadminUtils.getResponse(Constants.GREETING_MESSAGE));
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String command) throws Exception {
		Map<String, String> map = LadminUtils.parse(command);

		if(map.isEmpty()) {
			ctx.writeAndFlush(LadminUtils.getBadResponse(Constants.INVALID_COMMAND));
			return;
		}

		String protocolName = map.get("protocol");
		Map<String, LadminProtocol> protocolMap = Protocol.getMap();
		LadminProtocol ladminProtocol = null;

		try {
			ladminProtocol = protocolMap.keySet()
				.stream()
				.filter(str -> protocolName.equals(str))
				.map(str -> protocolMap.get(str))
				.findAny()
				.get();
		} catch(NoSuchElementException e) {
			logger.error("NoSuchLadminProtocol", e);
			ctx.writeAndFlush(LadminUtils.getBadResponse(map.get("tag"), Constants.INVALID_COMMAND));
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
		ctx.writeAndFlush(LadminUtils.getBadResponse(Constants.INVALID_COMMAND));
		return;
	}
}