package com.zzuli.index;

import net.sf.json.JSONObject;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.util.Map;

/**
 * @author LDDFY
 *         用于转换mongo记录到lucnene文档
 */
public class DocConvertUtil {

    public static Document mapToDoc(Map fields) {
        Document luceneDoc = new Document();
        for (Object key : fields.keySet()) {
            if (key.toString().contains("userinfo")) {
                String str=fields.get(key).toString();
                JSONObject obj=JSONObject.fromObject(str);
                for(Object o:obj.keySet()){
                    luceneDoc.add(new Field(o.toString(), obj.getString(o.toString()), Field.Store.YES, Field.Index.ANALYZED));
                }
            } else {
                luceneDoc.add(new Field(key.toString(), fields.get(key).toString(), Field.Store.YES, Field.Index.ANALYZED));
            }
        }
        return luceneDoc;
    }
}
