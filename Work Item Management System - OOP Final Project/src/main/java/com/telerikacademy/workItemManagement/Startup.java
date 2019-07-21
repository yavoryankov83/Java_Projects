package com.telerikacademy.workItemManagement;

import com.telerikacademy.workItemManagement.core.EngineImpl;
import com.telerikacademy.workItemManagement.core.contracts.Engine;

import java.util.Locale;

public class Startup {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);

    Engine engine = new EngineImpl();
    engine.start();
  }
}
