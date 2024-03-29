/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:00:15 GMT 2018
 */

package system.controller.service;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import system.controller.service.GameArchiver;
import system.controller.service.GameService;
import system.controller.service.ResultService;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class GameArchiver_ESTest extends GameArchiver_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      GameArchiver gameArchiver0 = new GameArchiver();
      GameService gameService0 = mock(GameService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(gameService0).getFinishedGames();
      Injector.inject(gameArchiver0, (Class<?>) GameArchiver.class, "gameService", (Object) gameService0);
      ResultService resultService0 = mock(ResultService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameArchiver0, (Class<?>) GameArchiver.class, "resultService", (Object) resultService0);
      Injector.validateBean(gameArchiver0, (Class<?>) GameArchiver.class);
      gameArchiver0.archiveFinishedGames();
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      GameArchiver gameArchiver0 = new GameArchiver();
      GameService gameService0 = mock(GameService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameArchiver0, (Class<?>) GameArchiver.class, "gameService", (Object) gameService0);
      ResultService resultService0 = mock(ResultService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameArchiver0, (Class<?>) GameArchiver.class, "resultService", (Object) resultService0);
      Injector.validateBean(gameArchiver0, (Class<?>) GameArchiver.class);
      gameArchiver0.gameService = null;
      // Undeclared exception!
      try { 
        gameArchiver0.archiveFinishedGames();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.GameArchiver", e);
      }
  }
}
