/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:11:10 GMT 2018
 */

package system.model.classes.questions;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import system.model.classes.questions.Question;
import system.model.classes.questions.QuestionType;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Question_ESTest extends Question_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Question question0 = new Question();
      question0.setTeacher("p*UJ|a*32.p`");
      String string0 = question0.getTeacher();
      assertEquals("p*UJ|a*32.p`", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question("", questionType0, "", "system.model.classes.questions.QuestionType", "", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "", "SOE}'D", "AyGe3^(a", "");
      String string0 = question0.getTeacher();
      assertEquals("", question0.getQuestionImage());
      assertEquals("", string0);
      assertEquals("", question0.getQuestionTitle());
      assertEquals("SOE}'D", question0.getSubcategory());
      assertEquals("AyGe3^(a", question0.getSubsubcategory());
      assertEquals("system.model.classes.questions.QuestionType", question0.getQuestionText());
      assertEquals("", question0.getId());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question("=ji1Z=<YT|z~5?", questionType0, "ye", "ye", "ye", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "=ji1Z=<YT|z~5?", "=ji1Z=<YT|z~5?", "ye", "ye");
      String string0 = question0.getSubsubcategory();
      assertEquals("=ji1Z=<YT|z~5?", question0.getId());
      assertEquals("ye", question0.getQuestionTitle());
      assertNotNull(string0);
      assertEquals("=ji1Z=<YT|z~5?", question0.getSubcategory());
      assertEquals("=ji1Z=<YT|z~5?", question0.getCategory());
      assertEquals("ye", question0.getQuestionText());
      assertEquals("ye", string0);
      assertEquals("ye", question0.getQuestionImage());
      assertEquals("ye", question0.getTeacher());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Question question0 = new Question();
      question0.setSubcategory("cz+|{X1yyT+t2");
      String string0 = question0.getSubcategory();
      assertEquals("cz+|{X1yyT+t2", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuestionType questionType0 = QuestionType.SINGLE_CHOICE;
      Question question0 = new Question("");
      question0.setQuestionType(questionType0);
      QuestionType questionType1 = question0.getQuestionType();
      assertEquals(QuestionType.SINGLE_CHOICE, questionType1);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question(questionType0, "^y/SP", "^y/SP", "^y/SP", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "", "", "", "");
      String string0 = question0.getQuestionTitle();
      assertEquals("^y/SP", string0);
      assertEquals("^y/SP", question0.getQuestionText());
      assertEquals("", question0.getTeacher());
      assertEquals("^y/SP", question0.getQuestionImage());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuestionType questionType0 = QuestionType.SINGLE_CHOICE;
      Question question0 = new Question("", questionType0, "", "", "", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "O*-,<<", "O*-,<<", "O*-,<<");
      String string0 = question0.getQuestionTitle();
      assertEquals("", question0.getQuestionImage());
      assertEquals("", question0.getId());
      assertEquals("", string0);
      assertEquals("", question0.getQuestionText());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question(questionType0, "^y/SP", "^y/SP", "^y/SP", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "", "", "", "");
      String string0 = question0.getQuestionText();
      assertEquals("^y/SP", question0.getQuestionTitle());
      assertEquals("^y/SP", string0);
      assertEquals("^y/SP", question0.getQuestionImage());
      assertEquals("", question0.getTeacher());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Question question0 = new Question();
      question0.setQuestionText("");
      String string0 = question0.getQuestionText();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuestionType questionType0 = QuestionType.MULTIPLE_CHOICE;
      Question question0 = new Question("", questionType0, "", "", "", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null);
      String string0 = question0.getQuestionImage();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuestionType questionType0 = QuestionType.SINGLE_CHOICE;
      Question question0 = new Question((String) null, questionType0, "x&p$=;6xMO+%g\"bb", "x&p$=;6xMO+%g\"bb", (String) null, (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, (String) null, "system.model.classes.questions.Question", "SINGLE_CHOICE");
      String string0 = question0.getId();
      assertEquals("x&p$=;6xMO+%g\"bb", question0.getQuestionTitle());
      assertEquals("SINGLE_CHOICE", question0.getSubsubcategory());
      assertEquals("system.model.classes.questions.Question", question0.getSubcategory());
      assertNull(string0);
      assertEquals("x&p$=;6xMO+%g\"bb", question0.getQuestionText());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Question question0 = new Question("");
      String string0 = question0.getId();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      QuestionType questionType0 = QuestionType.MULTIPLE_CHOICE;
      Question question0 = new Question(questionType0, "MULTIPLE_CHOICE", "^BE", "^BE", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "MULTIPLE_CHOICE", "MULTIPLE_CHOICE", "E=y.g");
      String string0 = question0.getCategory();
      assertEquals("E=y.g", question0.getSubsubcategory());
      assertEquals("MULTIPLE_CHOICE", question0.getQuestionTitle());
      assertEquals("MULTIPLE_CHOICE", question0.getSubcategory());
      assertEquals("^BE", question0.getQuestionText());
      assertEquals("MULTIPLE_CHOICE", string0);
      assertEquals("^BE", question0.getQuestionImage());
      assertNotNull(string0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Question question0 = null;
      try {
        question0 = new Question((Question) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.model.classes.questions.Question", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Question question0 = new Question();
      question0.removeEmptyGroups();
      assertNull(question0.getSubsubcategory());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getSubsubcategory();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Question question0 = new Question((String) null);
      question0.setCategory((String) null);
      assertNull(question0.getQuestionText());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getQuestionText();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getSubcategory();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getQuestionImage();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Question question0 = new Question();
      question0.setId("^y/SP");
      assertNull(question0.getQuestionImage());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Question question0 = new Question((String) null);
      question0.setQuestionTitle((String) null);
      assertNull(question0.getTeacher());
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getCategory();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Question question0 = new Question();
      question0.setCorrectAnswers((LinkedList<Boolean>) null);
      assertNull(question0.getQuestionText());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Question question0 = new Question();
      LinkedList<String> linkedList0 = question0.getAnswerImages();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Question question0 = new Question();
      LinkedList<Boolean> linkedList0 = question0.getCorrectAnswers();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Question question0 = new Question();
      question0.setQuestionImage("^y/SP");
      assertNull(question0.getQuestionText());
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      Question question0 = new Question();
      LinkedList<String> linkedList0 = question0.getAnswerTexts();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question(questionType0, "^y/SP", "^y/SP", "^y/SP", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null, "", "", "", "");
      String string0 = question0.getQuestionImage();
      assertEquals("^y/SP", question0.getQuestionTitle());
      assertEquals("", question0.getTeacher());
      assertEquals("^y/SP", question0.getQuestionText());
      assertEquals("^y/SP", string0);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      Question question0 = new Question();
      question0.setAnswerImages((LinkedList<String>) null);
      assertNull(question0.getTeacher());
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      QuestionType questionType0 = QuestionType.FREE_TEXT;
      Question question0 = new Question(questionType0, "", "SINGLE_CHOICE", "", (LinkedList<String>) null, (LinkedList<String>) null, (LinkedList<Boolean>) null);
      assertEquals("", question0.getQuestionText());
      assertEquals("", question0.getQuestionImage());
      assertEquals("SINGLE_CHOICE", question0.getQuestionTitle());
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getQuestionTitle();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      Question question0 = new Question();
      question0.getQuestionType();
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getTeacher();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.getId();
      assertEquals("00000000-0100-4000-8200-000003000000", string0);
  }

  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      Question question0 = new Question();
      question0.setSubsubcategory("");
      assertNull(question0.getQuestionImage());
  }

  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      Question question0 = new Question();
      String string0 = question0.changeId();
      assertEquals("04000000-0500-4000-8600-000007000000", string0);
  }

  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      Question question0 = new Question();
      question0.setAnswerTexts((LinkedList<String>) null);
      assertNull(question0.getSubcategory());
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      Question question0 = new Question();
      Question question1 = new Question(question0);
      assertNull(question1.getQuestionText());
  }
}
