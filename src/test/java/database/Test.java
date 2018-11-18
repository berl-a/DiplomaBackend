package database;

import org.springframework.stereotype.Component;

@Component
public class Test {

    @org.junit.jupiter.api.Test
    public void testHashCode() {
        System.out.println("erer".hashCode());
    }

}