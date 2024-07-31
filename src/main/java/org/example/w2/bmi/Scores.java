package org.example.w2.bmi;

import lombok.*;


@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Scores {

    private int subject1;
    private int subject2;
    private int subject3;
    private int subject4;
    private int subject5;


    public void close() {
        System.out.println("close______________________");
        System.out.println("close______________________");
        System.out.println("close______________________");
        System.out.println("close______________________");
    }


}
