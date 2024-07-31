package org.example.dao;


import lombok.extern.log4j.Log4j2;
import org.example.w2.bmi.Scores;
import org.junit.jupiter.api.Test;

@Log4j2
public class ScoresTests {

    @Test
    public void testBuilder() {
        Scores data1 = Scores.builder().subject1(89).subject5(44).build();

        Scores data2 = Scores.builder().subject1(100).subject2(90).subject3(29).subject4(30).build();
        log.info(data1);
        log.info("Data2 = "+ data2);
    }
}
