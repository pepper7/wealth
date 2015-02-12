package org.solt.wealth.model.view;

import org.solt.wealth.model.Journal;

public class JournalView extends Journal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8151952914630039927L;

	private int dailyCount;

	public JournalView() {
		super();
	}

	public int getDailyCount() {
		return dailyCount;
	}

	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}

}
