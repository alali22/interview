package com.ubs.opsit.interviews;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ubs.opsit.interviews.BerlinClockTimerServiceImpl;
import com.ubs.opsit.interviews.TimerService;

public class BerlinClockTimerServiceTest {
	
	private TimerService berlinTimerService;

	@Test
	public void testDisplayTime() {
		berlinTimerService = new BerlinClockTimerServiceImpl();
		List<String> seconds = new ArrayList<String>();
		seconds.add("0");
		List<String> hours = new ArrayList<String>();
		hours.add("OOOO");
		hours.add("OOOO");
		List<String> minutes = new ArrayList<String>();
		minutes.add("OOOOOOOOOOO");
		minutes.add("OOOO");
		String exepctedFormattedOutput = "0\r\nOOOO\r\nOOOO\r\nOOOOOOOOOOO\r\nOOOO\r\n";
		assertThat(berlinTimerService.formatClockTime(seconds, hours, minutes)).isEqualTo(exepctedFormattedOutput);
	}
	
	@Test
	public void testFormatHours() {
		BerlinClockTimerServiceImpl berlinTimerService = new BerlinClockTimerServiceImpl();
		List<String> expectedFormattedHours = new ArrayList<String>();
		expectedFormattedHours.add("RROO");
		expectedFormattedHours.add("RRRO");
		assertThat(berlinTimerService.formatHours(2, 3)).isEqualTo(expectedFormattedHours);
	}
	
	@Test
	public void testCalculateHoursWithValidInput() {
		int testInputHours = 13; 
		berlinTimerService = new BerlinClockTimerServiceImpl();
		List<String> expectedHours = new ArrayList<String>();
		expectedHours.add("RROO");
		expectedHours.add("RRRO");
		assertThat(berlinTimerService.calculateHours(testInputHours)).isEqualTo(expectedHours);
	}
	
	@Test 
	public void testCalculateMinutesWithValidInput() {
		int testInputMins = 59;
		berlinTimerService = new BerlinClockTimerServiceImpl();
		List<String> expectedMins = new ArrayList<String>();
		expectedMins.add("YYRYYRYYRYY");
		expectedMins.add("YYYY");
		assertThat(berlinTimerService.calculateMinutes(testInputMins)).isEqualTo(expectedMins);
	}
	
	@Test 
	public void testCalculateSecondsWithValidInput() {
		int testInputSeconds = 0;
		berlinTimerService = new BerlinClockTimerServiceImpl();
		List<String> expectedOutput = new ArrayList<String>();
		expectedOutput.add("Y");
		assertThat(berlinTimerService.calculateSeconds(testInputSeconds)).isEqualTo(expectedOutput);
	}
	
	@Test
	public void testCalculateTimeWithValidInput() throws Exception {
		berlinTimerService = new BerlinClockTimerServiceImpl();
		String testInputTime = "24:00:00";
		//String testInputTime = "00:00:00";
		//String expectedBerlinFormatTime = "Y\r\nOOOO\r\nOOOO\r\nOOOOOOOOOOO\r\nOOOO\r\n";
		String expectedBerlinFormatTime = "Y\r\nRRRR\r\nRRRR\r\nOOOOOOOOOOO\r\nOOOO\r\n";
		assertThat(berlinTimerService.calculateTime(testInputTime)).isEqualTo(expectedBerlinFormatTime);
	}
	
	@Test
	public void testCalculateTimeWithInvalidInput() throws Exception {
		berlinTimerService = new BerlinClockTimerServiceImpl();
		String testInputTime = "hvjv kbb jhbvjh";
		String sr= berlinTimerService.calculateTime(testInputTime);
		assertNull(sr);
	}
	
	@Test
	public void testCalculateTimeWithInputAsNull() throws Exception {
		berlinTimerService = new BerlinClockTimerServiceImpl();
		String sr= berlinTimerService.calculateTime(null);
		assertNull(sr);
	}
	

}
