package com.project.LeaugeOfLegendsApp.difficulty;

public enum EDifficulty {
	EASY(40), MEDIUM(60), HARD(80), SUPER_HARD(100);

    private int point;

	private EDifficulty(final int point) {
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(final int point) {
		this.point = point;
	}
}
