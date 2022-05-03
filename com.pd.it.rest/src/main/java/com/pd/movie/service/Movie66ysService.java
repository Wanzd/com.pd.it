package com.pd.movie.service;

import com.pd.common.util.WebUtil;
import com.pd.it.common.itf.BaseService;
import com.pd.model.movie.vo.MovieFO;
import com.pd.model.movie.vo.MovieVO;
import com.pd.springboot.dao.IMovieDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.isEmpty;

@Named
public class Movie66ysService extends BaseService<MovieFO, MovieVO, IMovieDao> {
    private static final String PRE_URL = "https://www.66yingshi.com/";

    Calculator cal = new Calculator();

    public void process() {
        MovieCfgEnum[] movieCfgEnums = MovieCfgEnum.values();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (MovieCfgEnum eachVO : movieCfgEnums) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    processByConfig(eachVO);
                }
            });
        }
    }

    private void processByConfig(MovieCfgEnum eachVO) {
        boolean continueFlag = true;
        int index = 1;
        while (continueFlag) {
            String url = cal.calUrl(eachVO, index);
            String htmlStr = WebUtil.get(url, "GB2312");
            List<MovieVO> list = cal.calMovieList(eachVO, htmlStr);
            if (isEmpty(list)) {
                continueFlag = false;
                break;
            }
            dao.insertList(list);
            index++;
        }
    }

    private class Calculator {

        public String calUrl(MovieCfgEnum eachVO, int index) {
            String indexUrl = index <= 1 ? "" : formatStr("/index_%d.html", index);
            return formatStr("%s%s%s", PRE_URL, eachVO.getUrl(), indexUrl);
        }

        public List<MovieVO> calMovieList(MovieCfgEnum eachVO, String htmlStr) {
            List<MovieVO> rsList = new ArrayList<>();
            Document doc = Jsoup.parse(htmlStr);
            try {
                Element channelListDiv = doc.getElementsByClass("channellist").first();
                Elements liList = channelListDiv.getElementsByTag("ul").first().getElementsByTag("li");
                liList.forEach(vo -> {
                    MovieVO movieVO = new MovieVO();
                    Element listInfoDoc = vo.getElementsByClass("listInfo").first();
                    Element aDoc = listInfoDoc.getElementsByTag("h3").first().getElementsByTag("a").first();
                    Element pDoc = listInfoDoc.getElementsByTag("p").last();
                    movieVO.setUrl(aDoc.attr("href"));
                    movieVO.setType(eachVO.getCnName());
                    movieVO.setName(aDoc.text());
                    movieVO.setSource("66ys");
                    movieVO.setDetail(pDoc.text());
                    rsList.add(movieVO);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rsList;
        }
    }

    @Getter
    @AllArgsConstructor
    private enum MovieCfgEnum {
        XJP("xjp", "喜剧片", "/xijupian"),
        DZP("dzp", "动作片", "/dongzuopian"),
        AQP("aqp", "爱情片", "/aiqingpian"),
        KHP("khp", "科幻片", "/kehuanpian"),
        KBP("kbp", "恐怖片", "/kongbupian"),
        ZZP("zzp", "战争片", "/zhanzhengpian"),
        JLP("jlp", "纪录片", "/jilupian"),
        JQP("jqp", "剧情片", "/bd"),
        TDDY("3ddy", "3D电影", "/3ddy"),
        DSJ("dsj", "国产剧", "/dsj"),
        GTJ("gtj", "港台剧", "/dsj2"),
        RHJ("rhj", "日韩剧", "/dsj3"),
        OMJ("omj", "欧美剧", "/dsj4"),
        GPDY("gpdy", "国配电影", "/gy"),
        ZY("zy", "综艺", "/zy");
        private String name;
        private String cnName;
        private String url;
    }
}
