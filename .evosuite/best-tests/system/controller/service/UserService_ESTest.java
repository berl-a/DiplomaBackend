/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 07 14:55:37 GMT 2018
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
import system.controller.service.UserService;
import system.model.classes.users.User;
import system.model.classes.users.UserType;
import system.model.dao.UserDao;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class UserService_ESTest extends UserService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      UserDao userDao1 = userService0.dao;
      userService0.dao = userDao1;
      userService0.dao = null;
      // Undeclared exception!
      try { 
        userService0.updateCached();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      userService0.dao = null;
      // Undeclared exception!
      try { 
        userService0.getAll();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      userService0.dao = null;
      UserType userType0 = UserType.TEACHER;
      // Undeclared exception!
      try { 
        userService0.get("", userType0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      userService0.dao = null;
      // Undeclared exception!
      try { 
        userService0.get("+9 ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      User user0 = userService0.get("ok");
      assertNull(user0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      LinkedList<User> linkedList0 = userService0.getAll();
      assertNull(linkedList0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      // Undeclared exception!
      try { 
        userService0.getById((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null, (LinkedList) null, (LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      // Undeclared exception!
      try { 
        userService0.addAdminToDatabase();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      userService0.get("j{y");
      // Undeclared exception!
      try { 
        userService0.getById("^C");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.controller.service.UserService", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null).when(userDao0).getAll();
      doReturn((String) null).when(userDao0).remove(anyString());
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      String string0 = userService0.remove("");
      assertEquals("ok", string0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      UserService userService0 = new UserService();
      UserDao userDao0 = mock(UserDao.class, new ViolatedAssumptionAnswer());
      doReturn((LinkedList) null, (LinkedList) null).when(userDao0).getAll();
      Injector.inject(userService0, (Class<?>) UserService.class, "dao", (Object) userDao0);
      Injector.validateBean(userService0, (Class<?>) UserService.class);
      // Undeclared exception!
      try { 
        userService0.copy("F)");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("system.model.classes.users.User", e);
      }
  }
}
