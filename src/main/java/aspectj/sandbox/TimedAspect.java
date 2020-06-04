package aspectj.sandbox;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TimedAspect {

	@Pointcut("@annotation(Timed)")
	public void timedPointCutDefinition() {

	}

	@Pointcut("execution(* *(..))")
	public void atExeuction() {
	}

    @Around("@annotation(Timed) && execution(* *(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		// Default Object that we can use to return to the consumer
		Object returnObject = null;
		try {
			System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
			// We choose to continue the call to the method in question
			returnObject = joinPoint.proceed();
			// If no exception is thrown we should land here and we can modify the
			// returnObject, if we want to.
		} catch (Throwable throwable) {
			// Here we can catch and modify any exceptions that are called
			// We could potentially not throw the exception to the caller and instead return
			// "null" or a default object.
			throw throwable;
		} finally {
			// If we want to be sure that some of our code is executed even if we get an
			// exception
			System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
		}
		return returnObject;
	}

	@After("timedPointCutDefinition() && atExeuction()")
	// JointPoint = the reference of the call to the method
	public void printNewLine(JoinPoint pointcut) {
		// Just prints new lines after each method that's executed in
		System.out.print("After\n\r");
	}

}
