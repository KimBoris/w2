package org.example.w2.todo;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoVO {

    private int tno;
    private String title;
    private String writer;
    private Timestamp regdate;
    private Timestamp moddate;
    private boolean delflag;


}
