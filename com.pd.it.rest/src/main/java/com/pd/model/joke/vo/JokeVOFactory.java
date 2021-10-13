package com.pd.model.joke.vo;

import static com.pd.it.common.util.StaticTool.toInt;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pd.it.common.exception.BusinessException;

public class JokeVOFactory {

    public static List<JokeVO> getListByHtmlStr(String htmlStr) throws BusinessException {
        Document doc = Jsoup.parse(htmlStr);
        Elements resultList = doc.getElementsByTag("article");

        List<JokeVO> rsList = new ArrayList<>();
        for (int index = 0, total = resultList.size(); index < total; index++) {
            try {
                Element tmp = resultList.get(index);
                JokeVO jokeVO = new JokeVO();
                jokeVO.setSource("qiushibaike");
                jokeVO.setId(tmp.attr("id"));
                jokeVO.setText(tmp.getElementsByClass("content-text").get(0).getElementsByTag("span").get(0).html());
                jokeVO.setUrl(
                        tmp.getElementsByClass("content-text").get(0).getElementsByTag("a").get(0).attr("href"));

                String laughCommentStr = tmp.getElementsByClass("laugh-comment").get(0).html();
                jokeVO.setColorValue(toInt(laughCommentStr.substring(0, laughCommentStr.indexOf(" "))));

                String commentsStr = tmp.getElementsByClass("comments").get(0).html();
                jokeVO.setDiscussValue(toInt(commentsStr.substring(0, commentsStr.indexOf(" "))));
                rsList.add(jokeVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rsList;
    }
}