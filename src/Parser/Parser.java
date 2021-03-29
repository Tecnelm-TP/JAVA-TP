package Parser;

import java.util.ArrayList;

public class Parser {

	private int index;

	public Parser(String file) {
	}

	private String getName(String str) {
		StringBuilder sb = new StringBuilder();
		index++;
		while (str.charAt(index) != '"' && index < str.length()) {
			sb.append(str.charAt(index));
			index++;
		}
		return sb.toString();
	}

	private void goNextChar(String c, String str) {
		char[] stopchar = c.toCharArray();
		Boolean stop = false;
		while (!stop && index + 1 < str.length()) {
			index++;
			for (char t : stopchar) {
				stop = stop || (!stop && (str.charAt(index) == t)) ? true : false;
			}

		}
		if (!stop) {
			index++;
		}

	}

	public  String getByID(String key, String str) throws NotPresentException {
		String ret = null;
		StringBuilder sb = new StringBuilder();

		for (index = 0; index < str.length() && ret == null; index++) {
			switch (str.charAt(index)) {
			case '\"':
				if (getName(str).equals(key)) {
					goNextChar(":", str);
					index++;
					while (!(str.charAt(index) == '}' || str.charAt(index) == ',') && index < str.length()) {
						sb.append(str.charAt(index));
						index++;
					}
					ret = (sb.toString());
				}
				break;
			case ':':
				goNextElem(str);
				index--;
				break;
			}

		}
		if (ret == null)
			throw new NotPresentException();
		return ret;
	}

	public ArrayList<String> getArray(String key, String str) throws InvalidTypeException, NotPresentException {
		ArrayList<String> ret = null;

		String categorie;
		index = 0;

		for (index = 0; index < str.length() && ret == null; index++) {

			switch (str.charAt(index)) {
			case '\"':

				categorie = getName(str);
				if (categorie.equals(key)) {
					goNextChar(":", str);
					ret = parseArray(str);
				} else {
					goNextElem(str);
					index--;
				}

				break;
			case ':':
				goNextElem(str);
				index--;
				break;
			}

		}

		if (ret == null)
			throw new NotPresentException();
		return ret;

	}

	private ArrayList<String> parseArray(String str) throws InvalidTypeException {
		ArrayList<Character> braquet = new ArrayList<>();
		ArrayList<Character> braquet1 = new ArrayList<>();

		ArrayList<String> ret = new ArrayList<>();
		StringBuilder tmp = new StringBuilder();

		final char c = '[';
		Boolean stop = false;
		while (!stop && index + 1 < str.length()) {
			index++;
			switch (str.charAt(index)) {
			case c:
				stop = true;
				break;
			case ' ':
			case '\t':
			case '\n':
			case '\b':
			case '\r':
				break;
			default:
				throw new InvalidTypeException("Not an array");
			}

		}

		Character b2 = '[';
		Character b = '{';
		char tmpchar;
		do {
			tmpchar = str.charAt(index);
			switch ((tmpchar)) {
			case ']':
				braquet.remove(b2);

				break;
			case '[':
				braquet.add(b2);
				break;
			case '}':
				braquet1.remove(b);
				tmp.append(tmpchar);

				if (braquet1.size() == 0) {
					ret.add(tmp.toString().replace("\t", "").replace("\n", ""));
					tmp.setLength(0);
				}
				break;
			case '{':
				tmp.append(tmpchar);

				braquet1.add(b);
				break;
			case ',':
				if (braquet1.size() != 0)
					tmp.append(tmpchar);
				break;
			default:
				tmp.append(tmpchar);

				break;
			}
			stop = braquet.size() == 0 && index < str.length();

			if (!stop && index + 1 < str.length())
				index++;

		} while (!stop && index + 1 < str.length());

		return ret;

	}

	private void goNextElem(String str) {

		ArrayList<Character> braquet = new ArrayList<>();
		Boolean stop = false;
		Boolean canBeElem = false;
		Character b2 = '[';
		Character b = '{';
		do {
			switch (str.charAt(index)) {
			case ']':
				braquet.remove(b2);
				break;
			case '[':
				canBeElem = true;
				braquet.add(b2);
				break;
			case '{':
				canBeElem = true;
				braquet.add(b);
				break;
			case '}':
				if (!braquet.remove(b))
					stop = true;
				break;
			case '\"':
				stop = braquet.size() == 0 && canBeElem ? true : false;
				break;
			case ',':
				if (braquet.size() == 0) {
					goNextChar("\"", str);
					stop = true;
				}
				break;
			default:
				break;
			}
			if (!stop)
				index++;

		} while (!stop && index + 1 < str.length());

	}

}
