/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 14:59:22 GMT 2018
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
import system.model.classes.questions.Question;
import system.model.dao.QuestionDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuestionService_ESTest extends QuestionService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      LinkedList<Question> linkedList0 = questionService0.getAll();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      // Undeclared exception!
      try { 
        questionService0.getAllWithCatNamesByTeacher("yTy");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      QuestionDao questionDao1 = questionService0.dao;
      questionService0.dao = questionDao1;
      questionService0.dao = null;
      // Undeclared exception!
      try { 
        questionService0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      questionService0.dao = null;
      // Undeclared exception!
      try { 
        questionService0.get("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      questionService0.updateCached();
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      // Undeclared exception!
      try { 
        questionService0.removeQuestionsFromGroup("system.model.dao.QuestionGroupDao");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(questionDao0).getAll();
      doReturn((String) null).when(questionDao0).remove(anyString());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      Question question0 = mock(Question.class, new ViolatedAssumptionAnswer());
      doReturn((String) null, (String) null).when(question0).getId();
      String string0 = questionService0.edit(question0);
      assertEquals("ok", string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionGroupService0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      // Undeclared exception!
      try { 
        questionService0.getAllWithCatNames();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      QuestionService questionService0 = new QuestionService();
      QuestionDao questionDao0 = mock(QuestionDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(questionDao0).getAll();
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "dao", (Object) questionDao0);
      QuestionGroupService questionGroupService0 = mock(QuestionGroupService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionService0, (Class<?>) QuestionService.class, "questionGroupService", (Object) questionGroupService0);
      Injector.validateBean(questionService0, (Class<?>) QuestionService.class);
      // Undeclared exception!
      try { 
        questionService0.get("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.QuestionService", e);
      }
  }
}