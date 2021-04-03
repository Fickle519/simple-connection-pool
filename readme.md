#### A simple connection pool
[toc]
[https://github.com/Fickle519/simple-connection-pool.git]
Author: Xuanhe Er from Chengdu University of Information Technology(CUIT)
##### Question
The connection pool devise to avoid following question:
1. building a connection is a costing time scope in JDBC.

##### Quick Test


###### dependencies

- JDK8
- IntellIJ IDEA 
- Maven
- pom.xml : ```mysql-connector```

```
<dependencies>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.23</version>
        </dependency>
</dependencies>

```

###### Run

1. pom.xml to reimport
2. modify the **connpool.properties** to adapt your database(If you are using another database(rather MySQL) you should also change the connector between Java and your database)
3. simple apply it to your project.

using method example in test.TestConnPool

###### Divise

1. avoid multi-Thread Concurrent problems while getting connections.
2. using static proxy to restore the similating using methods in origin JDBC operation.
3. Default Adapter Pattern to Selective inherit  methods in upper interface.
4. using native configuration file to modify the configuration working for the pool.
5. the pool is designed with lazy-init policy while the configuration would be pre-loaded in static Map<String, String> in ConfigClass.
6. if there are no available connections in the pool,the pool allow to wait 5 sec(modify in config file) waiting for available connections at most(rather directly return null).


