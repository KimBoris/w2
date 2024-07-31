package org.example.w2.member;

import lombok.Cleanup;
import org.example.w2.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum MemberDAO {
    INSTANCE;

    public Optional<MemberVO> get(String word, String pw) throws Exception {
        String query = """
                            select * from tbl_member
                          where 
                          (uid='u1' or email='u1') 
                        and 
                          upw ='1111' 
                        and
                          delflag = false
  """;

        //커넥션 생성
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        //물음표를 채운다.
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        //이제 가래떡 뽑으러 가자
        @Cleanup ResultSet rs = ps.executeQuery();
        //가래떡 앞부분 자라내야한다.
        if (!rs.next()) {//쿼리는 실행이 되었는데 값이 틀릴 수가 있다. 그러면 여기서 끊어야 한다.
            return Optional.empty();
            //옛날에는 null을 반환했지만 요즘은 Optinal을 반환한다.
        }
        //값을 뽑는다. 가럐떡 앞부분잘라내고 이동했다.
        MemberVO member = MemberVO.builder()
                .mno(rs.getInt("mno"))
                .uid(rs.getString("uid"))
                .upw(rs.getString("upw"))//패스워드를 가져오는 이유 > 패스워드를 변경하는 경우,3개월이 지나셨습니다. (가져오지만 노출은 안한다)
                .email(rs.getString("email"))
                .delflag(rs.getBoolean("delflag")).//이미 삭제된 사용자는 가지고 오면 안된다.
                        build();

        return Optional.of(member);
    }

}
