package com.varunarl.androidbrail.brail;

import java.util.ArrayList;
import java.util.List;

public class Brail {

	public static class Key {
		int IDENTIFIER;
		Character ASCII_CHARACTER;
		BrailCharacter BRAIL_CHARACTER;

		@Override
		public boolean equals(Object o) {
			if (o instanceof Key)
				if (((Key) o).IDENTIFIER == this.IDENTIFIER)
					return true;
			return false;
		}

		@Override
		public String toString() {
			return "IDENTIFIER : " + IDENTIFIER + " ASCII : " + ASCII_CHARACTER
					+ " BRAIL" + BRAIL_CHARACTER.toString();
		}

		public static Key get(Character c) {
			Key k = new Key();
			k.BRAIL_CHARACTER = KeyBoard.get(c);
			k.ASCII_CHARACTER = c;
			k.IDENTIFIER = c;
			return k;
		}

		public static Key get(BrailCharacter c, int type) {
			Key k = new Key();
			k.BRAIL_CHARACTER = c;
			k.ASCII_CHARACTER = new KeyBoard().get(c, type);
			k.IDENTIFIER = k.ASCII_CHARACTER;
			return k;
		}

	}

	public static class KeyBoard {

		public static final int NUMERIC_KEY_TYPE = 1;
		public static final int UPPER_KEY_TYPE = 2;
		public static final int LOWER_KEY_TYPE = 3;
		public static final int SPECIAL_KEY_TYPE = 4;
		
		public static final int LETTER_TYPE = UPPER_KEY_TYPE + LOWER_KEY_TYPE;
		public static final int ITALIC_DECIMAL_TYPE = 20;
		public static final int NUMERAL_ACCENT_TYPE = 30;
		public static final int LITERAL_TYPE = 40;

		private Numeric mNumericKeyboard;
		private Upper mUpperCaseKeyboard;
		private Lower mLowerCaseKeyboard;
		private Special mSpecialKeyboard;
		private Control mControlKeyboard;

		public KeyBoard() {
			mNumericKeyboard = new Numeric();
			mUpperCaseKeyboard = new Upper();
			mLowerCaseKeyboard = new Lower();
			mSpecialKeyboard = new Special();
			mControlKeyboard = new Control();
		}

		public Character get(BrailCharacter c, int type) {
			Character result = '~';
			result = mControlKeyboard.get(c);
			if (result == '~')
				switch (type) {
				case NUMERIC_KEY_TYPE:
					result = mNumericKeyboard.get(c);
					result = result >= 48 && result <= 57 ? result : '~';
				case UPPER_KEY_TYPE:
					result = mUpperCaseKeyboard.get(c);
					result = result >= 65 && result <= 90 ? result : '~';
				case LOWER_KEY_TYPE:
					result = mLowerCaseKeyboard.get(c);
					result = result >= 97 && result <= 122 ? result : '~';
				case SPECIAL_KEY_TYPE:
				default:
					result = mSpecialKeyboard.get(c);
				}
			return result;
		}
		
		public boolean isControlCharacter(BrailCharacter c)
		{
			return mControlKeyboard.get(c) != '~' ? true:false;
		}

		public int getControlType(BrailCharacter c)
		{
			int _c = mControlKeyboard.get(c);
			switch (_c) {
			case '@':
				return LITERAL_TYPE;
			case '#':
				return NUMERIC_KEY_TYPE;
			case '&':
				return LETTER_TYPE;
			case '^':
				return UPPER_KEY_TYPE;
			case '$':
				return NUMERAL_ACCENT_TYPE;
			case '%':
				return ITALIC_DECIMAL_TYPE;

			default:
				return LETTER_TYPE;
			}
		}
		public static BrailCharacter get(Character c) {
			BrailCharacter bc = new BrailCharacter();
			if (Character.isDigit(c)) {
				switch (c) {
				case 1:
					bc._one_one = DOT.on();
					break;
				case 2:
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					break;
				case 3:
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 4:
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 5:
					bc._one_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 6:
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 7:
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 8:
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 9:
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 0:
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;

				default:
					break;
				}
			} else if (Character.isLetter(c)) {
				switch (c) {
				case 'A':
				case 'a':
					bc._one_one = DOT.on();
					break;
				case 'B':
				case 'b':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					break;
				case 'C':
				case 'c':
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'D':
				case 'd':
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'E':
				case 'e':
					bc._one_one = DOT.on();
					bc._one_two = DOT.off();
					bc._two_two = DOT.on();
					break;
				case 'F':
				case 'f':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'G':
				case 'g':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'H':
				case 'h':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'I':
				case 'i':
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'J':
				case 'j':
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'K':
				case 'k':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					break;
				case 'L':
				case 'l':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					break;
				case 'M':
				case 'm':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'N':
				case 'n':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'O':
				case 'o':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'P':
				case 'p':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'Q':
				case 'q':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'R':
				case 'r':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'S':
				case 's':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'T':
				case 't':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case 'U':
				case 'u':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'V':
				case 'v':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'W':
				case 'w':
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'X':
				case 'x':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Y':
				case 'y':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Z':
				case 'z':
					bc._one_one = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ç':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'É':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'À':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'È':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ù':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Â':
					bc._one_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ê':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Î':
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ô':
					bc._one_one = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Û':
					bc._one_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ë':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ï':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'Ü':
					bc._one_one = DOT.on();
					bc._one_two = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case 'ì':
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					break;
				case 'ò':
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;

				}
			} else {
				switch (c) {
				case '@':
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					break;
				case '#':
					bc._one_three = DOT.on();
					bc._two_one = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '$':
					bc._two_one = DOT.on();
					break;
				case '%':
					bc._two_one = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '^':
					bc._two_three = DOT.on();
					break;
				case '&':
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case ',':
					bc._one_two = DOT.on();
					break;
				case ';':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					break;
				case ':':
					bc._one_two = DOT.on();
					bc._two_two = DOT.on();
					break;
				case '.':
					bc._one_two = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case ' ':
					bc._one_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '!':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					break;
				case '(':
				case ')':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '?':
					bc._one_two = DOT.on();
					bc._one_three = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '*':
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					break;
				case '"':
					bc._one_three = DOT.on();
					bc._two_two = DOT.on();
					bc._two_three = DOT.on();
					break;
				case '\'':
					bc._one_three = DOT.on();
					break;
				case '-':
					bc._one_three = DOT.on();
					bc._two_three = DOT.on();
					break;
				default:
					break;
				}
			}
			return bc;
		}

