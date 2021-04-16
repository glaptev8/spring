package edu.school21.App;

import edu.school21.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    Printer printer = context.getBean("printWithPrefix", Printer.class);
    printer.print("HeLLo!");
  }
}
