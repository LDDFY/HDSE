package com.zzuli.search;

import com.zzuli.vo.User;
import com.zzuli.vo.WeiBo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.util.Version;

import java.io.StringReader;

/**
 *@author LDDFY
 *lucene 文档与实体之间的转换
 */
public class LuceneConvertUtils {


    public static String getHightLightStr(Highlighter highlight, String key, String value) {
        try {
            TokenStream tokens = new StandardAnalyzer(Version.LUCENE_34).tokenStream(key, new StringReader(value));
            String temp = highlight.getBestFragment(tokens, value);
            return temp == null ? value : temp;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 转化并高亮显示
     *
     * @param doc       lucene文档
     * @param highlight 高亮
     * @return
     */
    public static WeiBo documentToWeiBo(Document doc, Highlighter highlight) {
        User user = new User();
        WeiBo weiBo = new WeiBo();
        try {
            String id = doc.get("_id");
            if (StringUtils.isNotBlank(id)) {
                weiBo.setId(id);
            }

            String repostsCount = doc.get("reposts_count");
            if (StringUtils.isNotBlank(repostsCount)) {
                weiBo.setRepostsCount(getHightLightStr(highlight, "reposts_count", repostsCount));
            }

            String commentsCount = doc.get("comments_count");
            if (StringUtils.isNotBlank(commentsCount)) {
                weiBo.setCommentsCount(getHightLightStr(highlight, "comments_count", commentsCount));
            }

            String text = doc.get("text");
            if (StringUtils.isNotBlank(text)) {
                weiBo.setText(getHightLightStr(highlight, "text", text));
            }

            String location = doc.get("location");
            if (StringUtils.isNotBlank(location)) {
                weiBo.setLocation(getHightLightStr(highlight, "location", location));
            }

            String date = doc.get("date");
            if (StringUtils.isNotBlank(location)) {
                weiBo.setDate(getHightLightStr(highlight, "date", date));
            }

            String comments = doc.get("comments");
            if (StringUtils.isNotBlank(comments)) {
                weiBo.setComments(getHightLightStr(highlight, "comments", comments));
            }

            String likesCount = doc.get("likes_count");
            if (StringUtils.isNotBlank(likesCount)) {
                weiBo.setLikesCount(getHightLightStr(highlight, "likes_count", likesCount));
            }

            String userInfo = doc.get("userinfo");
            if (StringUtils.isNotBlank(userInfo)) {
                JSONObject object = JSONObject.fromObject(userInfo);
                String gender = object.getString("gender");
                if (StringUtils.isNotBlank(gender)) {
                    user.setGender(getHightLightStr(highlight, "gender", gender));
                }
                String region = object.getString("region");
                if (StringUtils.isNotBlank(region)) {
                    user.setRegion(getHightLightStr(highlight, "region", region));
                }
                String name = object.getString("name");
                if (StringUtils.isNotBlank(name)) {
                    user.setName(getHightLightStr(highlight, "name", name));
                }
                String birthdate = object.getString("birthdate");
                if (StringUtils.isNotBlank(birthdate)) {
                    user.setBirthDate(getHightLightStr(highlight, "birthdate", birthdate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        weiBo.setUserInfo(user);
        return weiBo;
    }
}
