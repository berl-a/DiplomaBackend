/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:04:49 GMT 2018
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
import system.controller.service.QuestionService;
import system.model.classes.questions.QuestionGroup;
import system.model.dao.QuestionGroupDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuestionGroupService_ESTest extends QuestionGroupService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getNumberOfQuestions((String) null, (String) null, "gOs");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getAll("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getQuestionsFromGroups("+^3:h7 '6C8]0", "+^3:h7 '6C8]0", "");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getNumberOfQuestions("", "", "uA-%pJQ?l");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      LinkedList<QuestionGroup> linkedList0 = questionGroupService0.getAll();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      questionGroupService0.dao = null;
      // Undeclared exception!
      try { 
        questionGroupService0.remove("Og#");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getQuestionsFromGroupsFree("", "", "");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getAllWithNumberOfQuestions((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      questionGroupService0.dao = null;
      // Undeclared exception!
      try { 
        questionGroupService0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.edit((QuestionGroup) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getAll(" of them");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getQuestionsFromGroupsFree((String) null, (String) null, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      questionGroupService0.updateCached();
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.get("$8");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getQuestionsFromGroupsFree("AEM\"tt3tJ", (String) null, "AEM\"tt3tJ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getQuestionsFromGroups("Y9S|@)v9t@h-", (String) null, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      doReturn((String) null).when(questionGroupDao0).remove(anyString());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      String string0 = questionGroupService0.remove("Og#");
      assertEquals("ok", string0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getAll("ONEUm8_]Kw$gpw-O");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupDao0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getAllWithNumberOfQuestions();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      QuestionGroupService questionGroupService0 = new QuestionGroupService();
      QuestionGroupDao questionGroupDao0 = mock(QuestionGroupDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "dao", (Object) questionGroupDao0);
      QuestionService questionService0 = mock(QuestionService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionService0).getAll();
      Injector.inject(questionGroupService0, (Class<?>) QuestionGroupService.class, "questionService", (Object) questionService0);
      Injector.validateBean(questionGroupService0, (Class<?>) QuestionGroupService.class);
      // Undeclared exception!
      try { 
        questionGroupService0.getNumberOfQuestions((String) null, "ok", (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionGroupService", e);
      }
  }
}
