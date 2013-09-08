package com.varunarl.invisibletouch.braille;

public class DOT {
	boolean _on;
	int _value;

	private long[] vpattern;

	public static DOT on() {
		DOT n = new DOT();
		n._on = true;
		n._value = 1;
		return n;
	}

	public static DOT off() {
		DOT n = new DOT();
		n._on = false;
		n._value = 0;
		return n;
	}

	public static DOT on(DOT n) {
		n._on = true;
		n._value = 1;
		return n;
	}

	public static DOT off(DOT n) {
		n._on = false;
		n._value = 0;
		return n;
	}

	public boolean isOn() {
		return _on;
	}

	public void swap() {
		if (_on) {
			_on = false;
			_value = 0;
		} else {
			_on = true;
			_value = 1;
		}
	}

	public void setPattern(long[] pattern) {
		this.vpattern = pattern;
	}

	public long[] getPattern() {
		return vpattern;
	}

	@Override
	public String toString() {
		return "" + _value;
	}

}
