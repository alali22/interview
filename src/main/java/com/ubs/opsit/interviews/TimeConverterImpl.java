package com.ubs.opsit.interviews;

public class TimeConverterImpl implements TimeConverter {
	
	private TimerService berlinTimerService;

	@Override
	public String convertTime(String aTime) {
		berlinTimerService = new BerlinClockTimerServiceImpl();
		try {
			return berlinTimerService.calculateTime(aTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
