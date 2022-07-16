package com.pd.springboot.service;

import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.notEmpty;
import static com.pd.it.common.util.StaticTool.toObj;
import static com.pd.it.common.util.StringTool.BLANK;
import static java.util.stream.Collectors.toList;

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

@Named
public class MenuService extends BaseService<SysMenuFO, SysMenuVO, ISysMenuDao> {
	private static Map<String, List<SysMenuVO>> menuEnumMap = null;

	@Override
	public List<SysMenuVO> queryList(SysMenuFO fo) throws BusinessException {
		if (isEmpty(menuEnumMap)) {
			menuEnumMap = initMenuEnumMap();
		}
		if (notEmpty(menuEnumMap)) {
			List<SysMenuVO> enumList = menuEnumMap.get(fo.getPid());
			if (notEmpty(enumList)) {
				return enumList;
			}
		}
		return null;
	}

	private Map<String, List<SysMenuVO>> initMenuEnumMap() {
		List<SysMenuVO> resultList = new ArrayList<>();
		List<SysMenuVO> enumList = Arrays.stream(MenuEnum.values()).map(vo -> toObj(vo, SysMenuVO.class))
				.collect(toList());
		resultList.addAll(enumList);
		try {
			List<SysMenuVO> dbList = dao.queryList(null);
			if (notEmpty(dbList)) {
				resultList.addAll(dbList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultList.stream().forEach(vo -> {
			if (isEmpty(vo.getPid())) {
				vo.setPid(BLANK);
			}
		});
		Map<String, List<SysMenuVO>> resultMap = resultList.stream().distinct()
				.collect(Collectors.groupingBy(vo -> vo.getPid()));
		return resultMap;
	}
}