		class Numeric {
			List<Key> mNumericKeyBoard;

			public Numeric() {
				mNumericKeyBoard = new ArrayList<Brail.Key>();
				init();
			}

			public void init() {
				mNumericKeyBoard.add(Key.get('1'));
				mNumericKeyBoard.add(Key.get('2'));
				mNumericKeyBoard.add(Key.get('3'));
				mNumericKeyBoard.add(Key.get('4'));
				mNumericKeyBoard.add(Key.get('5'));
				mNumericKeyBoard.add(Key.get('6'));
				mNumericKeyBoard.add(Key.get('7'));
				mNumericKeyBoard.add(Key.get('8'));
				mNumericKeyBoard.add(Key.get('9'));
				mNumericKeyBoard.add(Key.get('0'));
			}

			public Character get(BrailCharacter c) {
				String pattern = c.pattern();
				for (Key k : mNumericKeyBoard)
					if (k.BRAIL_CHARACTER.pattern() == pattern)
						return k.ASCII_CHARACTER;
				return 'E';
			}
		}

		class Upper {
			List<Key> mAlphabeticalKeyBoard;

			public Upper() {
				mAlphabeticalKeyBoard = new ArrayList<Brail.Key>();
				init();
			}

			public void init() {
				mAlphabeticalKeyBoard.add(Key.get('A'));
				mAlphabeticalKeyBoard.add(Key.get('B'));
				mAlphabeticalKeyBoard.add(Key.get('C'));
				mAlphabeticalKeyBoard.add(Key.get('D'));
				mAlphabeticalKeyBoard.add(Key.get('E'));
				mAlphabeticalKeyBoard.add(Key.get('F'));
				mAlphabeticalKeyBoard.add(Key.get('G'));
				mAlphabeticalKeyBoard.add(Key.get('H'));
				mAlphabeticalKeyBoard.add(Key.get('I'));
				mAlphabeticalKeyBoard.add(Key.get('J'));
				mAlphabeticalKeyBoard.add(Key.get('K'));
				mAlphabeticalKeyBoard.add(Key.get('L'));
				mAlphabeticalKeyBoard.add(Key.get('M'));
				mAlphabeticalKeyBoard.add(Key.get('N'));
				mAlphabeticalKeyBoard.add(Key.get('O'));
				mAlphabeticalKeyBoard.add(Key.get('P'));
				mAlphabeticalKeyBoard.add(Key.get('Q'));
				mAlphabeticalKeyBoard.add(Key.get('R'));
				mAlphabeticalKeyBoard.add(Key.get('S'));
				mAlphabeticalKeyBoard.add(Key.get('T'));
				mAlphabeticalKeyBoard.add(Key.get('U'));
				mAlphabeticalKeyBoard.add(Key.get('V'));
				mAlphabeticalKeyBoard.add(Key.get('W'));
				mAlphabeticalKeyBoard.add(Key.get('X'));
				mAlphabeticalKeyBoard.add(Key.get('Y'));
				mAlphabeticalKeyBoard.add(Key.get('Z'));
			}

			public Character get(BrailCharacter c) {
				String pattern = c.pattern();
				for (Key k : mAlphabeticalKeyBoard)
					if (k.BRAIL_CHARACTER.pattern() == pattern)
						return k.ASCII_CHARACTER;
				return 'e';
			}
		}

		class Lower {
			List<Key> mAlphabeticalKeyBoard;

