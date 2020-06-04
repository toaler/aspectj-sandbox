package aspectj.sandbox;

import java.util.Arrays;

public class HelloWorld {

	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.bar();
	}

	public static class Foo {

		@Timed
		public void bar() {
			System.out.println("Foo Bar");
			Arrays.stream(Thread.currentThread().getStackTrace()).forEach(f -> System.out.println(f));
		}
	}

}
