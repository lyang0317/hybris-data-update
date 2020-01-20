package com.lyang.dataconsumer.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * FileName: MongoClientUtils
 * Author:   lujy7
 * Date:     2020/1/15 14:11
 * Description:
 */
public class MongoClientUtils {

    private static final String IP = "10.62.109.136";
    private static final int PORT = 27017;
    private static final String DB = "DIT1";

    private static MongoClient mongoClient;

    static {
        /*System.out.println("===============MongoDBUtil初始化========================");
        mongoClient = new MongoClient(IP, PORT);
        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// 自动重连true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
        options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
        options.maxWaitTime(5000); //
        options.socketTimeout(0);// 套接字超时时间，0无限制
        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        options.writeConcern(WriteConcern.SAFE);//
        options.build();*/
    }

    public static MongoCollection<Document> getCollection(String collName) {
        return mongoClient.getDatabase(DB).getCollection(collName);
    }

}
