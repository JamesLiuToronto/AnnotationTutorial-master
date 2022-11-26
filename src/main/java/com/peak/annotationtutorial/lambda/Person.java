package com.peak.annotationtutorial.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author James Liu
 * @date 11/23/2022 -- 1:17 PM
 */

@Data
@AllArgsConstructor
public class Person {
    String firstName;
    String lastName;
    int age ;

}
