package com.pd.springboot.service;

import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.it.common.util.StaticTool.toObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.pd.businessobject.SysMenuFO;
import com.pd.businessobject.SysMenuVO;
import com.pd.common.enums.MenuEnum;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ISysMenuDao;

import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
public class MenuService extends BaseService<SysMenuFO, SysMenuVO, ISysMenuDao> {
	private static Map<String, List<SysMenuVO>> menuEnumMap = initMenuEnumMap();

	@Override
	public List<SysMenuVO> queryList(SysMenuFO fo) throws BusinessException {
		List<SysMenuVO> resultList = new ArrayList<>();
		List<SysMenuVO> enumList = menuEnumMap.get(fo.getPid());
		if(notEmpty(enumList)){
			return enumList;
		}
		try {
			List<SysMenuVO> superResultList = dao.queryList(fo);
			resultList.addAll(superResultList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resultList;
	}

	private static Map<String, List<SysMenuVO>> initMenuEnumMap() {
		Map<String, List<SysMenuVO>> resultMap = Arrays.stream(MenuEnum.values()).map(vo -> {
			SysMenuVO tmpVO=new SysMenuVO();
			tmpVO.setId(vo.getId());
			tmpVO.setPid(vo.getPid());
			tmpVO.setName(vo.getName());
			tmpVO.setUrl(vo.getUrl());
			return tmpVO;
		})
				.collect(Collectors.groupingBy(vo -> vo.getPid()));
		return resultMap;
	}
}
