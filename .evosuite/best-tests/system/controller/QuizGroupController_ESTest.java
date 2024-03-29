/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 14:52:40 GMT 2018
 */

package system.controller;

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
import system.controller.QuizGroupController;
import system.controller.service.QuizGroupService;
import system.controller.simple_frontend_models.Response;
import system.model.classes.quizzes.QuizGroup;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizGroupController_ESTest extends QuizGroupController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("system.controller.QuizGroupController").when(quizGroupService0).rename(anyString() , anyString());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      // Undeclared exception!
      try { 
        quizGroupController0.rename((String) null, "~j=)J4nMh");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      // Undeclared exception!
      try { 
        quizGroupController0.remove("");
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("ok", (String) null).when(quizGroupService0).remove(anyString());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      quizGroupController0.remove("TRACE");
      // Undeclared exception!
      try { 
        quizGroupController0.remove("Fd:-(?'Dc#^");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(quizGroupService0).edit(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        quizGroupController0.edit(quizGroup0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(quizGroupService0).add(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        quizGroupController0.add(quizGroup0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupService0).getAll();
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      Response response0 = quizGroupController0.getAll();
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("ok").when(quizGroupService0).rename(anyString() , anyString());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      Response response0 = quizGroupController0.rename("]TR?,#S($sTp", "]TR?,#S($sTp");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("nb").when(quizGroupService0).rename(anyString() , anyString());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      Response response0 = quizGroupController0.rename("Xrrg2N%/rbd+AoD", "tTRACE");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("ok").when(quizGroupService0).edit(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      Response response0 = quizGroupController0.edit(quizGroup0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("TRACE").when(quizGroupService0).edit(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      Response response0 = quizGroupController0.edit(quizGroup0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn(">").when(quizGroupService0).remove(anyString());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      Response response0 = quizGroupController0.remove("system.controller.service.QuestionGroupService");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("ok").when(quizGroupService0).add(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      Response response0 = quizGroupController0.add(quizGroup0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn("nok").when(quizGroupService0).add(any(system.model.classes.quizzes.QuizGroup.class));
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      Response response0 = quizGroupController0.add(quizGroup0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      QuizGroupController quizGroupController0 = new QuizGroupController();
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupController0, (Class<?>) QuizGroupController.class, "service", (Object) quizGroupService0);
      Injector.validateBean(quizGroupController0, (Class<?>) QuizGroupController.class);
      quizGroupController0.service = null;
      // Undeclared exception!
      try { 
        quizGroupController0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.QuizGroupController", e);
      }
  }
}
