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
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author LDDFY
 */
@Repository
public class CreateIndex {

    /**
     * Mapper程序用于读出mongo数据库数据并将每条记录转换成Map
     */
    public static class IndexMapper extends Mapper<Object, BSONObject, Text, MapWritable> {
        @Override
        protected void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
            //声明MapWritable对象
            MapWritable mapWritable = new MapWritable();
            //将BSONObject mongo记录对象转换成Map对象
            Map tempMap = value.toMap();
            for (Object mapKey : tempMap.keySet()) {
                //将普通Map对象转换成reduce输入对象形式
                mapWritable.put(new Text(mapKey.toString()), new Text(tempMap.get(mapKey).toString()));
            }
            //写入对象到reduce中
            context.write(new Text(value.get("_id").toString()), mapWritable);
        }
    }

    /**
     * Reduce程序用于将Map集合中的数据记录转换成Lucene类型文档
     */
    public static class IndexReduce extends Reducer<Text, MapWritable, Text, LuceneDocument> {
        @Override
        protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
            //声明自定义Lucene文档对象
            LuceneDocument luceneDocument = new LuceneDocument();
            Iterator<MapWritable> iterator = values.iterator();
            //循环values对象生成lucene文档并将文档写出
            while (iterator.hasNext()) {
                luceneDocument.setFile(iterator.next());
                context.write(key, luceneDocument);
            }
        }
    }

    /**
     * MapReduce执行设置
     *
     * @return
     */
    public boolean IndexRun() {
        Configuration conf = new Configuration();
        //设置数据输入为mongoDB数据库
        MongoConfigUtil.setInputURI(conf, Constants.MONGO_HADOOP_INPUTURI);
        System.setProperty("hadoop.home.dir", Constants.HADOOP_HOME_DIR);
        conf.addResource(new Path("core-site.xml"));
        conf.addResource(new Path("hdfs-site.xml"));
        conf.addResource(new Path("yarn-site.xml"));

//        //设置HDFS地址
//        conf.set("fs.defaultFS", Constants.FS_DEFAULTFS);
//        //指定MapReduce程序运行框架
//        conf.set("mapreduce.framework.name", Constants.MAPREDUCE_FRAMEWORK_NAME);
//        //设置避免windows下提交报错
//        conf.set("mapreduce.app-submission.cross-platform", Constants.MAPREDUCE_APP_SUBMISSION_CROSS_PLATFORM);
//        //设置ResourseManager地址
//        conf.set("yarn.resourcemanager.address", Constants.YARN_RESOURCEMANAGER_ADDRESS);
        try {
            //设置程序结果输出路径
            Path outPath = new Path(Constants.INDEX_DRI);
            FileSystem fs = FileSystem.get(conf);
            if (fs.exists(outPath)) {
                //避免路径下存在内容则报错需要先将该路径下内容删除
                fs.delete(outPath, true);
            }

            //实例化Job
            Job job = Job.getInstance(conf, "Index job");
            //设置Job执行类
            job.setJarByClass(CreateIndex.class);
            //设置Map程序类
            job.setMapperClass(CreateIndex.IndexMapper.class);
            //设置Reduce程序类
            job.setReducerClass(CreateIndex.IndexReduce.class);
            //设置Map程序输出Key的类型
            job.setMapOutputKeyClass(Text.class);
            //设置Map程序输出Value的类型
            job.setMapOutputValueClass(MapWritable.class);
            //设置输出key类型
            job.setOutputKeyClass(Text.class);
            //设置输出value类型
            job.setOutputValueClass(LuceneDocument.class);
            //设置输入格式化类
            job.setInputFormatClass(MongoInputFormat.class);
            //设置输出格式化类
            job.setOutputFormatClass(LuceneDocumentOutput.class);
            //添加job输出路径
            FileOutputFormat.setOutputPath(job, outPath);
            return job.waitForCompletion(true) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        boolean flag = new CreateIndex().IndexRun();
        System.out.println("-------------------------------->" + flag);
    }

}
