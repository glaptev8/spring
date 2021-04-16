package edu.school21.printer;

import edu.school21.render.Renderer;

public class PrinterWithPrefixImpl implements Printer {

  String prefix;
  Renderer renderer;

  public PrinterWithPrefixImpl(String prefix, Renderer renderer) {
    this.prefix = prefix;
    this.renderer = renderer;
  }

  public PrinterWithPrefixImpl(Renderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public void print(String message) {
    renderer.print(prefix + " " + message);
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
}
