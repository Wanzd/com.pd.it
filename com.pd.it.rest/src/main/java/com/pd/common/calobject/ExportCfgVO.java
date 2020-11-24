package com.pd.common.calobject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pd.businessobject.MapVO;
import com.pd.standard.itf.IExportConfigEnum;

import lombok.Data;

@Data
public class ExportCfgVO<T extends IExportConfigEnum> {
	private String titleName;
	private String sheetName;
	private int batchSize;
	private List<T> cfgList;

	public List<String> getLabels() {
		return cfgList.stream().map(vo -> vo.getLabel()).collect(Collectors.toList());
	}

	public List<String[]> calData(List list) {
		List<String[]> rsList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] array = new String[cfgList.size()];
			MapVO mapVO = new MapVO(list.get(i));
			for (int j = 0; j < cfgList.size(); j++) {
				array[j] = mapVO.str(cfgList.get(j).getField());
			}
			rsList.add(array);
		}

		return rsList;
	}
}
