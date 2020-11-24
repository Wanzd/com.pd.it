package com.pd.businessobject;

import java.util.HashMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MapKV extends HashMap<String, String> {
	public MapKV(String kvStr) {
		String[] splitKV = kvStr.split(",");
		for (String eachKV : splitKV) {
			String[] split = eachKV.split(":");
			this.put(split[0], split[split.length > 1 ? 1 : 0]);
		}
	}
}
