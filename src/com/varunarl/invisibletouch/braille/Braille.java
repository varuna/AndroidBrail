package com.varunarl.invisibletouch.braille;

import java.util.ArrayList;
import java.util.List;

public class Braille {

    public static class Key {
        int IDENTIFIER;
        Character ASCII_CHARACTER;
        BrailleCharacter BRAILLE_CHARACTER;

        public static Key get(Character c) {
            Key k = new Key();
            k.BRAILLE_CHARACTER = KeyBoard.get(c);
            k.ASCII_CHARACTER = c;
            k.IDENTIFIER = c;
            return k;
        }

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
                    + " BRAIL" + BRAILLE_CHARACTER.toString();
        }

    }

    public static class KeyBoard {

        public static final int NUMERIC_KEY_TYPE = 1001;
        public static final int UPPER_KEY_TYPE = 1002;
        public static final int LOWER_KEY_TYPE = 1003;
        public static final int SPECIAL_KEY_TYPE = 1004;
        public static final int LETTER_TYPE = UPPER_KEY_TYPE + LOWER_KEY_TYPE;
        public static final int ITALIC_DECIMAL_TYPE = 1020;
        public static final int NUMERAL_ACCENT_TYPE = 1030;
        public static final int LITERAL_TYPE = 1040;

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

        public static BrailleCharacter get(Character c) {
            BrailleCharacter bc = new BrailleCharacter();
            if (Character.isDigit(c)) {
                switch (c) {
                    case '1':
                        bc._one_one = DOT.on();
                        break;
                    case '2':
                        bc._one_one = DOT.on();
                        bc._one_two = DOT.on();
                        break;
                    case '3':
                        bc._one_one = DOT.on();
                        bc._two_one = DOT.on();
                        break;
                    case '4':
                        bc._one_one = DOT.on();
                        bc._two_one = DOT.on();
                        bc._two_two = DOT.on();
                        break;
                    case '5':
                        bc._one_one = DOT.on();
                        bc._two_two = DOT.on();
                        break;
                    case '6':
                        bc._one_one = DOT.on();
                        bc._one_two = DOT.on();
                        bc._two_one = DOT.on();
                        break;
                    case '7':
                        bc._one_one = DOT.on();
                        bc._one_two = DOT.on();
                        bc._two_one = DOT.on();
                        bc._two_two = DOT.on();
                        break;
                    case '8':
                        bc._one_one = DOT.on();
                        bc._one_two = DOT.on();
                        bc._two_two = DOT.on();
                        break;
                    case '9':
                        bc._one_two = DOT.on();
                        bc._two_one = DOT.on();
                        break;
                    case '0':
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

        public Character get(BrailleCharacter c, int type) {
            Character result;
            result = mControlKeyboard.get(c);
            if (result.equals('~'))
                switch (type) {
                    case NUMERIC_KEY_TYPE:
                        result = mNumericKeyboard.get(c);
                        result = result >= 48 && result <= 57 ? result : '~';
                        break;
                    case UPPER_KEY_TYPE:
                        result = mUpperCaseKeyboard.get(c);
                        result = result >= 65 && result <= 90 ? result : '~';
                        break;
                    case LOWER_KEY_TYPE:
                        result = mLowerCaseKeyboard.get(c);
                        result = result >= 97 && result <= 122 ? result : '~';
                        break;
                    case SPECIAL_KEY_TYPE:
                    default:
                        result = mSpecialKeyboard.get(c);
                        break;
                }
            return result;
        }

        public boolean isControlCharacter(BrailleCharacter c) {
            return !mControlKeyboard.get(c).equals('~');
        }

        public boolean isErrorCharacter(BrailleCharacter c) {
            boolean stage1 = mControlKeyboard.get(c).equals('~');
            boolean stage2 = mLowerCaseKeyboard.get(c).equals('~');
            boolean stage3 = mNumericKeyboard.get(c).equals('~');
            boolean stage4 = mUpperCaseKeyboard.get(c).equals('~');
            boolean stage5 = mSpecialKeyboard.get(c).equals('~');
            return stage1 && stage2 && stage3 && stage4 && stage5;
        }

        public int getControlType(BrailleCharacter c) {
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

        public int getControlType(Character c) {
            switch (c) {
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

        class Numeric {
            List<Key> mNumericKeyBoard;

            public Numeric() {
                mNumericKeyBoard = new ArrayList<Braille.Key>();
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

            public Character get(BrailleCharacter c) {
                String pattern = c.pattern();
                for (Key k : mNumericKeyBoard)
                    if (k.BRAILLE_CHARACTER.pattern().equals(pattern))
                        return k.ASCII_CHARACTER;
                return 'E';
            }
        }

        class Upper {
            List<Key> mAlphabeticalKeyBoard;

            public Upper() {
                mAlphabeticalKeyBoard = new ArrayList<Braille.Key>();
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

            public Character get(BrailleCharacter c) {
                String pattern = c.pattern();
                for (Key k : mAlphabeticalKeyBoard)
                    if (k.BRAILLE_CHARACTER.pattern().equals(pattern))
                        return k.ASCII_CHARACTER;
                return 'e';
            }
        }

        class Lower {
            List<Key> mAlphabeticalKeyBoard;

            public Lower() {
                mAlphabeticalKeyBoard = new ArrayList<Braille.Key>();
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

            public Character get(BrailleCharacter c) {
                String pattern = c.pattern();
                for (Key k : mAlphabeticalKeyBoard)
                    if (k.BRAILLE_CHARACTER.pattern().equals(pattern))
                        return k.ASCII_CHARACTER;
                return 'E';
            }
        }

        class Special {
            List<Key> mSpecialCharacters;

            public Special() {
                mSpecialCharacters = new ArrayList<Braille.Key>();
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
            }

            public Character get(BrailleCharacter c) {
                String pattern = c.pattern();
                for (Key k : mSpecialCharacters)
                    if (k.BRAILLE_CHARACTER.pattern().equals(pattern))
                        return k.ASCII_CHARACTER;
                return '~';
            }

        }

        class Control {
            List<Key> mControlCharacters;

            public Control() {
                mControlCharacters = new ArrayList<Braille.Key>();
                init();
            }

            private void init() {
                // Numeral
                mControlCharacters.add(Key.get('#'));
                // Literals
                mControlCharacters.add(Key.get('@'));
                // Italics,decimal
                mControlCharacters.add(Key.get('%'));
                // Letters
                mControlCharacters.add(Key.get('&'));
                // Capital
                mControlCharacters.add(Key.get('^'));
                // Numerical index, accent
                mControlCharacters.add(Key.get('$'));
            }

            public Character get(BrailleCharacter c) {
                String pattern = c.pattern();
                for (Key k : mControlCharacters)
                    if (k.BRAILLE_CHARACTER.pattern().equals(pattern))
                        return k.ASCII_CHARACTER;
                return '~';
            }
        }
    }

}
