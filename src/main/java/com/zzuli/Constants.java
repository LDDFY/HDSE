package com.zzuli;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LDDFY
 *         hadoop变量地址
 */
public class Constants {

    //Mongo数据库连接信息
    public static final String MONGO_HOST = "172.20.81.134";

    //端口号
    public static final int MONGO_PORT = 27017;

    //数据库名称
    public static final String DB_NAME = "weibo";

    //collection 名称
    public static final String DB_COLLECTION_NAME = "data";

    //mongo链接地址
    public static final String MONGO_HADOOP_INPUTURI = "mongodb://" + MONGO_HOST + ":" + MONGO_PORT + "/" + DB_NAME + "." + DB_COLLECTION_NAME;

    //hdfs文件系统地址
    public static final String FS_DEFAULTFS = "hdfs://172.20.81.130:9000";

    //索引在hdfs上存储位置
    public static final String INDEX_DRI = FS_DEFAULTFS + "/usr/luceneIndex";

    //索引创建成功标志
    public static final String INDEX_FLAG = "index.done";

    //本机hadoop环境变量地址
    public static final String HADOOP_HOME_DIR = "D:\\Software\\Develop\\Hadoop";

    //指定hadoop系统
    public static final String MAPRED_REMOTE_OS = "Linux";

    //mapreduce计算框架
    public static final String MAPREDUCE_FRAMEWORK_NAME = "yarn";

    //windows下提交报/bin/bash: line 0: fg: no job control
    public static final String MAPREDUCE_APP_SUBMISSION_CROSS_PLATFORM = "true";

    //yarn 地址
    public static final String YARN_RESOURCEMANAGER_ADDRESS = "172.20.81.130:18040";

    //每页显示的条数
    public static final Integer PAGE_SIZE = 10;

    //分页页码显示个数
    public static final Integer PAGE_NUMBER = 5;

    //查询字段
    public static final Map<String, String> searchCodeMap = new LinkedHashMap<String, String>();
    //发布人
    public static final String CODE_AUTHOR = "name";
    //发布时间
    public static final String CODE_DATE ="date";
    //发布地区
    public static final String CODE_REGION = "region";
    //发布内容
    public static final String CODE_TEXT = "text";

    static {
        searchCodeMap.put(CODE_TEXT, "内容");
        searchCodeMap.put(CODE_REGION, "地区");
        searchCodeMap.put(CODE_AUTHOR, "发布人");
        searchCodeMap.put(CODE_DATE, "时间");
    }

    public static String getSearchCodeMap(String key) {
        return searchCodeMap.get(key);
    }
}
