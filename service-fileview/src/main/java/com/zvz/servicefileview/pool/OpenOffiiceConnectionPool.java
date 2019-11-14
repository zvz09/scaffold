package com.zvz.servicefileview.pool;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;

import java.net.ConnectException;
import java.util.Enumeration;
import java.util.Vector;

public class OpenOffiiceConnectionPool {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8100;

    private String host;
    private int port;

    private int initialConnections = 10; // 连接池的初始大小
    private int incrementalConnections = 5;// 连接池自动增加的大小
    private int maxConnections = 20; // 连接池最大的大小

    private Vector connections = null; // 存放连接池中连接的向量 , 初始时为 null

    public OpenOffiiceConnectionPool() {
        this.host = DEFAULT_HOST;
        this.port = DEFAULT_PORT;
    }

    public OpenOffiiceConnectionPool(int port) {
        this.host = DEFAULT_HOST;
        this.port = port;
    }

    public OpenOffiiceConnectionPool(String host) {
        this.host = host;
        this.port = DEFAULT_PORT;
    }

    public OpenOffiiceConnectionPool(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public synchronized void createPool() throws Exception {
        // 确保连接池没有创建
        // 如果连接池己经创建了，保存连接的向量 connections 不会为空
        if (connections != null) {
            return; // 如果己经创建，则返回
        }
        // 创建保存连接的向量 , 初始时有 0 个元素
        connections = new Vector();
        // 根据 initialConnections 中设置的值，创建连接。
        createConnections(this.initialConnections);
        System.out.println(" 连接池创建成功！ ");
    }
    /**
     * 创建由 numConnections 指定数目的连接 , 并把这些连接
     * 放入 connections 向量中
     * @param numConnections
     *            要创建的连接的数目
     */
    private void createConnections(int numConnections){
// 循环创建指定数目的连接
        for (int x = 0; x < numConnections; x++) {
            // 是否连接池中的连接的数量己经达到最大？最大值由类成员 maxConnections
            // 指出，如果 maxConnections 为 0 或负数，表示连接数量没有限制。
            // 如果连接数己经达到最大，即退出。
            if (this.maxConnections > 0  && this.connections.size() >= this.maxConnections) {
                break;
            }
            try {
                connections.add(new PooledConnection(newConnection()));
            } catch (ConnectException e) {
                e.printStackTrace();
            }
            // add a new PooledConnection object to connections vector
            // 增加一个连接到连接池中（向量 connections 中）

            System.out.println(" 连接己创建 ......");
        }
    }

    /**
     * 创建单个链接
     * @return
     */
    private OpenOfficeConnection newConnection() throws ConnectException {
        SocketOpenOfficeConnection socketOpenOfficeConnection = new SocketOpenOfficeConnection(this.host,this.port);
        socketOpenOfficeConnection.connect();
        return socketOpenOfficeConnection;
    }

    /**
     * 通过调用 getFreeConnection() 函数返回一个可用的连接 ,
     * 如果当前没有可用的连接，并且更多的连接不能创建（如连接池大小的限制），此函数等待一会再尝试获取。
     * @return 返回一个可用的连接对象
     */
    public synchronized OpenOfficeConnection getConnection() throws InterruptedException {
        // 确保连接池己被创建
        if (connections == null) {
            return null; // 连接池还没创建，则返回 null
        }
        OpenOfficeConnection conn = getFreeConnection(); // 获得一个可用的连接
        // 如果目前没有可以使用的连接，即所有的连接都在使用中
        while (conn == null) {
            // 等一会再试
            wait(250);
            conn = getFreeConnection(); // 重新再试，直到获得可用的连接，如果
            // getFreeConnection() 返回的为 null
            // 则表明创建一批连接后也不可获得可用连接
        }
        return conn;// 返回获得的可用的连接
    }

    /**
     * 本函数从连接池向量 connections 中返回一个可用的的连接，如果
     * 当前没有可用的连接，本函数则根据 incrementalConnections 设置
     * 的值创建几个连接，并放入连接池中。
     * 如果创建后，所有的连接仍都在使用中，则返回 null
     * @return 返回一个可用的连接
     */
    private OpenOfficeConnection getFreeConnection()  {
        // 从连接池中获得一个可用的连接
        OpenOfficeConnection conn = findFreeConnection();
        if (conn == null) {
            // 如果目前连接池中没有可用的连接
            // 创建一些连接
            createConnections(incrementalConnections);
            // 重新从池中查找是否有可用连接
            conn = findFreeConnection();
            if (conn == null) {
                // 如果创建连接后仍获得不到可用的连接，则返回 null
                return null;
            }
        }
        return conn;
    }

    /**
     * 查找连接池中所有的连接，查找一个可用的连接，
     * 如果没有可用的连接，返回 null
     * @return 返回一个可用的连接
     */
    private OpenOfficeConnection findFreeConnection()  {
        OpenOfficeConnection conn = null;
        PooledConnection pConn = null;
        // 获得连接池向量中所有的对象
        Enumeration enumerate = connections.elements();
        // 遍历所有的对象，看是否有可用的连接
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            if (!pConn.isBusy()) {
                // 如果此对象不忙，则获得它的连接并把它设为忙
                conn = pConn.getConnection();
                pConn.setBusy(true);
                // 测试此连接是否可用
                if (!conn.isConnected()) {
                    // 如果此连接不可再用了，则创建一个新的连接，
                    // 并替换此不可用的连接对象，如果创建失败，返回 null
                    try {
                        conn = newConnection();
                    } catch (ConnectException e) {
                        System.out.println(" 创建连接失败！ " + e.getMessage());
                        return null;
                    }
                    pConn.setConnection(conn);
                }
                break; // 己经找到一个可用的连接，退出
            }
        }
        return conn;// 返回找到到的可用连接
    }

