package com.nouser.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
//import org.wltea.analyzer.core.IKSegmenter;
//import org.wltea.analyzer.core.Lexeme;

public class StringTool {
	public static String join(String[] array, String separator, boolean removeEmpty) {
		StringBuilder sb = new StringBuilder();
		String[] arrayOfString = array;
		int j = array.length;
		for (int i = 0; i < j; i++) {
			String s = arrayOfString[i];
			if (s != null) {
				if (removeEmpty) {
					s = s.trim();
					if (s.equals("")) {
					}
				} else {
					sb.append(s).append(separator);
				}
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String join(List<?> list, String separator, boolean removeEmpty) {
		StringBuilder sb = new StringBuilder();
		for (Object s : list) {
			if (s != null) {
				if (removeEmpty) {
					s = s.toString().trim();
					if (s.equals("")) {
					}
				} else {
					sb.append(s).append(separator);
				}
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String decode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

//	public static List<String> splitLexeme(String text) {
//		List<String> ret = new ArrayList();
//		try {
//			StringReader reader = new StringReader(text);
//			IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
//			Lexeme lexeme = null;
//			while ((lexeme = ikSegmenter.next()) != null) {
//				ret.add(lexeme.getLexemeText());
//			}
//			reader.close();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		return ret;
//	}

//	public static String buildSearchRegexpSplitLexeme(String query) {
//		List<String> querys = splitLexeme(query);
//		return buildSearchRegexp((String[]) querys.toArray(new String[0]));
//	}

	public static String buildSearchRegexp(String... querys) {
		if ((querys == null) || (querys.length == 0)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int n = 0;
		String[] arrayOfString = querys;
		int j = querys.length;
		for (int i = 0; i < j; i++) {
			String s = arrayOfString[i];
			if (s != null) {
				s = s.trim();
				if (!s.isEmpty()) {
					n++;
					sb.append(s).append("|");
				}
			}
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		String keyword = sb.toString();
		if (keyword.isEmpty()) {
			return null;
		}
		keyword = keyword.replaceAll("\\(", "\\\\\\\\(");
		keyword = keyword.replaceAll("\\)", "\\\\\\\\)");

		StringBuilder regexp = new StringBuilder();
		regexp.append("^.*");
		for (int i = 0; i < n; i++) {
			regexp.append("(").append(keyword).append(")+");
			regexp.append(".*");
		}
		regexp.append("$");

		return regexp.toString();
	}

	public static boolean isEmpty(String s) {
		if ((s == null) || ("".equals(s)) || ("null".equals(s))) {
			return true;
		}
		return s.trim().isEmpty();
	}

	public static boolean isEmpty(String[] ss) {
		if (ss == null) {
			return true;
		}
		return ss.length == 0;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null) {
			return true;
		}
		return map.isEmpty();
	}

	public static boolean isEmpty(List<?> list) {
		if (list == null) {
			return true;
		}
		return list.isEmpty();
	}
}
