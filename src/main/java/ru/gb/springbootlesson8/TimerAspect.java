package ru.gb.springbootlesson8;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
    /*
    Создать аннотацию замера времени исполнения метода (Timer). Она может ставиться над методами или классами.
    Аннотация работает так: после исполнения метода (метода класса) с такой аннотацией, необходимо в лог записать следующее:
    className - methodName #(количество секунд выполнения)
     */

    @Pointcut("within(@ru.gb.springbootlesson8.Timer *)")
    public void beansMethod(){}

    @Pointcut("@annotation(ru.gb.springbootlesson8.Timer)")
    public void beansWithAnnotation(){}

    @Around("beansMethod() || beansWithAnnotation()")
    public Object timeMeasuring(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = proceedingJoinPoint.proceed();

            long finish = System.currentTimeMillis() - start;
            System.out.println("Class name: " + proceedingJoinPoint.getSignature().getDeclaringType() +
                    "\nMethod name: " + proceedingJoinPoint.getSignature().getName() +
                    "\nExecution time: " + finish + " ms");

            return result;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
