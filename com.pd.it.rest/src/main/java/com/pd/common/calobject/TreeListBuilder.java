package com.pd.common.calobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.businessobject.MapVO;
import com.pd.standard.itf.IBuilder;

public class TreeListBuilder implements IBuilder<List<MapVO>, List<MapVO>> {

    private Calculator cal = new Calculator();

    /**
     * 对入参中的list按照pid和id的父子关系进行结构化处理
     */
    @Override
    public List<MapVO> build(List<MapVO> list) {
        String pidStr = "pid";
        String idStr = "id";

        List<MapVO> rsList = new ArrayList<MapVO>();
        Map<String, MapVO> idMap = new HashMap<>();
        list.stream().forEach(vo -> idMap.put(vo.str(pidStr) + "|" + vo.str(idStr), vo));
        System.out.println(list);
        for (String eachKey : idMap.keySet()) {
            MapVO eachVO = idMap.get(eachKey);
            String curPid = eachVO.str(pidStr);
            if (curPid.length() > 0) {
                list.stream().filter(vo -> vo.str(idStr).equals(curPid)).forEach(vo -> {
                    List<MapVO> childrenList = vo.list("children");
                    childrenList.add(eachVO);
                });
            }
        }
        for (MapVO eachVO : list) {
            String curPid = eachVO.str(pidStr);
            if (curPid.length() == 0 && eachVO.get("children") != null) {
                rsList.add(eachVO);
            }
        }
        for (MapVO eachVO : rsList) {
            init(eachVO);
        }
        return rsList;
    }

    public void init(MapVO vo) {

        // cal.initChildrenCnt(vo);
        // cal.initFulChildrenCnt(vo);
        // cal.initLeafChildrenCnt(vo);
        // cal.initValidLeafChildrenCnt(vo);
        cal.initSortChildren(vo);
    }

    public static class Calculator {
        public void initSortChildren(MapVO vo) {
            if (vo.get("children") == null) {
                vo.put("childrenCnt", 0);
                return;
            }
            List<MapVO> childrenList = vo.list("children");
            Collections.sort(childrenList, new Comparator<MapVO>() {
                @Override
                public int compare(MapVO o1, MapVO o2) {
                    double sick1 = o1.num("sick");
                    double sick2 = o2.num("sick");
                    if (sick1 != sick2) {
                        return sick1 < sick2 ? 1 : -1;
                    }

                    double sortId1 = o1.num("sortId");
                    double sortId2 = o2.num("sortId");
                    if (sortId1 == sortId2) {
                        return 0;
                    }
                    return sortId1 > sortId2 ? 1 : -1;
                }
            });
            for (MapVO eachChild : childrenList) {
                initSortChildren(eachChild);
            }
        }

    }
}
