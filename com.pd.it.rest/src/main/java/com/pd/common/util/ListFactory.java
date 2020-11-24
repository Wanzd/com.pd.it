package com.pd.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pd.businessobject.MapKV;
import com.pd.businessobject.MapVO;

public class ListFactory {

    public static <IN> List<IN> asList(IN... vo) {
        return Stream.of(vo).collect(Collectors.toList());
    }

    public static List<MapVO> treeList(List<MapVO> list, String pid, String id, String sortId) {
        MapKV relKv = new MapKV();
        Map<String, MapVO> objMap = new HashMap<>();
        list.stream().forEach(mapVO -> {
            relKv.put(mapVO.str(id), mapVO.str(pid));
            objMap.put(mapVO.str(id), mapVO);
        });
        List<MapVO> rsList = new ArrayList<>();
        for (MapVO eachVO : list) {
            String tmpPid = eachVO.str("pid");
            MapVO pVo = objMap.get(tmpPid);
            if (pVo != null) {
                List<MapVO> children = pVo.list("children");
                children.add(eachVO);
            } else {
                rsList.add(eachVO);
            }
        }
        return rsList;
    }

    public static <T> T getFirst(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
