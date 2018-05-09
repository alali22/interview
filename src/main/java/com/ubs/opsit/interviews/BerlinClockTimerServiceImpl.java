package com.ubs.opsit.interviews;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * This Implementation class which converts Standard time into Berlin Clock time format.
 * @author alali/rafiq dhanani
 *
 */
public class BerlinClockTimerServiceImpl implements TimerService {

	private int hours;
	private int minutes;
	private int seconds;
	private String[] minutesTopPanel;
	private String[] minutesBottomPanel;
	private String[] hoursTopPanel;
	private String[] hoursBottomPanel;
	private final static String DELIMIT = ":";
	private final static int CLOCK_BASE_DIVISOR = 5;
	private final static int MAX_HOURS = 24;
	private final static int MAX_MINS = 59;
	private final static int QTR_INDX = 2;
	private final static int HLF_QTR_INDX = 5;
	private final static int LAST_QTR_INDX = 8;
	private final static String RED = "R";
	private final static String YELLOW = "Y";
	private final static String DEFAULT = "O";

	public BerlinClockTimerServiceImpl() {
		
		this.minutesTopPanel = new String[] { "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O" };
		this.minutesBottomPanel = new String[] { "O", "O", "O", "O" };
		this.hoursTopPanel = new String[] { "O", "O", "O", "O" };
		this.hoursBottomPanel = new String[] { "O", "O", "O", "O" };
	}

	/** 
	 * Main method which si entry point to calculate & shows berlin clock time 
	 * @throws Exception 
	 */
	@Override
	public String calculateTime(String time) throws Exception {
		if (StringUtils.isNotBlank(time) && time.indexOf(DELIMIT) > 0) {
			String[] tokens = time.split(DELIMIT);
			this.hours = Integer.parseInt(tokens[0]);
			this.minutes = Integer.parseInt(tokens[1]);
			this.seconds = Integer.parseInt(tokens[2]);
			List<String> formattedHours = calculateHours(hours);
			List<String> formattedMinutes = calculateMinutes(minutes);
			List<String> formattedSeconds = calculateSeconds(seconds);
			return formatClockTime(formattedSeconds, formattedHours, formattedMinutes);
		} else {
			return null;
		}
	}

	/**
	 * Method which finally format & display the berline clock time for specific Standard time provided
	 */
	public String formatClockTime(List<String> berlinSeconds, List<String> berlinHours, List<String> berlinMinutes) {
		StringBuilder sb = new StringBuilder();
		berlinSeconds.forEach(s -> sb.append(s).append(System.getProperty("line.separator")));
		berlinHours.forEach(h -> sb.append(h).append(System.getProperty("line.separator")));
		berlinMinutes.forEach(m -> sb.append(m).append(System.getProperty("line.separator")));
		return sb.toString();
	}

	/**
	 * Method which calculate the berlin clock hours 
	 */
	@Override
	public List<String> calculateHours(int hours) {
		int panelTop = 0, panelBottom = 0;
		if (hours >= 0 || hours < MAX_HOURS) {
			panelTop = hours / CLOCK_BASE_DIVISOR;
			panelBottom = hours % CLOCK_BASE_DIVISOR;
		}
		return formatHours(panelTop, panelBottom);
	}

	/**
	 * Method to format hours in Berlin Clock format
	 * @param top
	 * @param bottom
	 * @return
	 */
	public List<String> formatHours(int top, int bottom) {
		for (int x = 0; x < top; x++) {
			hoursTopPanel[x] = RED;
		}
		for (int y = 0; y < bottom; y++) {
			hoursBottomPanel[y] = RED;
		}
		List<String> hourIndicators = new ArrayList<String>();
		hourIndicators.add(String.join("", hoursTopPanel));
		hourIndicators.add(String.join("", hoursBottomPanel));
		return hourIndicators;
	}

	/**
	 * Method to calculate minutes for Berlin Clock
	 */
	@Override
	public List<String> calculateMinutes(int minutes) {
		int panelTop = 0, panelBottom = 0;
		if (minutes >= 0 || minutes < MAX_MINS) {
			panelTop = minutes / CLOCK_BASE_DIVISOR;
			panelBottom = minutes % CLOCK_BASE_DIVISOR;
		}
		return formatMinutes(panelTop, panelBottom);
	}

	/**
	 * Method to format minutes in Berlin Clock format
	 * @param top
	 * @param bottom
	 * @return
	 */
	private List<String> formatMinutes(int top, int bottom) {
		for (int x = 0; x < top; x++) {
			if (x == QTR_INDX) {
				minutesTopPanel[x] = RED;
			} else if (x == HLF_QTR_INDX) {
				minutesTopPanel[x] = RED;
			} else if (x == LAST_QTR_INDX) {
				minutesTopPanel[x] = RED;
			} else {
				minutesTopPanel[x] = YELLOW;
			}
		}
		for (int y = 0; y < bottom; y++) {
			minutesBottomPanel[y] = YELLOW;
		}
		List<String> minuteIndicators = new ArrayList<String>();
		minuteIndicators.add(String.join("", minutesTopPanel));
		minuteIndicators.add(String.join("", minutesBottomPanel));
		return minuteIndicators;
	}

	/**
	 * Method to calculate seconds and format the berlin clock display for it.
	 */
	@Override
	public List<String> calculateSeconds(int seconds) {
		List<String> secondIndicators = new ArrayList<String>();
		if (seconds > 0) {
			secondIndicators.add(DEFAULT);
		} else {
			secondIndicators.add(YELLOW);
		}
		return secondIndicators;
	}

}
