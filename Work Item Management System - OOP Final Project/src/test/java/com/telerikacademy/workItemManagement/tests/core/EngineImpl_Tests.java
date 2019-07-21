package com.telerikacademy.workItemManagement.tests.core;

import com.telerikacademy.workItemManagement.core.EngineImpl;
import com.telerikacademy.workItemManagement.core.contracts.Engine;
import org.junit.Test;

public class EngineImpl_Tests {

  @Test
  public void constructor_should_return_new_engineImpl(){
    Engine engine = new EngineImpl();
  }
  }
