/*
 * This file was automatically generated by EvoSuite
 * Mon Dec 03 15:23:26 GMT 2018
 */

package system.controller.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import system.controller.dao.Dao;
import system.controller.dao.QuizPartDao;
import system.controller.service.database.MySQLDatabaseService;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuizPartDao_ESTest extends QuizPartDao_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      QuizPartDao quizPartDao0 = new QuizPartDao();
      MySQLDatabaseService mySQLDatabaseService0 = mock(MySQLDatabaseService.class, new ViolatedAssumptionAnswer());
      Injector.inject(quizPartDao0, (Class<?>) Dao.class, "databaseService", (Object) mySQLDatabaseService0);
      Injector.validateBean(quizPartDao0, (Class<?>) QuizPartDao.class);
      assertEquals("QuizParts", quizPartDao0.getTableName());
  }
}