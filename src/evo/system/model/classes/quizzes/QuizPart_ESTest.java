/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:03:51 GMT 2018
 */

package system.model.classes.quizzes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import system.model.classes.quizzes.QuizPart;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizPart_ESTest extends QuizPart_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("system.model.classes.quizzes.QuizPart", "system.model.classes.quizzes.QuizPart", "system.model.classes.quizzes.QuizPart", 219, 219, "%_v{X>o'|8", "system.model.classes.quizzes.QuizPart", (String) null);
      String string0 = quizPart0.getSubsubcategory();
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getSubcategory());
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getQuiz());
      assertEquals(219, quizPart0.getOrder());
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getName());
      assertNull(string0);
      assertEquals("%_v{X>o'|8", quizPart0.getCategory());
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getId());
      assertEquals(219, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", (-322), (-1), "", "", "");
      quizPart0.getSubsubcategory();
      assertEquals((-322), quizPart0.getOrder());
      assertEquals((-1), quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart();
      String string0 = quizPart0.getSubcategory();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", "", 0, 0, "", "", "`R! NXy(6!6v%");
      String string0 = quizPart0.getSubcategory();
      assertEquals("", string0);
      assertEquals("`R! NXy(6!6v%", quizPart0.getSubsubcategory());
      assertEquals("", quizPart0.getQuiz());
      assertEquals("", quizPart0.getName());
      assertEquals("", quizPart0.getCategory());
      assertEquals(0, quizPart0.getNumber());
      assertEquals("", quizPart0.getId());
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart((String) null, (String) null, (-4006), 0, (String) null, (String) null, "system.model.classes.quizzes.QuizPart");
      String string0 = quizPart0.getQuiz();
      assertEquals(0, quizPart0.getNumber());
      assertNull(string0);
      assertEquals((-4006), quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", "", 0, 0, "", "", "");
      quizPart0.getQuiz();
      assertEquals(0, quizPart0.getOrder());
      assertEquals(0, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("j\"],X", "j\"],X", "", 1, 1, (String) null, "", " ");
      int int0 = quizPart0.getOrder();
      assertEquals("", quizPart0.getQuiz());
      assertEquals(" ", quizPart0.getSubsubcategory());
      assertEquals(1, int0);
      assertEquals(1, quizPart0.getNumber());
      assertEquals("j\"],X", quizPart0.getName());
      assertEquals("j\"],X", quizPart0.getId());
      assertEquals("", quizPart0.getSubcategory());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "]B[UPYr)x", (-1), (-1), "", "", "]B[UPYr)x");
      int int0 = quizPart0.getOrder();
      assertEquals("]B[UPYr)x", quizPart0.getQuiz());
      assertEquals("", quizPart0.getSubcategory());
      assertEquals("", quizPart0.getCategory());
      assertEquals("", quizPart0.getName());
      assertEquals((-1), int0);
      assertEquals((-1), quizPart0.getNumber());
      assertEquals("]B[UPYr)x", quizPart0.getSubsubcategory());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "J?_;>}7q)d", 3598, 3598, "", "", "");
      int int0 = quizPart0.getNumber();
      assertEquals("", quizPart0.getSubcategory());
      assertEquals("", quizPart0.getSubsubcategory());
      assertEquals("", quizPart0.getName());
      assertEquals("J?_;>}7q)d", quizPart0.getQuiz());
      assertEquals(3598, quizPart0.getOrder());
      assertEquals(3598, int0);
      assertEquals("", quizPart0.getCategory());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", "", (-1365), (-1), "", "sU}!", "");
      int int0 = quizPart0.getNumber();
      assertEquals("", quizPart0.getSubsubcategory());
      assertEquals("", quizPart0.getName());
      assertEquals("", quizPart0.getQuiz());
      assertEquals("", quizPart0.getCategory());
      assertEquals((-1), int0);
      assertEquals("sU}!", quizPart0.getSubcategory());
      assertEquals((-1365), quizPart0.getOrder());
      assertEquals("", quizPart0.getId());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart();
      String string0 = quizPart0.getName();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", 2552, 0, "", "", "i +PM");
      String string0 = quizPart0.getName();
      assertEquals("", string0);
      assertEquals("i +PM", quizPart0.getSubsubcategory());
      assertEquals("", quizPart0.getQuiz());
      assertEquals("", quizPart0.getCategory());
      assertEquals(2552, quizPart0.getOrder());
      assertEquals(0, quizPart0.getNumber());
      assertEquals("", quizPart0.getSubcategory());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("system.model.classes.quizzes.QuizPart", "system.model.classes.quizzes.QuizPart", "system.model.classes.quizzes.QuizPart", (-2733), (-2733), (String) null, "xYpU~o1989_Z-glU", "n");
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getId());
      
      quizPart0.setId((String) null);
      quizPart0.getId();
      assertEquals((-2733), quizPart0.getOrder());
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getName());
      assertEquals("xYpU~o1989_Z-glU", quizPart0.getSubcategory());
      assertEquals("n", quizPart0.getSubsubcategory());
      assertEquals((-2733), quizPart0.getNumber());
      assertEquals("system.model.classes.quizzes.QuizPart", quizPart0.getQuiz());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", "", 0, 0, "", "", "`R! NXy(6!6v%");
      String string0 = quizPart0.getId();
      assertEquals("", quizPart0.getName());
      assertEquals("`R! NXy(6!6v%", quizPart0.getSubsubcategory());
      assertEquals("", string0);
      assertEquals(0, quizPart0.getOrder());
      assertEquals("", quizPart0.getSubcategory());
      assertEquals("", quizPart0.getCategory());
      assertEquals("", quizPart0.getQuiz());
      assertEquals(0, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("_hwEZ", "_hwEZ", 545, 545, "system.model.classes.quizzes.QuizPart", "_hwEZ", "");
      String string0 = quizPart0.getCategory();
      assertEquals(545, quizPart0.getNumber());
      assertEquals("_hwEZ", quizPart0.getQuiz());
      assertEquals("_hwEZ", quizPart0.getSubcategory());
      assertEquals("", quizPart0.getSubsubcategory());
      assertEquals(545, quizPart0.getOrder());
      assertEquals("_hwEZ", quizPart0.getName());
      assertEquals("system.model.classes.quizzes.QuizPart", string0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("", "", "", 0, 0, "", "", "`R! NXy(6!6v%");
      String string0 = quizPart0.getCategory();
      assertEquals("`R! NXy(6!6v%", quizPart0.getSubsubcategory());
      assertEquals("", string0);
      assertEquals("", quizPart0.getId());
      assertEquals("", quizPart0.getSubcategory());
      assertEquals(0, quizPart0.getOrder());
      assertEquals("", quizPart0.getQuiz());
      assertEquals("", quizPart0.getName());
      assertEquals(0, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      QuizPart quizPart0 = null;
      try {
        quizPart0 = new QuizPart((QuizPart) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.model.classes.quizzes.QuizPart", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.getSubsubcategory();
      assertEquals((-596), quizPart0.getNumber());
      assertEquals((-596), quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart();
      String string0 = quizPart0.getCategory();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      QuizPart quizPart1 = new QuizPart(quizPart0);
      assertEquals(0, quizPart1.getOrder());
      assertEquals(0, quizPart1.getNumber());
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      int int0 = quizPart0.getOrder();
      assertEquals(0, int0);
      assertEquals(0, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.setQuiz("44Io");
      assertEquals((-596), quizPart0.getOrder());
      assertEquals((-596), quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      quizPart0.setSubcategory("L!`8}2OFP?(h^9='18");
      assertEquals(0, quizPart0.getNumber());
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      quizPart0.getQuiz();
      assertEquals(0, quizPart0.getNumber());
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.getId();
      assertEquals((-596), quizPart0.getNumber());
      assertEquals((-596), quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart();
      quizPart0.setSubsubcategory((String) null);
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.setNumber((-596));
      assertEquals((-596), quizPart0.getOrder());
      assertEquals((-596), quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      quizPart0.setOrder(0);
      assertEquals(0, quizPart0.getOrder());
      assertEquals(0, quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      quizPart0.changeId();
      assertEquals(0, quizPart0.getNumber());
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", 0, 0, "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18", "L!`8}2OFP?(h^9='18");
      int int0 = quizPart0.getNumber();
      assertEquals(0, int0);
      assertEquals(0, quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.getName();
      assertEquals((-596), quizPart0.getOrder());
      assertEquals((-596), quizPart0.getNumber());
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.getSubcategory();
      assertEquals((-596), quizPart0.getNumber());
      assertEquals((-596), quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.setName("44Io");
      assertEquals((-596), quizPart0.getNumber());
      assertEquals((-596), quizPart0.getOrder());
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      QuizPart quizPart0 = new QuizPart("44Io", "44Io", "44Io", (-596), (-596), "44Io", "44Io", "44Io");
      quizPart0.setCategory("44Io");
      assertEquals((-596), quizPart0.getNumber());
      assertEquals((-596), quizPart0.getOrder());
  }
}
