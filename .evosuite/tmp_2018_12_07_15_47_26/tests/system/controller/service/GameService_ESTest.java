/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 14:54:36 GMT 2018
 */

package system.controller.service;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import system.controller.service.GameService;
import system.controller.service.PlayerService;
import system.controller.service.QuestionGroupService;
import system.controller.service.QuestionService;
import system.controller.service.QuizService;
import system.controller.simple_frontend_models.GameWithActualQuiz;
import system.model.classes.games.Answer;
import system.model.classes.quizzes.Quiz;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class GameService_ESTest extends GameService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      // Undeclared exception!
      try { 
        gameService0.getRealQuestionsForPlayer("#3tRKX81Hfkq?ZZU+8+", "ydan");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.GameService", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      Quiz quiz0 = mock(Quiz.class, new ViolatedAssumptionAnswer());
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn(quiz0).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.addGameFromQuiz("ok");
      gameService0.getAll();
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn((Quiz) null).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      GameWithActualQuiz gameWithActualQuiz0 = gameService0.addGameFromQuiz(" ~!");
      // Undeclared exception!
      try { 
        gameService0.set((String) null, gameWithActualQuiz0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn((Quiz) null).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.addGameFromQuiz("ok");
      // Undeclared exception!
      try { 
        gameService0.remove((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      // Undeclared exception!
      try { 
        gameService0.getQuestionsForPlayer("", ":Zr>%2Z8TwX+.{]");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.GameService", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn((Quiz) null).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.addGameFromQuiz("");
      // Undeclared exception!
      try { 
        gameService0.getByCode((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn((Quiz) null).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.addGameFromQuiz("5lfAr[*S");
      // Undeclared exception!
      try { 
        gameService0.get((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      Answer answer0 = mock(Answer.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        gameService0.answerQuestion("C;[i?. `", "jt/n+ZP6nNC", "", answer0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.GameService", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.get("");
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.getAll();
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.getWithQuiz("");
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.getByCode((String) null);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.remove("ok");
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.getFinishedGames();
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.removeByCode("ajyxm7d>!u\"/^&");
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      Quiz quiz0 = mock(Quiz.class, new ViolatedAssumptionAnswer());
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      doReturn((Quiz) null).when(quizService0).get(anyString());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      GameWithActualQuiz gameWithActualQuiz0 = gameService0.addGameFromQuiz("ABCDEFHIKLMNPQSTXYZ");
      // Undeclared exception!
      try { 
        gameService0.set("ABCDEFHIKLMNPQSTXYZ", gameWithActualQuiz0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: -1, Size: 1
         //
         verifyException("java.util.LinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      // Undeclared exception!
      try { 
        gameService0.join("", "rP#yqQu");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.GameService", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.startGame("ok");
      assertEquals(4, GameService.CODE_LENGTH);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      GameService gameService0 = new GameService();
      PlayerService playerService0 = mock(PlayerService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "playerService", (Object) playerService0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionGroupService", (Object) questionGroupService0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "questionService", (Object) questionService0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(gameService0, (Class<?>) GameService.class, "quizService", (Object) quizService0);
      Injector.validateBean(gameService0, (Class<?>) GameService.class);
      gameService0.removeFinishedGames();
      assertEquals(4, GameService.CODE_LENGTH);
  }
}
