package util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by LDDFY on 2017/5/21.
 */
public class Tools {
    @Test
    public void testAA() {
        try {
            File file = new File("F:\\毕设\\日期数据\\sogou.500w.utf8");
            List<String> data = FileUtils.readLines(file, "UTF-8");
            FileWriter fileWriter = new FileWriter("F:\\毕设\\日期数据\\sogou.500w.json", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            int index = 0;
            StringBuffer temp = new StringBuffer(100);
            for (String line : data) {
                String[] contents = line.split("\t");
                if (contents.length == 6) {
                    String id = contents[1];
                    String date = contents[0];
                    String name = contents[2];
                    String url = contents[5];
                    temp.append("{\"_id\": { \"$oid\": \"" + id + "\" },");
                    temp.append("\"name\": \"" + name + " \"," + "\"url\":" + "\"" + url + "\",");
                    temp.append("\"date\":" + "\"" + date + "\"}");
                    writer.write(temp.toString() + "\n");
                    temp.setLength(0);
                }
                index++;
                if (index % 100 == 0) {
                    writer.flush();
                    //break;
                }
            }
            writer.flush();
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copyData() {
        try {
            File file = new File("F:\\毕设\\日期数据\\DATA.json");
            List<String> data = FileUtils.readLines(file, "UTF-8");
            FileWriter fileWriter = new FileWriter("F:\\毕设\\日期数据\\DATA_COPY01.json", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String replaceStr="";
            int index = 0;
            StringBuffer temp = new StringBuffer();
            for (String line : data) {
                if (line.contains("$oid")) {
                    UUID uuid = UUID.randomUUID();
                    temp.append("{ \"_id\" : { \"$oid\" : \"");
                    temp.append(uuid.toString().replace("-","").substring(0,24)+"\"");
                    temp.append(line.substring(line.indexOf(" },"),line.length()));
                    temp.append("\n");
                    writer.write(temp.toString());
                    temp.setLength(0);
                    index++;
                    if (index % 100 == 0) {
                        writer.flush();
                    }
                }
            }
            writer.flush();
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
