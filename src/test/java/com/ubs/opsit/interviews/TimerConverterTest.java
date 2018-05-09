package com.ubs.opsit.interviews;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TimerConverterTest {

	private TimerService berlinTimer;

	@Test
	public void testConvertTime() throws Exception {
		String inputTime = "23:43:00";
		String expectedOutputInBerlinClockFormat = "Y\r\nRRRR\r\nRRRO\r\nYYRYYRYYOOO\r\nYYYO\r\n";
		berlinTimer = new BerlinClockTimerServiceImpl();
		assertThat(berlinTimer.calculateTime(inputTime)).isEqualTo(expectedOutputInBerlinClockFormat);
	}
}
