package com.gkatzioura;

import java.io.IOException;

import akka.actor.typed.ActorSystem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

	public static final String APP_NAME = "akka-java-app";

	public static void main(String[] args) {
		final ActorSystem<AppGuardian.GuardianMessage> appGuardian = ActorSystem.create(AppGuardian.create(), APP_NAME);
		appGuardian.tell(new AppGuardian.MessageToGuardian("First Akka Java App"));

		try {
			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		}
		catch (IOException ignored) {
		}
		finally {
			appGuardian.terminate();
		}
	}

}
