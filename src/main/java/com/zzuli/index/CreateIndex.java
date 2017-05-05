package com.zzuli.index;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.util.MongoConfigUtil;
import com.zzuli.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.bson.BSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author LDDFY
 */
public class CreateIndex {

    public static class IndexMapper extends Mapper<Object, BSONObject, Text, MapWritable> {
        @Override
        protected void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
            MapWritable mapWritable=new MapWritable();
            Map  tempMap=value.toMap();
            for(Object mapKey:tempMap.keySet()){
                mapWritable.put(new Text(mapKey.toString()),new Text(tempMap.get(mapKey).toString()));
            }
            context.write(new Text(value.get("_id").toString()),mapWritable );
        }
    }


    public static class IndexReduce extends Reducer<Text, MapWritable, Text, LuceneDocument> {
        @Override
        protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
            LuceneDocument luceneDocument = new LuceneDocument();

            Iterator<MapWritable> iterator = values.iterator();

            while(iterator.hasNext()){
                luceneDocument.setFile(iterator.next());
                context.write(key,luceneDocument);
            }
        }
    }

    public static boolean IndexRun() {
        Configuration conf = new Configuration();
        MongoConfigUtil.setInputURI(conf, Constants.MONGO_HADOOP_INPUTURI);
        conf.set("fs.defaultFS", Constants.FS_DEFAULTFS);
        conf.set("mapreduce.framework.name", Constants.MAPREDUCE_FRAMEWORK_NAME);
        conf.set("mapreduce.app-submission.cross-platform", Constants.MAPREDUCE_APP_SUBMISSION_CROSS_PLATFORM);
        conf.set("yarn.resourcemanager.address", Constants.YARN_RESOURCEMANAGER_ADDRESS);
        try {
            Path outPath = new Path(Constants.INDEX_DRI);
            FileSystem fs = FileSystem.get(conf);
            if (fs.exists(outPath)) {
                fs.delete(outPath, true);
            }

            Job job = Job.getInstance(conf, "Index job");
            job.setJarByClass(CreateIndex.class);

            job.setMapperClass(CreateIndex.IndexMapper.class);
            job.setReducerClass(CreateIndex.IndexReduce.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(MapWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LuceneDocument.class);

            job.setInputFormatClass(MongoInputFormat.class);
            job.setOutputFormatClass(LuceneDocumentOutput.class);

            FileOutputFormat.setOutputPath(job, outPath);

            return job.waitForCompletion(true) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        boolean flag = IndexRun();
        System.out.println("-------------------------------->" + flag);
    }
}
