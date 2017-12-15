package com.wen_wen.firstsee.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenEntity;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenListDetail;
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;
import com.wen_wen.firstsee.mvp.model.e.bean.SentenceDetail;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 句子解析工具类
 */
public class DocParseUtil {

    /**
     * 解析名人名句
     *
     * @param result
     * @return
     */
    public static List<LinkEntity> parseLink(String result) {

        System.out.println(result);
        Document doc = Jsoup.parse(result);

        Elements rowElements = doc.getElementsByClass("views-row");

        List<LinkEntity> linkEntityList = new ArrayList<>();

        if (rowElements != null) {

            for (int i = 0; i < rowElements.size(); i++) {

                LinkEntity linkEntity = new LinkEntity();
                Element rowElement = rowElements.get(i);
                // 图片
                Elements views_field_tids = rowElement.getElementsByClass("views-field-tid");

                if (views_field_tids != null && views_field_tids.size() > 0) {
                    Element views_field_tid = views_field_tids.get(0);
                    if (views_field_tid != null && views_field_tid.select("img") != null && views_field_tid.select("img").size() > 0) {
                        String imgUrl = "http:" + views_field_tid.select("img").get(0).attr("src");
                        linkEntity.setImgUrl(imgUrl);
                    }

                    if (views_field_tid != null && views_field_tid.select("a") != null && views_field_tid.select("a").size() > 0) {
                        String detailUrl = "http://www.juzimi.com" + views_field_tid.select("a").get(0).attr("href");
                        linkEntity.setDetailUrl(detailUrl);
                    }
                }

                Elements views_field_phpcodes = rowElement.getElementsByClass("views-field-phpcode");
                Element views_field_phpcode = views_field_phpcodes.get(0);

                // 标题
                Elements xqallarticletilelinkspans = views_field_phpcode.getElementsByClass("xqallarticletilelinkspan");
                if (xqallarticletilelinkspans != null && xqallarticletilelinkspans.size() > 0) {
                    Element xqallarticletilelinkspan = xqallarticletilelinkspans.get(0);

                    if (xqallarticletilelinkspan != null) {
                        String title = xqallarticletilelinkspan.text();
                        linkEntity.setTitle(title);
                    }
                }

                // 内容
                Elements xqagepawirdescs = views_field_phpcode.getElementsByClass("xqagepawirdesc");
                if (xqagepawirdescs != null && xqagepawirdescs.size() > 0) {
                    Element xqagepawirdesc = xqagepawirdescs.get(0);
                    if (xqagepawirdesc != null) {
//                                    String content = xqagepawirdesc.text().replaceAll(" ", "\n");
                        String content = xqagepawirdesc.text();
                        linkEntity.setContent(content);
                    }
                }

                Elements xqagepawirdesclinks = views_field_phpcode.getElementsByClass("xqagepawirdesclink");
                if (xqagepawirdesclinks != null && xqagepawirdesclinks.size() > 0) {
                    Element xqagepawirdesclink = xqagepawirdesclinks.get(0);
                    if (xqagepawirdesclink != null) {
                        // 来源、个数
                        String source_num = xqagepawirdesclink.text();
                        linkEntity.setSource_num(source_num);
                    }
                }

                linkEntityList.add(linkEntity);
            }

//            System.out.println(sentenceSimples.size());

//            for (int i = 0; i < sentenceSimples.size(); i++) {
//                LogUtils.e(sentenceSimples.get(i));
//            }

        }

        return linkEntityList;
    }


    /**
     * 原创句子
     *
     * @param result
     * @return
     */
    public static List<SentenceDetail> parseOrignal(String result) {

        Document doc = Jsoup.parse(result);

        Elements field_contents = doc.getElementsByClass("views-field-phpcode");

        List<SentenceDetail> sentenceDetails = new ArrayList<>();

        for (int i = 0; i < field_contents.size(); i++) {

            SentenceDetail sentenceDetail = new SentenceDetail();

            Element field_content = field_contents.get(i);
            if (field_content != null) {
                Elements xlistjus = field_content.getElementsByClass("xlistju");
                if (xlistjus != null && xlistjus.size() > 0) {

//                    String conent = xlistjus.get(0).text();
                    String conent = xlistjus.get(0).text().replaceAll(" ", "\n");

                    sentenceDetail.setContent(conent);

                    sentenceDetails.add(sentenceDetail);

//                    LogUtils.e(sentenceDetail);
                }
            }
        }

        return sentenceDetails;

    }

