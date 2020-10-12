import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// import tests.*;

public class TestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(CorrectnessTests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    
    result = JUnitCore.runClasses(PerformanceTests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}