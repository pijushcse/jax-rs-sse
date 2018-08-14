package com.pilab.xyz;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("/api")
public class Api {

	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@Path("/ping")
	public void healthCheck(@Context SseEventSink link, @Context Sse sse) throws Exception {
		int i = 101;
		while (i < 110) {
			OutboundSseEvent sseEvent = sse.newEventBuilder().name("Id").id(String.valueOf(i++))
					.mediaType(MediaType.APPLICATION_JSON_TYPE)
					.data(Person.class, new Person(UUID.randomUUID().toString())).reconnectDelay(3000)
					.comment("Random UUID").build();

			link.send(sseEvent);
			Thread.sleep(2000);
		}
	}
}
