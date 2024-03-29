/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:02:55 GMT 2018
 */

package system.model.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import system.controller.service.database.MySQLDatabaseService;
import system.model.dao.Dao;
import system.model.dao.QuestionDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class QuestionDao_ESTest extends QuestionDao_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      QuestionDao questionDao0 = new QuestionDao();
      MySQLDatabaseService mySQLDatabaseService0 = mock(MySQLDatabaseService.class, new ViolatedAssumptionAnswer());
      Injector.inject(questionDao0, (Class<?>) Dao.class, "databaseService", (Object) mySQLDatabaseService0);
      Injector.validateBean(questionDao0, (Class<?>) QuestionDao.class);
      assertFalse(Dao.SQLITE_NOT_MYSQL);
  }
}
