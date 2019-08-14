package com.ex.study;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TestClass {

    /**
     * animals : {"dog":[{"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},{"name":"Marty","breed":"whippet","count":1,"twoFeet":false}],"cat":{"name":"Matilda"}}
     */

    private Animals animals;

    @NoArgsConstructor
    @Data
    public static class Animals {
        /**
         * dog : [{"name":"Rufus","breed":"labrador","count":1,"twoFeet":false},{"name":"Marty","breed":"whippet","count":1,"twoFeet":false}]
         * cat : {"name":"Matilda"}
         */

        private Cat cat;
        private List<Dog> dog;

        @NoArgsConstructor
        @Data
        public static class Cat {
            /**
             * name : Matilda
             */

            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class Dog {
            /**
             * name : Rufus
             * breed : labrador
             * count : 1
             * twoFeet : false
             */

            private String name;
            private String breed;
            private int count;
            private boolean twoFeet;
        }
    }
}
