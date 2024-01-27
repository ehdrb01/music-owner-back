package kr.co.strato.wrp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
* HashMap 을 상속 받아서 만듬.
* 목적  : 프로젝트내에서  통일된 형태의 자료형을 사용하기 위해 ...
* 
* @author 	임대선
*/
public class DataMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	/**
	 * 지정된 key에 저장된 객체를 문자열로 변환하여 반환한다.
	 * 만약 key에 대해 저장된 값이 없거나 null이 저장된 경우 공백문자열("")을 반환한다.<p>
	 *
	 * @param key 얻을 값을 지정하기 위한 key
	 * @return 지정된 key에 저장된 객체를 문자열로 변환한 값 또는 지정된 key에 해당하는 HTTP 요청 파라미터 값. 만약 두 값이 null인 경우 공백문자열("")을 반환한다.
	 */
	public String getString(String key) {
		if ( key == null ) return "";
		Object value = get(key);
		if ( value == null ) return "";
		return value.toString();
	}

	/**
	 * 지정된 key에 저장된 객체를 int값으로 변환하여 반환한다.
	 * 만약 key에 대해 저장된 값이 없거나 null이 저장된 경우 0을 반환한다.<p>
	 *
	 * @param key 얻을 값을 지정하기 위한 key
	 * @return 지정된 key에 저장된 객체를 int로 변환한 값. 만약 값이 null인 0을 반환한다.
	 */
	public int getInt(String key) {
		if ( key == null ) return 0;
		Object value = get(key);
		if ( value == null ) return 0;
		if ( value instanceof java.lang.String ) {
			try {
				return Integer.parseInt((String)value);
			} catch(NumberFormatException e) {
				return 0;
			}
		} else if ( value instanceof java.lang.Number ) {
			return ((Number)value).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 지정된 key에 저장된 객체를 long값으로 변환하여 반환한다.
	 * 만약 key에 대해 저장된 값이 없거나 null이 저장된 경우 0을 반환한다.<p>
	 *
	 * @param key 얻을 값을 지정하기 위한 key
	 * @return 지정된 key에 저장된 객체를 long으로 변환한 값. 만약 값이 null인 0을 반환한다.
	 */
	public long getLong(String key) {
		if ( key == null ) return 0;
		Object value = get(key);
		if ( value == null ) return 0;
		if ( value instanceof java.lang.String ) {
			try {
				return Long.parseLong((String)value);
			} catch(NumberFormatException e) {
				return 0;
			}
		} else if ( value instanceof java.lang.Number ) {
			return ((Number)value).longValue();
		} else {
			return 0;
		}
	}

	/**
	 * 지정된 key에 저장된 객체를 double값으로 변환하여 반환한다.
	 * 만약 key에 대해 저장된 값이 없거나 null이 저장된 경우 0을 반환한다.<p>
	 *
	 * @param key 얻을 값을 지정하기 위한 key
	 * @return 지정된 key에 저장된 객체를 double값으로 변환한 값. 만약 값이 null인 0을 반환한다.
	 */
	public double getDouble(String key) {
		if ( key == null ) return 0;
		Object value = get(key);
		if ( value == null ) return 0;
		if ( value instanceof java.lang.String ) {
			try {
				return Double.parseDouble((String)value);
			} catch(NumberFormatException e) {
				return 0;
			}
		} else if ( value instanceof java.lang.Number ) {
			return ((Number)value).doubleValue();
		} else {
			return 0;
		}
	}

	public Date getDate(String key) {
		if ( key == null ) return null;
		Object value = get(key);
		if ( value == null ) return null;
		if ( value instanceof java.sql.Date ) {
			return (Date)value;
		} else {
			return null;
		}
	}
}