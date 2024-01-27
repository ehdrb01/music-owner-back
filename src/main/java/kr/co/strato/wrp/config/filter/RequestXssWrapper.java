package kr.co.strato.wrp.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class RequestXssWrapper extends HttpServletRequestWrapper {
	public RequestXssWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) return null;
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) return null;
		return cleanXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)	return null;
		if("Authorization".equals(name)) return value;
		return cleanXSS(value);
	}

	/*
	 * 가장 기본적인것만 치환
	 */
	private String cleanXSS(String value) {
		value = value.replaceAll("'", "&apos;");
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		
		return value;
	}
}
