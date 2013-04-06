package com.varunarl.invisibletouch.brail;

public class BrailCharacter {
	public DOT _one_one;
	public DOT _one_two;
	public DOT _one_three;
	public DOT _two_one;
	public DOT _two_two;
	public DOT _two_three;

	public BrailCharacter() {
		_one_one = DOT.off();
		_one_two = DOT.off();
		_one_three = DOT.off();
		_two_one = DOT.off();
		_two_two = DOT.off();
		_two_three = DOT.off();
		setupVirationPatterns();
	}

	@Override
	public String toString() {
		return "" + _one_one.toString() + " : " + _two_one.toString() + "\n"
				+ _one_two.toString() + " : " + _two_two.toString() + "\n"
				+ _one_three.toString() + " : " + _two_three.toString();
	}

	public String pattern() {
		String pattern = "";
		pattern += _one_one.isOn() ? "1" : "";
		pattern += _one_two.isOn() ? "2" : "";
		pattern += _one_three.isOn() ? "3" : "";
		pattern += _two_one.isOn() ? "4" : "";
		pattern += _two_two.isOn() ? "5" : "";
		pattern += _two_three.isOn() ? "6" : "";
		return pattern;
	}

	public void reset() {
		_one_one = DOT.off();
		_one_two = DOT.off();
		_one_three = DOT.off();
		_two_one = DOT.off();
		_two_two = DOT.off();
		_two_three = DOT.off();
	}

	private void setupVirationPatterns() {
		long[] one_one = { 0, 100, 100, 100 };
		long[] one_two = { 0, 100, 100, 300 };
		long[] one_three = { 0, 100, 100, 500 };
		long[] two_one = { 0, 300, 100, 100 };
		long[] two_two = { 0, 300, 100, 300 };
		long[] two_three = { 0, 300, 100, 500 };
		_one_one.setPattern(one_one);
		_one_two.setPattern(one_two);
		_one_three.setPattern(one_three);

		_two_one.setPattern(two_one);
		_two_two.setPattern(two_two);
		_two_three.setPattern(two_three);
	}
}
