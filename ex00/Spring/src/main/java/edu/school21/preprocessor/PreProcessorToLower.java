package edu.school21.preprocessor;

public class PreProcessorToLower implements PreProcessor {
  @Override
  public String preProcessor(String message) {
    return message.toLowerCase();
  }
}
