package com.zzuli.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author LDDFY
 *         实现lucene Document的Hdfs接口
 */
public class LuceneDocumentOutput extends FileOutputFormat<Text, LuceneDocument> {
    @Override
    public RecordWriter<Text, LuceneDocument> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        final LuceneWriter lw = new LuceneWriter();
        lw.open(job.getConfiguration());

        return new RecordWriter<Text, LuceneDocument>() {
            @Override
            public void write(Text text, LuceneDocument luceneDocument) throws IOException, InterruptedException {
                lw.write(luceneDocument);
            }

            @Override
            public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
                lw.close();
            }
        };
    }
}
