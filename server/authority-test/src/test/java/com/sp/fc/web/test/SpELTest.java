package com.sp.fc.web.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.jupiter.api.Assertions.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Person {

    private String name;

    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Horse {

    private String name;

    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}

class SpELTest {

    ExpressionParser parser = new SpelExpressionParser();

    Person person = Person.builder()
            .name("홍길동").height(178).build();

    Horse horse = Horse.builder()
            .name("nancy").height(160).build();

    @DisplayName("1. 기본 테스트")
    @Test
    void test_1() {

        assertEquals("홍길동", parser.parseExpression("name").getValue(person, String.class));
    }

    @DisplayName("2. 값 변경 테스트")
    @Test
    void test_2() {
        parser.parseExpression("name").setValue(person, "호나우드");
        assertEquals("호나우드", parser.parseExpression("name").getValue(person, String.class));
    }

    @DisplayName("3. 메소드 호출")
    @Test
    void test_3() {
        assertEquals(Boolean.TRUE, parser.parseExpression("over(170)").getValue(person, Boolean.class));
        assertNotEquals(Boolean.TRUE, parser.parseExpression("over(170)").getValue(horse, Boolean.class));
    }

    @DisplayName("4. Context 테스트")
    @Test
    void test_4() {
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setBeanResolver(new BeanResolver() {
            @Override
            public Object resolve(EvaluationContext context, String beanName) throws AccessException {
                return beanName.equals("person") ? person : horse;
            }
        });

        ctx.setRootObject(person);
        ctx.setVariable("nancy", horse);

        assertEquals(Boolean.TRUE, parser.parseExpression("over(170)").getValue(ctx, Boolean.class));
        assertNotEquals(Boolean.TRUE, parser.parseExpression("#nancy.over(170)").getValue(ctx, Boolean.class));
        assertEquals(Boolean.TRUE, parser.parseExpression("@person.over(170)").getValue(ctx, Boolean.class));
        assertNotEquals(Boolean.TRUE, parser.parseExpression("@horse.over(170)").getValue(ctx, Boolean.class));
    }
}
