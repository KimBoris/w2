package org.example.w2.dao;


import lombok.Builder;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.example.w2.common.ConnectionUtil;
import org.example.w2.todo.TodoVO;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum TodoDAO {//싱글톤으로 하고싶다.
    INSTANCE;

    TodoDAO() {
        //Step2-1 : 등록을 할 거다
        //return값을 뭘 줄거니
        //DB에 등록된 순 - Vue, React에서 하는게 편리할거다.
        //등록된 번호 -
    }

    public Integer insert(final TodoVO todoVO) throws Exception {

        String sql = "insert into tbl_todo (title, writer) values (?,?)";
        log.info("Inserting todoVO: " + todoVO);
        //등록이 된다음 몇번으로 등록되었는지 알고싶다


        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        con.setAutoCommit(false);
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, todoVO.getTitle());
        ps.setString(2, todoVO.getWriter());

        //카운트가 정상적인 케이스 = 1
        // 1이 아니라면? 잘못됐다.
        int count = ps.executeUpdate();

        if (count != 1) {
            throw new Exception("Abnormal insertion");
        }

        ps.close();
        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        //auto Increment로 몇이라는 값으로 들어갔는지 알아야 한다.
        //트랜젝션

        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();
        Integer tno = rs.getInt(1);

        log.info("TNO ........" + tno);

        con.commit();
        con.setAutoCommit(true);

        return tno;
    }

    //제일먼저 만드는것 = 타입의 리스트
    public List<TodoVO> list(int page) throws Exception {   //ArrayList = 클래스
        // List = 인터페이스, list가 좀더 추상적이다.
        log.info("Todo DAO list");

        int skip = (page - 1) * 10;

        String query = """ 
                select
                    *
                from
                    tbl_todo
                where
                    delflag = false
                and
                    tno > 0
                order by
                    tno desc
                limit  ? , 10 
                    """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        // con.setAutoCommit(false);
        @Cleanup PreparedStatement ps = con.prepareStatement(query);


        ps.setInt(1, skip);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getInt("tno"))
                    .title(rs.getString("title"))
                    .writer(rs.getString("writer"))
                    .regdate(rs.getTimestamp("regdate"))
                    .moddate(rs.getTimestamp("moddate"))
                    .delflag(rs.getBoolean("delflag"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public int getTotal() throws Exception {
        log.info("GetTatal");

        String query = "select count(tno) from tbl_todo where tno > 0 and delflag = false";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();
        //select는 결과 집합 next를해야 원하는 값을 얻을 수 있다.

        //while(rs.next) 가 필요가 없다.
        // 위 커리문을 실행시키면 1개의 항목만 나오기때문에
        rs.next();

        int total = rs.getInt(1);

        return total;
    }

    public Optional<TodoVO> get(Integer tno) throws Exception {

        //변하지 않는 것이기에 상수로 뺴준다.
        //select를 날리면 가래떡이 온다.
        final String query = """
                select
                    tno,title,writer,regdate,moddate,delflag
                from
                    tbl_todo
                where tno = ?
                """;
        //추상클래스를 잘 활용하여 Connection 처리
        @Cleanup Connection on = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = on.prepareStatement(query);
        ps.setInt(1, tno);

        //탄자니아 어를 보내자
        @Cleanup ResultSet rs = ps.executeQuery();

        //맨앞의 메타데이터는 자르고 1부터
        if (!rs.next()) {
            //빈 데이터를 준다.
            //Optional = 안전한 예외처리
            return Optional.empty();
        }
        // rs.next()를 넘어오면 TodoVO를 만든다.


        TodoVO vo = TodoVO.builder()
                .tno(rs.getInt("tno"))
                .title(rs.getString("title"))
                .writer(rs.getString("writer"))
                .regdate(rs.getTimestamp("regdate"))
                .moddate(rs.getTimestamp("moddate"))
                .delflag(rs.getBoolean("delflag"))
                .build();


        //next를 한 번 하긴 해야한다.

        return Optional.of(vo);
    }


    public boolean delete(Integer tno) throws Exception {
        String sql = "update tbl_todo set moddate = now(), delflag=true where tno = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, tno);
        int count = ps.executeUpdate();

        return count == 1 ? true : false;
    }

    public boolean update(TodoVO todoVO) throws Exception {
        //여러개가 필요하기에 TodoVo를 받는것이 맞다.
        //데이터베이스에 맞는 VO를 만드는 것 부터 시작
        String sql = """
                update tbl_todo
                set title =?,
                 writer = ?,
                  moddate = now()
                where tno = ?
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, todoVO.getTitle());
        ps.setString(2, todoVO.getWriter());
        ps.setInt(3, todoVO.getTno());

        int count = ps.executeUpdate();

        return count == 1 ? true : false;
    }


}
