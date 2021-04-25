### A Simple Connection Pool and a Semi-automatic Persistence Layer ORM Framework
[toc]
[https://github.com/Fickle519/simple-connection-pool.git]
Author: Xuanhe Er from Chengdu University of Information Technology(CUIT)

#### Simple Connection Pool

The connection pool is devised to avoid following question:
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

#### Persistence Layer ORM Frame
The ORM frame is devised to avoid following question:
1. To diminish the code redundancy in JDBC operation.(create connectivity)
2. Reduce code development and improve efficiency

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

using in mapper(DAO layer) **<EXAMPLE>**
> following is a DAO layer class

```java
@Mapper
public class UserMapper{
    private JdbcSession session = new JdbcSession();

    public List<User> getUserList(String sql,User user){
        String sql = "SELECT * FROM USER WHERE userID = #{userID}";
        List<User> userList = session.selectList(sql, User.class, user);
        return userList;
    }
}
```
4. Then the ```getUserList``` method is callable in service layer.


###### Divise

1. using template schema to diminish code redundancy
2. Convert multi-line JDBC operations to one line(update,insert,delete,selectOne,selectList)
3. using PreparedStatement to avoid SQL injection attack
4. the orm tool could help to assemble query results rows into objects based on Strategy or Reflect.
5. through sql containing #{xxxx(example)} to get the value(field of input object),put these values into sql.Then executing them. increase the efficiency of coding.
6. easy to use.


###### future

1. convert the DAO layer class into a interface through dynamic proxy method to further reduce the amount of code developers.Put annotations on methods on this interface's methods.
