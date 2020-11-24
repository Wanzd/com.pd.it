package com.pd.builderobject;

import java.util.ArrayList;
import java.util.List;

import com.pd.businessobject.MapVO;
import com.pd.common.util.IntFactory;
import com.pd.standard.itf.IBuilder;

public class SariProvinceParseBuilder implements IBuilder<MapVO, List<MapVO>> {

	@Override
	public List<MapVO> build(MapVO in) {
		List<MapVO> rsList = new ArrayList<>();
		String inStr = in.str("value");
		String[] strArr = inStr.split(";");
		Object provinceName = "";
		for (int i = 0, total = strArr.length; i < total; i++) {
			String eachStr = strArr[i];
			if (eachStr.contains(" 确诊 ")) {
				provinceName = parseAttr("provinceName", eachStr);
				String[] attrNames = new String[] { "cnt", "heal", "death", "doubt" };
				for (String eachAttrName : attrNames) {
					MapVO vo = new MapVO();
					vo.put("province", provinceName);
					vo.put("qtyType", eachAttrName);
					vo.put("qty", parseAttr(eachAttrName, eachStr));
					vo.put("creationDate", in.date("creationDate"));
					rsList.add(vo);
				}
			}
		}
		return rsList;
	}

	private Object parseAttr(String attrName, String eachStr) {
		Object attrValue = null;
		int start = -1;
		int end = -1;
		switch (attrName) {
		case "provinceName":
			attrValue = eachStr.substring(0, eachStr.indexOf(" "));
			break;
		case "doubt":
			start = eachStr.indexOf("疑似");
			if (start < 0) {
				return 0;
			}
			eachStr = eachStr.substring(start + 2);
			end = eachStr.indexOf("例");
			attrValue = IntFactory.str(eachStr.substring(0, end));
			break;
		case "death":
			start = eachStr.indexOf("死亡");
			if (start < 0) {
				return 0;
			}
			eachStr = eachStr.substring(start + 2);
			end = eachStr.indexOf("例");
			attrValue = IntFactory.str(eachStr.substring(0, end));
			break;
		case "heal":
			start = eachStr.indexOf("治愈");
			if (start < 0) {
				return 0;
			}
			eachStr = eachStr.substring(start + 2);
			end = eachStr.indexOf("例");
			attrValue = IntFactory.str(eachStr.substring(0, end));
			break;
		case "cnt":
			start = eachStr.indexOf("确诊");
			if (start < 0) {
				return 0;
			}
			eachStr = eachStr.substring(start + 2);
			end = eachStr.indexOf("例");
			if (end < 0) {
				attrValue = null;
			}
			try {
				attrValue = IntFactory.str(eachStr.substring(0, end));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		return attrValue;
	}

}
