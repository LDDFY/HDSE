package com.zzuli.index;

import com.zzuli.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.LimitTokenCountAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogDocMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;

/**
 * @author LDDFY
 */
public class LuceneWriter implements Serializable {
    private Path prem;
    private Path temp;
    private FileSystem fs;
    private IndexWriter writer;

    public void open(Configuration config) throws IOException {
        this.fs = FileSystem.get(config);
        prem = new Path(config.get("mapreduce.output.fileoutputformat.outputdir"));
        // 临时本地路径，存储临时的索引结果
        temp = config.getLocalPath("mapreduce.cluster.local.dir", "index/_" + Integer.toString(new Random().nextInt()));
        if (fs.exists(prem)) {
            fs.delete(prem, true);
        }

        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        LimitTokenCountAnalyzer itca = new LimitTokenCountAnalyzer(analyzer, Integer.MAX_VALUE);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_34, itca);
        indexWriterConfig.setMaxBufferedDocs(100000);
        LogMergePolicy mergePolicy = new LogDocMergePolicy();
        mergePolicy.setMergeFactor(100000);
        mergePolicy.setMaxMergeDocs(100000);
        indexWriterConfig.setMergePolicy(mergePolicy);
        indexWriterConfig.setRAMBufferSizeMB(256);
        indexWriterConfig.setMergePolicy(mergePolicy);

        File file = new File(fs.startLocalOutput(prem, temp).toString());
        writer = new IndexWriter(FSDirectory.open(file), indexWriterConfig);
    }

    public void close() throws IOException {
        writer.optimize();
        writer.close();
        fs.completeLocalOutput(prem, temp);
        fs.createNewFile(new Path(prem, Constants.INDEX_FLAG));
    }

    public void write(LuceneDocument doc) throws IOException {
        Map fields = doc.getFile();
        Document luceneDoc = DocConvertUtil.mapToDoc(fields);
        writer.addDocument(luceneDoc);
    }
}