    /**
     * 此函数返回一个连接到连接池中，并把此连接置为空闲。
     * 所有使用连接池获得的连接均应在不使用此连接时返回它。
     * @param conn 需返回到连接池中的连接对象
     */
    public void returnConnection(OpenOfficeConnection conn) {
        // 确保连接池存在，如果连接没有创建（不存在），直接返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration enumerate = connections.elements();
        // 遍历连接池中的所有连接，找到这个要返回的连接对象
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            // 先找到连接池中的要返回的连接对象
            if (conn == pConn.getConnection()) {
                // 找到了 , 设置此连接为空闲状态
                pConn.setBusy(false);
                break;
            }
        }
    }

    /**
     * 关闭连接池中所有的连接，并清空连接池。
     */
    public synchronized void closeConnectionPool()  {
        // 确保连接池存在，如果不存在，返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法关闭 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration enumerate = connections.elements();
        while (enumerate.hasMoreElements()) {
            pConn = (PooledConnection) enumerate.nextElement();
            // 如果忙，等 5 秒
            if (pConn.isBusy()) {
                wait(5000); // 等 5 秒
            }
            // 5 秒后直接关闭它
            closeConnection(pConn.getConnection());
        }
        // 置连接池为空
        connections = null;
    }

    /**
     * 关闭一个连接
     * @param  conn 需要关闭的连接
     */
    private void closeConnection(OpenOfficeConnection conn) {
        conn.disconnect();
    }

    /**
     * 使程序等待给定的毫秒数
     * @param  mSeconds 给定的毫秒数
     */

    private void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

/**
 * 内部使用的用于保存连接池中连接对象的类
 * 此类中有两个成员，一个是的连接，另一个是指示此连接是否
 * 正在使用的标志。
 */
class PooledConnection {
    OpenOfficeConnection connection = null;// 连接
    boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用
    // 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
    public PooledConnection(OpenOfficeConnection connection){
        this.connection = connection;
    }
    // 返回此对象中的连接
    public OpenOfficeConnection getConnection() {
        return connection;
    }
    // 设置此对象的，连接
    public void setConnection(OpenOfficeConnection connection) {
        this.connection = connection;
    }
    // 获得对象连接是否忙
    public boolean isBusy() {
        return busy;
    }
    // 设置对象的连接正在忙
    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
