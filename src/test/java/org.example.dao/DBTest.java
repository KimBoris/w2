package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public class DBTest {

    @Test
    public void testInsert() throws Exception {
        String sql = "insert into tbl_todo (title,writer)" +
                "                  values (?,?)";
        /*따옴표 = 값, 값은 물음표로 남겨라*/
        String url = "jdbc:mariadb://49.174.76.109:13306/webdb";
        String username = "webdbuser";
        String password = "webdbuser";

        Class.forName("org.mariadb.jdbc.Driver");


        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "해야 하는 일");
            ps.setString(2, "title");

            int count = ps.executeUpdate();


            log.info("COunt = " + count);

            //테스트의 정석 count = 1이어야 통과
            Assertions.assertEquals(count, 1);

        } catch (
                Exception e) {
            log.error(e);
        }
    }

//    @Test
//    public void testSelect() throws Exception {
//    /*    String url = "jdbc:mariadb://localhost:13306/webdb";
//        String username = "webdbuser";
//        String password = "webdbuser";*/
//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("org.mariadb.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mariadb://http://49.174.76.109:13306/webdb");
//        config.setUsername("webdbuser");
//        config.setPassword("webdbuser");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//
//        HikariDataSource ds = new HikariDataSource(config);
//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < 100; i++) {
//            @Cleanup Connection con = ds.getConnection();
//            String sql = "select * from tbl_todo where tno > 0 order by tno desc limit 0,10";
//
//            @Cleanup PreparedStatement ps = con.prepareStatement(sql);
//            @Cleanup ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                //이미 읽어온 상태
//                //뭐부터 읽을지는 중요하지 않다.
///*            log.info(rs.getInt(1)); //tno
//            log.info(rs.getString(2));//title
//            log.info(rs.getString(3));
//            log.info(rs.getTimestamp(4));
//            log.info(rs.getTimestamp(5));
//            log.info(rs.getBoolean(6));*/
//
//
//            }
//        }
//
//        /*    Class.forName("org.mariadb.jdbc.Driver");*/
//
//        // String sql = "select * from tbl_todo where tno>0 order by tno desc limit 0, 100/*limit 0, 100*/";
//
//        //  long start = System.currentTimeMillis();
//
//
//        // @Cleanup Connection conn = DriverManager.getConnection(url, username, password);
//        //  @Cleanup PreparedStatement ps = conn.prepareStatement(sql);
//        // @Cleanup ResultSet rs = ps.executeQuery();
//
//
//        long end = System.currentTimeMillis();
//
//        log.info("-------------------------------------------");
//        log.info(end - start);
//    }

    @Test
    public void testSelectOLD() throws Exception {

        String url = "jdbc:mariadb://49.174.76.109.:13306/webdb";
        String username = "webdbuser";
        String password = "webdbuser";

        Class.forName("org.mariadb.jdbc.Driver");

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {

            @Cleanup Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from tbl_todo where tno > 0 order by tno desc limit 0,10";
            @Cleanup PreparedStatement ps = con.prepareStatement(sql);
            @Cleanup ResultSet rs = ps.executeQuery();

            while (rs.next()) {
// log.info(rs.getInt(1)); //tno
// log.info(rs.getString(2)); //title
// log.info(rs.getString(3)); //writer
// log.info(rs.getTimestamp(4));
// log.info(rs.getTimestamp(5));
// log.info(rs.getBoolean(6));
            }//end while
        }

        long end = System.currentTimeMillis();
        log.info("----------------------------------------");
        log.info(end - start);
    }


    @Test
    public void testSelect()throws Exception {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://49.174.76.109/:13306/webdb");
        config.setUsername("webdbuser");
        config.setPassword("webdbuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(1000*10);
        config.setMaximumPoolSize(100);
        config.setMinimumIdle(1);
        HikariDataSource ds = new HikariDataSource(config);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {

            //CleanUp = close를 호출한다.
            //데코레이터 패턴 중 하나.
            //횡령 사고 : 커넥션 풀이
            @Cleanup Connection con = ds.getConnection();
            String sql ="select * from tbl_todo where tno > 0 order by tno desc limit 0,10";
            @Cleanup PreparedStatement ps = con.prepareStatement(sql);
            @Cleanup ResultSet rs = ps.executeQuery();

            while (rs.next()) {
// log.info(rs.getInt(1)); //tno
// log.info(rs.getString(2)); //title
// log.info(rs.getString(3)); //writer
// log.info(rs.getTimestamp(4));
// log.info(rs.getTimestamp(5));
// log.info(rs.getBoolean(6));
            }//end while
        }

        long end = System.currentTimeMillis();
        log.info("----------------------------------------");
        log.info(end - start);
    }
}
