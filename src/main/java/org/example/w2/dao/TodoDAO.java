package org.example.w2.dao;


import lombok.extern.log4j.Log4j2;
import org.example.w2.todo.TodoVO;

import java.util.List;
@Log4j2
public enum TodoDAO {//싱글톤으로 하고싶다.
    INSTANCE;

    TodoDAO(){

    }

    //제일먼저 만드는것 = 타입의 리스트
    public List<TodoVO> list()
    {   //ArrayList = 클래스
        // List = 인터페이스, list가 좀더 추상적이다.
        log.info("Todo DAO");
        return null;
    }

}
