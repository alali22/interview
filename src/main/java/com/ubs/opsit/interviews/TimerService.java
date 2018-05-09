package com.ubs.opsit.interviews;

import java.util.List;

public interface TimerService {
	
	public List<String> calculateHours(int hours);
	
	public List<String> calculateMinutes(int minutes);
	
	public List<String> calculateSeconds(int seconds);

	public String calculateTime(String standardTime) throws Exception;
	
	public String formatClockTime(List<String> seconds, List<String> hours, List<String> minutes);
	
}
