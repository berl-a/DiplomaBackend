/*
 * This file was automatically generated by EvoSuite
 * Mon Dec 03 16:39:49 GMT 2018
 */

package system.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import system.controller.QuizPartController;
import system.controller.service.QuizPartService;
import system.controller.simple_frontend_models.Response;
import system.model.quizzes.QuizPart;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizPartController_ESTest extends QuizPartController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      // Undeclared exception!
      try { 
        quizPartController0.remove("");
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("ok", (String) null).when(quizPartService0).remove(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      quizPartController0.remove("B;|_^/VJ!%B](L'");
      // Undeclared exception!
      try { 
        quizPartController0.remove("POST");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      quizPartController0.service = null;
      // Undeclared exception!
      try { 
        quizPartController0.get("=!3SKF%");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.QuizPartController", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(quizPartService0).edit(any(system.model.quizzes.QuizPart.class));
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        quizPartController0.edit(quizPart0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("GET", (String) null).when(quizPartService0).add(any(system.model.quizzes.QuizPart.class));
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      quizPartController0.add(quizPart0);
      QuizPart quizPart1 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        quizPartController0.add(quizPart1);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      // Undeclared exception!
      try { 
        quizPartController0.add("");
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(quizPartService0).copy(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      // Undeclared exception!
      try { 
        quizPartController0.add(":$");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizPartService0).getAll();
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.getAll();
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("ok").when(quizPartService0).edit(any(system.model.quizzes.QuizPart.class));
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      Response response0 = quizPartController0.edit(quizPart0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("nok").when(quizPartService0).edit(any(system.model.quizzes.QuizPart.class));
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      Response response0 = quizPartController0.edit(quizPart0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("HEAD").when(quizPartService0).remove(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.remove("qy4#Y=F@P|i{h");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("Q7TERG5vyUi\"y>").when(quizPartService0).copy(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.add("newId");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("nok").when(quizPartService0).copy(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.add("error");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn("nok").when(quizPartService0).add(any(system.model.quizzes.QuizPart.class));
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      Response response0 = quizPartController0.add(quizPart0);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPart quizPart0 = mock(QuizPart.class, new ViolatedAssumptionAnswer());
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn(quizPart0).when(quizPartService0).get(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.get("DELETE");
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn((QuizPart) null).when(quizPartService0).get(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.get((String) null);
      assertNotNull(response0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      QuizPartController quizPartController0 = new QuizPartController();
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      doReturn((List) null).when(quizPartService0).getAllWithCatNamesFromQuiz(anyString());
      Injector.inject(quizPartController0, (Class<?>) QuizPartController.class, "service", (Object) quizPartService0);
      Injector.validateBean(quizPartController0, (Class<?>) QuizPartController.class);
      Response response0 = quizPartController0.getAllWithCatNames("error");
      assertNotNull(response0);
  }
}
