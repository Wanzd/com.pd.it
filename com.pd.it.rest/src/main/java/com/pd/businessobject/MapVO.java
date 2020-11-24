package com.pd.businessobject;

import static com.pd.common.util.StaticTool.clobToStr;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public class MapVO extends HashMap<String, Object> {
	public MapVO() {

	}

	public MapVO(Map<String, ?> map) {
		super();
		if (map == null) {
			return;
		}
		for (String eachKey : map.keySet()) {
			put(eachKey, map.get(eachKey));
		}
	}

	public MapVO(Attr... attrs) {
		super();
		p(attrs);
	}

	public MapVO(String jsonStr) {
		super();
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		for (String eachKey : jsonObject.keySet()) {
			put(eachKey, jsonObject.get(eachKey));
		}
	}
	public MapVO(Object obj) {
		JSONObject jsonObject = (JSONObject)JSON.toJSON(obj);
		for (String eachKey : jsonObject.keySet()) {
			put(eachKey, jsonObject.get(eachKey));
		}
	}

	public static List<MapVO> list$str(String jsonStr) {
		List<MapVO> rsList = new ArrayList<MapVO>();
		JSONArray parseArray = JSON.parseArray(jsonStr);
		for (int i = 0; i < parseArray.size(); i++) {
			rsList.add(new MapVO(parseArray.get(i).toString()));
		}
		return rsList;
	}

	public Object obj(String key) {
		Object obj = get(key);
		return obj;
	}

	public <Out> Out obj(String key, Class<Out> outClass) {
		Object object = get(key);
		if (object == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(object);
		return JSON.parseObject(jsonString, outClass);
	}

	public MapVO vo(String key) {
		Object object = get(key);
		if (object instanceof Map) {
			return new MapVO((Map) object);
		}
		return (MapVO) object;
	}

	public List<MapVO> list(String key) {
		Object object = get(key);
		if (object == null) {
			List<MapVO> rsList = new ArrayList<MapVO>();
			put(key, rsList);
			return rsList;
		}
		if (object instanceof List) {
			return (List<MapVO>) object;
		}

		return (List<MapVO>) object;
	}

	public Object v(String key) {
		Object obj = get(key);
		if (obj == null) {
			return "";
		}
		return obj;
	}

	public String str(String key) {
		Object obj = get(key);
		if (obj == null) {
			return "";
		}
		if (obj instanceof Clob) {
			return clobToStr((Clob) obj);
		}
		return obj.toString();
	}

	public MapVO p(Attr... attrs) {
		for (Attr attr : attrs) {
			put(attr.getKey(), attr.getValue());
		}
		return this;
	}

	public MapVO nvl(Attr... attrs) {
		for (Attr attr : attrs) {
			if (get(attr.getKey()) == null) {
				p(attr);
			}
		}
		return this;
	}

	public double num(String key) {
		Object v = get(key);
		if (v != null) {
			try {
				return Double.valueOf(v.toString());
			} catch (Exception e) {

			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public void init() {
		put("language", "CHN");
	}

	public MapVO p(String key, Object value) {
		put(key, value);
		return this;
	}

	public Date date(String key) {
		Object v = get(key);
		if (v != null) {
			try {
				return (Date) v;
			} catch (Exception e) {

			}
		}
		return null;
	}

	public MapVO map(String key) {
		Object v = get(key);
		if (v != null) {
			try {
				return (MapVO) v;
			} catch (Exception e) {

			}
		}
		return null;
	}

	public <T> T getValue(String path, Class<T> outClass) {
		String[] pathArray = path.split("\\.");
		HashMap tmpMap = this;
		for (int idx = 0, total = pathArray.length; idx < total; idx++) {
			String curPath = pathArray[idx];
			Object tmpObj = tmpMap.get(curPath);
			if (tmpObj == null) {
				return null;
			}
			if (tmpObj instanceof HashMap) {
				tmpMap = (HashMap) (tmpObj);
				System.out.println(tmpMap);
			}
			if (idx == total - 1) {
				return (T) tmpObj;
			}
		}
		return null;
	}
}
