package de.mygroup.powercore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.mygroup.powercore.model.User;

@SpringBootApplication(scanBasePackages = { "de.mygroup.powercore" })
public class PowercoreApplication {

	private static Set<User> loggedInUsers = new HashSet<>();

	public static void main(String[] args) {
		SpringApplication.run(PowercoreApplication.class, args);
	}

	public static void logInUser(User user) {
		loggedInUsers.add(user);
	}

	public static boolean isUserLoggedIn(User user) {
		return loggedInUsers.contains(user);
	}
}
