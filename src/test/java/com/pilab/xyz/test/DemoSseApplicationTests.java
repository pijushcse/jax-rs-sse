package com.pilab.xyz.test;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;

import org.junit.Before;
import org.junit.Test;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoSseApplicationTests {

	private Client client;
	private WebTarget uri;

	@Before
	public void initClient() {
		this.client = ClientBuilder.newClient();
		this.uri = this.client.target("http://localhost:8080/phx-rest/api/ping");
	}

	@Test
	public void init() throws IOException, InterruptedException {
		SseEventSource sse = SseEventSource.target(this.uri).reconnectingEvery(2, SECONDS).build();
		sse.register(this::onMessage);
		sse.open();
		Thread.currentThread().join();
	}

	void onMessage(InboundSseEvent event) {
		String id = event.getId();
		String name = event.getName();
		String payload = event.readData();
		String comment = event.getComment();
		System.out.println("Message received: " + payload);
	}
}
