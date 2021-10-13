package com.pd.springboot.adapter;

import java.util.List;

import javax.inject.Named;

import com.pd.common.util.WebUtil;
import com.pd.it.common.exception.BusinessException;
import com.pd.model.joke.vo.JokeVO;
import com.pd.model.joke.vo.JokeVOFactory;

@Named
public class QiushibaikeAdapter {
    private static final String RANDOM_URL = "https://www.qiushibaike.com/history/";

    public List<JokeVO> getRandomList() throws BusinessException {
        String htmlStr = WebUtil.get(RANDOM_URL, "GB2312");
        System.out.println(htmlStr);
        return JokeVOFactory.getListByHtmlStr(htmlStr);
    }
}