    /**
     * 句集
     *
     * @param result
     * @return
     */
    public static List<SeeEntity> parseSee(String result) {

        Document doc = Jsoup.parse(result);

        List<SeeEntity> seeEntityList = new ArrayList<>();


        Elements views_field_sns_values = doc.getElementsByClass("views-field-phpcode");
        for (int i = 0; i < views_field_sns_values.size(); i++) {
            Element views_field_sns_value = views_field_sns_values.get(i);
            if (views_field_sns_value != null) {
                SeeEntity  entity  =  new SeeEntity();

                Elements views_field_picture_bares = views_field_sns_value.getElementsByClass("views-field-picture-bare");
                if (views_field_picture_bares != null && views_field_picture_bares.size() > 0) {
                    Element views_field_picture_bare = views_field_picture_bares.first();

                    Elements as = views_field_picture_bare.select("a");
                    if (as != null && as.size() > 0) {
                        Element a = as.first();
                        String url = "http://www.juzimi.com" + a.attr("href");

                        entity.setDetailUrl(url);
                    }

                    Elements imgs = views_field_picture_bare.select("img");
                    if (imgs != null && imgs.size() > 0) {
                        Element img = imgs.first();
                        String src = "http:" + img.attr("src");

                        entity.setImgUrl(src);
                    }
                }

                Elements views_field_titles = views_field_sns_value.getElementsByClass("views-field-title");
                if (views_field_titles != null && views_field_titles.size() > 0) {
                    Element views_field_title = views_field_titles.first();

                    Elements as = views_field_title.select("a");
                    if (as != null && as.size() > 0) {
                        Element a = as.first();
                        String title = a.text();

                        entity.setTitle(title);
                    }
                }

                Elements views_field_bodys = views_field_sns_value.getElementsByClass("views-field-body");
                if (views_field_bodys != null && views_field_bodys.size() > 0) {
                    Element views_field_title = views_field_bodys.first();
                    if (views_field_title != null) {
                        String desc = views_field_title.text();
                        entity.setDesc(desc);
                    }
                }

                // 句集中的句子数
                Elements views_field_phpcodes = views_field_sns_value.getElementsByClass("views-field-phpcode-2");
                if (views_field_phpcodes != null && views_field_phpcodes.size() > 0) {
                    Element views_field_phpcode = views_field_phpcodes.get(0);
                    if (views_field_phpcode != null && views_field_phpcode.select("span") != null) {
                        String countStr = views_field_phpcode.select("span").text();

                        // 通过正则，取出其中的数字
                        String count = countStr.replaceAll("\\(收录", "").replaceAll("个句子\\)", "");

                        entity.setCount(count);
                    }
                }

                // 创建时间
                Elements views_field_createds = views_field_sns_value.getElementsByClass("views-field-created");
                if (views_field_createds != null && views_field_createds.size() > 0) {
                    Element views_field_created = views_field_createds.get(0);
                    if (views_field_created != null && views_field_created.select("span") != null) {
                        String countStr = views_field_created.select("span").text();

                        // 通过正则，取出其中的数字
                        String createTime = countStr.replaceAll("于: ", "");

                        entity.setCreateTime(createTime);
                    }
                }

                // 上传者
                Elements views_field_names = views_field_sns_value.getElementsByClass("views-field-name");
                if (views_field_names != null && views_field_names.size() > 0) {
                    Element views_field_name = views_field_names.get(0);
                    if (views_field_name != null && views_field_name.select("a") != null) {
                        String username = views_field_name.select("a").text();

                        entity.setUsername(username);
                    }
                }

//                LogUtils.e(sentenceCollection);
                seeEntityList.add(entity);
            }
        }

        return seeEntityList;
    }

    /**
     * 美图美剧
     *
     * @param
     * @param result
     * @return
     */
    public static ListenListDetail parseMeiju(String result) {

        Document doc = Jsoup.parse(result);

        ListenListDetail listenListDetail = new ListenListDetail();

        /*// 仅第一次记录页数
        if (isFirst) {
            Elements pageLasts = doc.getElementsByClass("pager-last");
            if (pageLasts != null && pageLasts.size() > 0) {
                String page = pageLasts.first().text();
                listenListDetail.page = page;
            }
        }*/

        List<ListenEntity> listenEntityList = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        Elements views_field_phpcodes = doc.getElementsByClass("views-field-phpcode");
        for (int i = 0; i < views_field_phpcodes.size(); i++) {
            Element views_field_phpcode = views_field_phpcodes.get(i);
            if (views_field_phpcode != null) {

                Element bdshare = views_field_phpcode.getElementById("bdshare");
                if (bdshare != null) {

                    String data = bdshare.attr("data");
//                    LogUtils.e(data);

                    try {
                        JSONObject jsonObject = new JSONObject(data);

//                        LogUtils.e(jsonObject.get("text") + "  " + jsonObject.get("desc") + "  " + jsonObject.get("url") + "  " + jsonObject.get("pic"));

                        ListenEntity listenEntity = new ListenEntity();
                        listenEntity.setText("" + jsonObject.get("text"));
                        listenEntity.setDesc("" + jsonObject.get("desc"));
                        listenEntity.setUrl("" + jsonObject.get("url"));
                        listenEntity.setPic("" + jsonObject.get("pic"));

                        listenEntityList.add(listenEntity);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        listenListDetail.listenEntityList = listenEntityList;
        return listenListDetail;
    }


    /**
     * 句子的详情
     *
     * @param result
     * @return
     */
    public static ListenListDetail parseJuziDetail(boolean isFirst, String result) {

        ListenListDetail listenListDetail = new ListenListDetail();

        Document doc = Jsoup.parse(result);

        // 仅第一次记录页数
        if (isFirst) {
            Elements pageItems = doc.getElementsByClass("pager-item");
            if (pageItems != null && pageItems.last() != null) {
                String page = pageItems.last().text();
                listenListDetail.page = page;
            } else {
                listenListDetail.page = null;
            }
        }

        Elements field_contents = doc.select("div.views-field-field-sns-value");

        List<ListenEntity> listenEntityList = new ArrayList<>();

        for (int i = 0; i < field_contents.size(); i++) {

            Element field_content = field_contents.get(i);
            if (field_content != null) {
                Elements bdshares = field_content.select("div#bdshare");
                if (bdshares != null && bdshares.size() > 0) {
                    String data = bdshares.first().attr("data");
                    try {
                        JSONObject jsonObject = new JSONObject(data);

                        ListenEntity listenEntity = new ListenEntity();
                        listenEntity.setText("" + jsonObject.get("text"));
                        listenEntity.setDesc("" + jsonObject.get("desc"));
                        listenEntity.setUrl("" + jsonObject.get("url"));
                        listenEntity.setPic("" + jsonObject.get("pic"));

                        listenEntityList.add(listenEntity);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        listenListDetail.listenEntityList = listenEntityList;

        return listenListDetail;

    }
}
