/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:08:21 GMT 2018
 */

package system.model.classes.games;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import system.model.classes.games.ListOfQuestions;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ListOfQuestions_ESTest extends ListOfQuestions_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ListOfQuestions listOfQuestions0 = new ListOfQuestions();
      LinkedList<String> linkedList0 = listOfQuestions0.getQuestionIds();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ListOfQuestions listOfQuestions0 = new ListOfQuestions();
      listOfQuestions0.setQuestionIds((LinkedList<String>) null);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ListOfQuestions listOfQuestions0 = new ListOfQuestions((LinkedList<String>) null);
  }
}
