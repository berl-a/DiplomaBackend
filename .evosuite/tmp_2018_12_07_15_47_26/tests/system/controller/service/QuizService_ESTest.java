/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:32:07 GMT 2018
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
import system.controller.service.QuestionGroupService;
import system.controller.service.QuizGroupService;
import system.controller.service.QuizPartService;
import system.controller.service.QuizService;
import system.model.classes.quizzes.Quiz;
import system.model.dao.QuizDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizService_ESTest extends QuizService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      quizService0.dao = null;
      // Undeclared exception!
      try { 
        quizService0.updateCached();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.removeQuizzesFromGroup("X'ie");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      quizService0.dao = null;
      // Undeclared exception!
      try { 
        quizService0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.removeQuizzesFromGroup("but number of questions from this part: ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      LinkedList<Quiz> linkedList0 = quizService0.getAll();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizGroupService0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.getAllWithCatNames();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.removeQuizzesFromGroup("NW,{u8");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(quizDao0).getAll();
      doReturn((String) null).when(quizDao0).remove(anyString());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      quizService0.remove("");
      // Undeclared exception!
      try { 
        quizService0.removeQuizzesFromGroup("system.controller.service.QuizService");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.copy("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.getAllWithCatNamesByTeacher("C8B/e<fIGsXvC7PkC5");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuizService quizService0 = new QuizService();
      QuizDao quizDao0 = mock(QuizDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(quizDao0).getAll();
      Injector.inject(quizService0, (Class<?>) QuizService.class, "dao", (Object) quizDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "questionGroupService", (Object) questionGroupService0);
      QuizGroupService quizGroupService0 = mock(QuizGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizGroupService", (Object) quizGroupService0);
      QuizPartService quizPartService0 = mock(QuizPartService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizService0, (Class<?>) QuizService.class, "quizPartService", (Object) quizPartService0);
      Injector.validateBean(quizService0, (Class<?>) QuizService.class);
      // Undeclared exception!
      try { 
        quizService0.get("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuizService", e);
      }
  }
}
