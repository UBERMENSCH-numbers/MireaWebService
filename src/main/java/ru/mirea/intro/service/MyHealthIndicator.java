package ru.mirea.intro.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MyHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = check();
		if (errorCode == 1) {
			return Health.down().status("Low free disk space").build();
		}
		return Health.up().build();
	}

	private int check() {
		File root = new File("/");
		if(((float) root.getUsableSpace() / root.getTotalSpace()) > 0.99) return 0;
		return 0;
	}

}
