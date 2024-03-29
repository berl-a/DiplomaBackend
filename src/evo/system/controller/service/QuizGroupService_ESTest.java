/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 14:49:49 GMT 2018
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
import system.controller.service.QuizGroupService;
import system.controller.service.QuizService;
import system.model.classes.quizzes.QuizGroup;
import system.model.dao.QuizGroupDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizGroupService_ESTest extends QuizGroupService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      QuizGroup quizGroup0 = mock(QuizGroup.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(quizGroup0).getId();
      String string0 = quizGroupService0.add(quizGroup0);
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      quizGroupService0.dao = null;
      // Undeclared exception!
      try { 
        quizGroupService0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      // Undeclared exception!
      try { 
        quizGroupService0.get("_IEd8");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      LinkedList<QuizGroup> linkedList0 = quizGroupService0.getAll();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      quizGroupService0.updateCached();
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      doReturn((String) null).when(quizGroupDao0).remove(anyString());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      String string0 = quizGroupService0.remove((String) null);
      assertEquals("ok", string0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      // Undeclared exception!
      try { 
        quizGroupService0.rename("", "SUBSUBCAT");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      QuizGroupService quizGroupService0 = new QuizGroupService();
      QuizGroupDao quizGroupDao0 = mock(QuizGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupDao0).getAll();
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "dao", (Object) quizGroupDao0);
      QuizService quizService0 = mock(QuizService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizGroupService0, (Class<?>) QuizGroupService.class, "quizService", (Object) quizService0);
      Injector.validateBean(quizGroupService0, (Class<?>) QuizGroupService.class);
      // Undeclared exception!
      try { 
        quizGroupService0.get("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizGroupService", e);
      }
  }
}
