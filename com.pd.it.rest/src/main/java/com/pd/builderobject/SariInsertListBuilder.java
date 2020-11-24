package com.pd.builderobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.MapVO;
import com.pd.standard.itf.IBuilder;

public class SariInsertListBuilder implements IBuilder<List<MapVO>, List<MapVO>> {

	@Override
	public List<MapVO> build(List<MapVO> in) throws BusinessException {
		List<MapVO> rsList = new ArrayList<>();
		for (MapVO eachVO : in) {
			rsList.addAll(buildNationList(eachVO));
			rsList.addAll(buildProvinceList(eachVO));
			rsList.addAll(buildCityList(eachVO));
		}
		rsList = rsList.stream().filter(vo -> vo.num("qty") > 0).collect(Collectors.toList());
		return rsList;
	}

	private Collection<? extends MapVO> buildCityList(MapVO fo) throws BusinessException {
		IBuilder<MapVO, List<MapVO>> parseBean = null;
		switch (fo.str("parseBean")) {
		case "html":
			parseBean = new SariCityHtmlParseBuilder();
			break;
		default:
			parseBean = new SariCityParseBuilder();
		}
		return parseBean.build(fo);
	}

	private Collection<? extends MapVO> buildProvinceList(MapVO fo) throws BusinessException {
		IBuilder<MapVO, List<MapVO>> parseBean = null;
		switch (fo.str("parseBean")) {
		case "html":
			parseBean = new SariProvinceHtmlParseBuilder();
			break;
		default:
			parseBean = new SariProvinceParseBuilder();
		}
		return parseBean.build(fo);
	}

	private Collection<? extends MapVO> buildNationList(MapVO fo) throws BusinessException {
		IBuilder<MapVO, List<MapVO>> parseBean = null;
		switch (fo.str("parseBean")) {
		case "html":
			parseBean = new SariNationHtmlParseBuilder();
			break;
		default:
			parseBean = new SariNationParseBuilder();
		}
		return parseBean.build(fo);
	}

}
