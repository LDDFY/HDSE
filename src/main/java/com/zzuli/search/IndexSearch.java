package com.zzuli.search;

import com.zzuli.Constants;
import com.zzuli.vo.WeiBo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
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
 * Created by LDDFY on 2017/4/24.
 */
@Repository
public class IndexSearch {

    private IndexReader reader;

    private IndexSearcher searcher;

    //带参数构造方法
    /*public IndexSearch(FsDirectory dir) throws CorruptIndexException, IOException {
        reader = IndexReader.open(dir);
        searcher = new IndexSearcher(reader);
    }*/

    //默认构造方法
    public IndexSearch(){
        try {
            System.setProperty("hadoop.home.dir", Constants.HADOOP_HOME_DIR);
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", Constants.FS_DEFAULTFS);
            FileSystem fs = FileSystem.get(conf);
            FsDirectory dir = new FsDirectory(fs, new Path(Constants.FS_DEFAULTFS + "/user/luceneIndex/"), false, conf);
            reader = IndexReader.open(dir);
            searcher = new IndexSearcher(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 分页查询索引
     * @param entity
     * @param pager
     * @return
     */
    public Pager<WeiBo> queryLucenePage(SearchEntity entity, Pager<WeiBo> pager) {
        //当前页码
        int currentPage = pager.getCurrentPage();
        if(currentPage==0){
            currentPage=1;
        }
        int pageSize = Constants.PAGE_SIZE;
        List<WeiBo> weiBos = new ArrayList<WeiBo>();
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        QueryParser queryParser = new QueryParser(Version.LUCENE_34,entity.getType(), analyzer);
        //查询总条数
        int totalRecord = 0;
        try {
            Query query = queryParser.parse(entity.getKey());
            //记录获取起始位置
            int fromIndex = pageSize * (currentPage - 1);
            TopDocs docs =null;
            ScoreDoc scoreDoc = null;
            if(fromIndex>0){
                docs = searcher.search(query, fromIndex);
                scoreDoc = docs.scoreDocs[fromIndex - 1];
            }

            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
            Highlighter highlight = new Highlighter(formatter, new QueryScorer(query));
            TopDocs hits = searcher.searchAfter(scoreDoc, query, pageSize);
            totalRecord = hits.totalHits;
            Document doc = null;
            for (ScoreDoc sd : hits.scoreDocs) {
                doc = searcher.doc(sd.doc);
                WeiBo weiBo = LuceneConvertUtils.documentToWeiBo(doc, highlight);
                weiBos.add(weiBo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pager<WeiBo>(currentPage, pageSize, totalRecord, weiBos);
    }


    /**
     * 单个文档查询
     *
     * @param entity
     * @param page
     * @return
     * @throws CorruptIndexException
     * @throws IOException
     */
    public Document search(SearchEntity entity, Pager page) throws CorruptIndexException, IOException {
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