			public Lower() {
				mAlphabeticalKeyBoard = new ArrayList<Brail.Key>();
				init();
			}

			public void init() {
				mAlphabeticalKeyBoard.add(Key.get('a'));
				mAlphabeticalKeyBoard.add(Key.get('b'));
				mAlphabeticalKeyBoard.add(Key.get('c'));
				mAlphabeticalKeyBoard.add(Key.get('d'));
				mAlphabeticalKeyBoard.add(Key.get('e'));
				mAlphabeticalKeyBoard.add(Key.get('f'));
				mAlphabeticalKeyBoard.add(Key.get('g'));
				mAlphabeticalKeyBoard.add(Key.get('h'));
				mAlphabeticalKeyBoard.add(Key.get('i'));
				mAlphabeticalKeyBoard.add(Key.get('j'));
				mAlphabeticalKeyBoard.add(Key.get('k'));
				mAlphabeticalKeyBoard.add(Key.get('l'));
				mAlphabeticalKeyBoard.add(Key.get('m'));
				mAlphabeticalKeyBoard.add(Key.get('n'));
				mAlphabeticalKeyBoard.add(Key.get('o'));
				mAlphabeticalKeyBoard.add(Key.get('p'));
				mAlphabeticalKeyBoard.add(Key.get('q'));
				mAlphabeticalKeyBoard.add(Key.get('r'));
				mAlphabeticalKeyBoard.add(Key.get('s'));
				mAlphabeticalKeyBoard.add(Key.get('t'));
				mAlphabeticalKeyBoard.add(Key.get('u'));
				mAlphabeticalKeyBoard.add(Key.get('v'));
				mAlphabeticalKeyBoard.add(Key.get('w'));
				mAlphabeticalKeyBoard.add(Key.get('x'));
				mAlphabeticalKeyBoard.add(Key.get('y'));
				mAlphabeticalKeyBoard.add(Key.get('z'));
			}

			public Character get(BrailCharacter c) {
				String pattern = c.pattern();
				for (Key k : mAlphabeticalKeyBoard)
					if (k.BRAIL_CHARACTER.pattern() == pattern)
						return k.ASCII_CHARACTER;
				return 'E';
			}
		}

		class Special {
			List<Key> mSpecialCharacters;

			public Special() {
				mSpecialCharacters = new ArrayList<Brail.Key>();
				init();
			}

			private void init() {
				mSpecialCharacters.add(Key.get(','));
				mSpecialCharacters.add(Key.get(';'));
				mSpecialCharacters.add(Key.get(':'));
				mSpecialCharacters.add(Key.get('.'));
				mSpecialCharacters.add(Key.get(' '));
				mSpecialCharacters.add(Key.get('!'));
				mSpecialCharacters.add(Key.get('('));
				mSpecialCharacters.add(Key.get(')'));
				mSpecialCharacters.add(Key.get('?'));
				mSpecialCharacters.add(Key.get('*'));
				mSpecialCharacters.add(Key.get('"'));
				mSpecialCharacters.add(Key.get(','));
				mSpecialCharacters.add(Key.get('-'));
				mSpecialCharacters.add(Key.get('Ç'));
				mSpecialCharacters.add(Key.get('É'));
				mSpecialCharacters.add(Key.get('À'));
				mSpecialCharacters.add(Key.get('È'));
				mSpecialCharacters.add(Key.get('Ù'));
				mSpecialCharacters.add(Key.get('Â'));
				mSpecialCharacters.add(Key.get('Ê'));
				mSpecialCharacters.add(Key.get('Î'));
				mSpecialCharacters.add(Key.get('Ô'));
				mSpecialCharacters.add(Key.get('Û'));
				mSpecialCharacters.add(Key.get('Ï'));
				mSpecialCharacters.add(Key.get('Ü'));
				mSpecialCharacters.add(Key.get('ì'));
				mSpecialCharacters.add(Key.get('ò'));

			}

			public Character get(BrailCharacter c) {
				String pattern = c.pattern();
				for (Key k : mSpecialCharacters)
					if (k.BRAIL_CHARACTER.pattern() == pattern)
						return k.ASCII_CHARACTER;
				return 'E';
			}

		}

		class Control {
			List<Key> mControlCharacters;

			public Control() {
				mControlCharacters = new ArrayList<Brail.Key>();
				init();
			}

			private void init() {
				//Numeral
				mControlCharacters.add(Key.get('#'));
				//Literals
				mControlCharacters.add(Key.get('@'));
				//Italics,decimal
				mControlCharacters.add(Key.get('%'));
				//Letters
				mControlCharacters.add(Key.get('&'));
				//Capital
				mControlCharacters.add(Key.get('^'));
				//Numerical index, accent
				mControlCharacters.add(Key.get('$'));
			}

			public Character get(BrailCharacter c) {
				String pattern = c.pattern();
				for (Key k : mControlCharacters)
					if (k.BRAIL_CHARACTER.pattern() == pattern)
						return k.ASCII_CHARACTER;
				return 'E';
			}
		}
	}

}
