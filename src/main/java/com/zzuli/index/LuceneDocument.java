package com.zzuli.index;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author LDDFY
 */
public class LuceneDocument implements Writable {
    Map  file = new HashMap ();

    public Map  getFile() {
        return file;
    }

    public void setFile(Map  file) {
        this.file = file;
    }

    public LuceneDocument() {

    }

    public LuceneDocument(Map  file) {
        this.file = file;
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        file.clear();
        String key = null, value = null;
        int size = WritableUtils.readVInt(dataInput);
        for (int i = 0; i < size; i++) {
            key = dataInput.readUTF();
            value = dataInput.readUTF();
            file.put(key, value);
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        String key = null, value = null;
        Iterator  iterator = file.keySet().iterator();
        while (iterator.hasNext()) {
            key = iterator.next().toString();
            value = file.get(key).toString();
            dataOutput.writeUTF(key);
            dataOutput.writeUTF(value);
        }
    }
}
