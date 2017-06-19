package com.zzuli.search;

import com.zzuli.Constants;
import com.zzuli.cache.MemoryCached;
import com.zzuli.vo.WeiBo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LDDFY
 */
@Repository
public class IndexSearch {

    private IndexReader reader;

    private IndexSearcher searcher;

    public IndexSearch() {
        try {
            //window下运行设置本地hadoop home地址
            System.setProperty("hadoop.home.dir", Constants.HADOOP_HOME_DIR);
            Configuration conf = new Configuration();
            //设置HDFS地址
            conf.addResource(new Path("core-site.xml"));
            conf.addResource(new Path("hdfs-site.xml"));
            FileSystem fs = FileSystem.get(conf);
            //创建FsDirectory对象
            FsDirectory dir = new FsDirectory(fs, new Path(Constants.INDEX_DRI), false, conf);
            //打开文件索引位置
            reader = IndexReader.open(dir);
            //声明搜索位置
            searcher = new IndexSearcher(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 分页查询索引
     *
     * @param entity
     * @param pager
     * @return
     */
    public Pager<WeiBo> queryLucenePage(SearchEntity entity, Pager<WeiBo> pager) {
        //当前页码
        int currentPage = pager.getCurrentPage();
        if (currentPage == 0) {
            //第一次查询时不存在页码默认为第一页
            currentPage = 1;
            pager.setCurrentPage(currentPage);
        }

        System.out.println("------------------>开始查询时间："+ System.currentTimeMillis());
        //查询缓存中的数据内容
        Pager<WeiBo> datas = MemoryCached.getWeiBoCache(entity, pager);
        if (datas != null) {
            System.out.println("---------------cached");
            System.out.println("------------------>结束查询时间："+ System.currentTimeMillis());
            return datas;
        }


        //设置每页显示记录的条数
        int pageSize = Constants.PAGE_SIZE;
        //用来保存查询记录
        List<WeiBo> weiBos = new ArrayList<WeiBo>();
        //声明分词器类型
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        //声明查询内容
        QueryParser queryParser = new QueryParser(Version.LUCENE_34, entity.getType(), analyzer);
        //查询总条数
        int totalRecord = 0;
        try {
            Query query = queryParser.parse(entity.getKey());
            //记录获取起始位置
            int fromIndex = pageSize * (currentPage - 1);
            //用于保存符合记录的文本
            TopDocs docs = null;
            ScoreDoc scoreDoc = null;
            if (fromIndex > 0) {
                docs = searcher.search(query, fromIndex);
                scoreDoc = docs.scoreDocs[fromIndex - 1];
            }
            //声明Html字符串格式话格式
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
            //声明高亮显示
            Highlighter highlight = new Highlighter(formatter, new QueryScorer(query));
            //Lucene分页查询
            TopDocs hits = searcher.searchAfter(scoreDoc, query, pageSize);
            totalRecord = hits.totalHits;
            Document doc = null;
            for (ScoreDoc sd : hits.scoreDocs) {
                doc = searcher.doc(sd.doc);
                //将查询结果转化为实体类
                WeiBo weiBo = LuceneConvertUtils.documentToWeiBo(doc, highlight);
                weiBos.add(weiBo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        datas=new Pager<WeiBo>(currentPage, pageSize, totalRecord, weiBos);
        //数据不存在则添加到缓存中去
        MemoryCached.putWeiBoCache(entity,datas);
        System.out.println("---------------add cached");
        System.out.println("------------------>结束查询时间："+ System.currentTimeMillis());
        return datas;
    }


    /**
     * 单个文档查询
     *
     * @param entity
     * @param page
     * @return
     */
    public Document search(SearchEntity entity, Pager page) {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        QueryParser queryParser = new QueryParser(Version.LUCENE_34, Constants.getSearchCodeMap(entity.getType()), analyzer);
        Document doc = null;
        try {
            Query query = queryParser.parse(entity.getKey());
            TopDocs docs = searcher.search(query, Constants.PAGE_SIZE);
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                doc = searcher.doc(scoreDoc.doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public void close() throws IOException {
        // 关闭对象
        searcher.close();
        reader.close();
    }
}
