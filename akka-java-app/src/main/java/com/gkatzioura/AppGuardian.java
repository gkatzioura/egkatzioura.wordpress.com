package com.gkatzioura;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AppGuardian extends AbstractBehavior<AppGuardian.GuardianMessage> {

	public interface GuardianMessage {}

	static Behavior<GuardianMessage> create() {
		return Behaviors.setup(AppGuardian::new);
	}

	@Getter
	@AllArgsConstructor
	public static class MessageToGuardian implements GuardianMessage {
		private String message;
	}

	private AppGuardian(ActorContext<GuardianMessage> context) {
		super(context);
	}

	@Override
	public Receive<GuardianMessage> createReceive() {
		return newReceiveBuilder().onMessage(MessageToGuardian.class, this::receiveMessage).build();
	}

	private Behavior<GuardianMessage> receiveMessage(MessageToGuardian messageToGuardian) {
		getContext().getLog().info("Message received: {}",messageToGuardian.getMessage());
		return this;
	}

}
