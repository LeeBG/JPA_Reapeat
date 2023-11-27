package org.example;

public class Main {
    public static void main(String[] args) {
        /* JPQL
            -가장 단순한 조회 방법
            - JPA를 사용하면 엔티티 객체를 중심으로 개발
            - 문제는 검색 쿼리
            - 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            - 모든 DB데이터를 객체로 변환해서 검색하는 것은 불가능
            - 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색
            조건이 포함된 SQL이 필요

            - JPQ는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
            - SQL과 문법 유사, SELECT ,FROM, WHERE GROUP BY


            JPQL -> 객체 지향 SQL
            - 테이블이 아닌 객체를 대상으로 방언같은 것들을 풀어서 잘 처리해준다.

            ```
            // 문자열로 쿼리를 만들면 동적 쿼리를 처리하기가 빡세다.
                    String qlString = "select m From Member as m";
            String username = "";
            if(username != null) {
                String where = "where m.username like '%kim%'";
                qlString += where;
            }

            // 동적 쿼리를


            // 여기서
            List<Member> result = em.createQuery("select m from Member m where m.username like '%kim%'",Member.class)
                    .getResultList();

            System.out.println("result : " + result);
            ```

            Criteria 소개



         */
    }
}