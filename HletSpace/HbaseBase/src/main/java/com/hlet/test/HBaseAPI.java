package com.hlet.test;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HBaseAPI {
    public static boolean  isTableExists(String tableName) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","10.1.30.13,10.1.30.14,10.1.30.15");
        configuration.set("hbase.zookeeper.property,clientPort","2181");
        configuration.set("hbase.master,clientPort","hdfs03:600000");
        configuration.set("zookeeper.znode.parent","/hbase-unsecure");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        boolean result = admin.tableExists(TableName.valueOf(tableName));
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(isTableExists("bd_position_rate"));
    }
}
