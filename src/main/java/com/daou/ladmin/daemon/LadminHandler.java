package com.daou.ladmin.daemon;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daou.ladmin.config.Constants;
import com.daou.ladmin.daemon.protocol.LadminProtocol;
import com.daou.ladmin.util.LadminProtocolUtils;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
@Component
public class LadminHandler extends SimpleChannelInboundHandler<String> {
	@Autowired
	private Map<String, LadminProtocol> ladminProtocolMap;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(LadminProtocolUtils.getResponse(Constants.GREETING_MESSAGE));
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String command) throws Exception {
		Map<String, String> map = LadminProtocolUtils.parse(command);

		if(map.isEmpty()) {
			ctx.writeAndFlush(LadminProtocolUtils.getBadResponse(Constants.INVALID_COMMAND));
			return;
		}

		LadminProtocol ladminProtocol = this.ladminProtocolMap.get(map.get("protocol"));

		if(ladminProtocol == null) {
			ctx.writeAndFlush(LadminProtocolUtils.getBadResponse(map.get("tag"), Constants.INVALID_COMMAND));
			return;
		}

		// block until job is completed
		String response = ladminProtocol.execute(map);
		ChannelFuture channelFuture = ctx.writeAndFlush(response);

		if(ladminProtocol.isClose()) {
			channelFuture.addListener(ChannelFutureListener.CLOSE);
		}
	}
}
