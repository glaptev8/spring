package edu.school21.printer;

import edu.school21.render.Renderer;

public class PrinterWithDateTimeImpl implements Printer {
  Renderer renderer;

  public PrinterWithDateTimeImpl(Renderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public void print(String message) {
    renderer.print("date = LocalDateTime.now().toString() : " + message);
  }
}
