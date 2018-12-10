/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 15:05:55 GMT 2018
 */

package system.model.classes.users;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import system.model.classes.users.User;
import system.model.classes.users.UserType;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class User_ESTest extends User_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User("<iD_Sp3+Vyj", (String) null, (String) null, userType0);
      UserType userType1 = user0.getType();
      assertSame(userType1, userType0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      User user0 = new User();
      user0.setLogin("system.model.classes.users.User");
      String string0 = user0.getLogin();
      assertEquals("system.model.classes.users.User", string0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      UserType userType0 = UserType.TEACHER;
      User user0 = new User("", "", "", userType0);
      String string0 = user0.getLogin();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      User user0 = new User();
      String string0 = user0.getId();
      assertEquals("00000000-0100-4000-8200-000003000000", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      UserType userType0 = UserType.TEACHER;
      User user0 = new User("", "", "", userType0);
      String string0 = user0.getId();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User("", "6Wfii", userType0);
      String string0 = user0.getHash();
      assertEquals("6Wfii", string0);
      assertEquals("", user0.getLogin());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      User user0 = new User();
      user0.setHash("");
      String string0 = user0.getHash();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      User user0 = null;
      try {
        user0 = new User((User) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.model.classes.users.User", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      User user0 = new User();
      user0.getType();
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User((String) null, (String) null, (String) null, userType0);
      user0.setId((String) null);
      assertNull(user0.getHash());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      UserType userType0 = UserType.TEACHER;
      User user0 = new User("", "", "", userType0);
      user0.setType(userType0);
      assertEquals("", user0.getId());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User((String) null, (String) null, (String) null, userType0);
      String string0 = user0.getLogin();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User((String) null, (String) null, (String) null, userType0);
      String string0 = user0.getHash();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User((String) null, (String) null, (String) null, userType0);
      String string0 = user0.getId();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User((String) null, (String) null, (String) null, userType0);
      user0.changeId();
      assertEquals(UserType.ADMINISTRATOR, user0.getType());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      UserType userType0 = UserType.ADMINISTRATOR;
      User user0 = new User("%", "", "NC3.Yrp?u>xm", userType0);
      User user1 = new User(user0);
      assertEquals("%", user1.getId());
      assertEquals("", user1.getLogin());
      assertEquals("NC3.Yrp?u>xm", user1.getHash());
  }
}