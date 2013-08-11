package autoer.ui.model;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ExtendedKeyEvent {

  private static HashMap<String, Integer> keyMaps = new HashMap<String, Integer>();
  
  static {
    keyMaps.put(",", KeyEvent.VK_COMMA);
    keyMaps.put(".", KeyEvent.VK_PERIOD);
    keyMaps.put("/", KeyEvent.VK_SLASH);
    keyMaps.put(";", KeyEvent.VK_SEMICOLON);
    keyMaps.put("=", KeyEvent.VK_EQUALS);
    keyMaps.put("(", KeyEvent.VK_OPEN_BRACKET);
    keyMaps.put("\\", KeyEvent.VK_BACK_SLASH);
    keyMaps.put(")", KeyEvent.VK_CLOSE_BRACKET);

    keyMaps.put("ENTER", KeyEvent.VK_ENTER);
    keyMaps.put("BACK_SPACE", KeyEvent.VK_BACK_SPACE);
    keyMaps.put("TAB", KeyEvent.VK_TAB);
    keyMaps.put("CANCEL", KeyEvent.VK_CANCEL);
    keyMaps.put("CLEAR", KeyEvent.VK_CLEAR);
    keyMaps.put("SHIFT", KeyEvent.VK_SHIFT);
    keyMaps.put("CONTROL", KeyEvent.VK_CONTROL);
    keyMaps.put("ALT", KeyEvent.VK_ALT);
    keyMaps.put("PAUSE", KeyEvent.VK_PAUSE);
    keyMaps.put("CAPS_LOCK", KeyEvent.VK_CAPS_LOCK);
    keyMaps.put("ESCAPE", KeyEvent.VK_ESCAPE);
    keyMaps.put(" ", KeyEvent.VK_SPACE);
    keyMaps.put("PAGE_UP", KeyEvent.VK_PAGE_UP);
    keyMaps.put("PAGE_DOWN", KeyEvent.VK_PAGE_DOWN);
    keyMaps.put("END", KeyEvent.VK_END);
    keyMaps.put("HOME", KeyEvent.VK_HOME);
    keyMaps.put("LEFT", KeyEvent.VK_LEFT);
    keyMaps.put("UP", KeyEvent.VK_UP);
    keyMaps.put("RIGHT", KeyEvent.VK_RIGHT);
    keyMaps.put("DOWN", KeyEvent.VK_DOWN);

    // numpad numeric keys handled below
    keyMaps.put("MULTIPLY", KeyEvent.VK_MULTIPLY);
    keyMaps.put("ADD", KeyEvent.VK_ADD);
    keyMaps.put("SEPARATOR", KeyEvent.VK_SEPARATOR);
    keyMaps.put("SUBTRACT", KeyEvent.VK_SUBTRACT);
    keyMaps.put("DECIMAL", KeyEvent.VK_DECIMAL);
    keyMaps.put("DIVIDE", KeyEvent.VK_DIVIDE);
    keyMaps.put("DELETE", KeyEvent.VK_DELETE);
    keyMaps.put("NUM_LOCK", KeyEvent.VK_NUM_LOCK);
    keyMaps.put("SCROLL_LOCK", KeyEvent.VK_SCROLL_LOCK);

    keyMaps.put("F1", KeyEvent.VK_F1);
    keyMaps.put("F2", KeyEvent.VK_F2);
    keyMaps.put("F3", KeyEvent.VK_F3);
    keyMaps.put("F4", KeyEvent.VK_F4);
    keyMaps.put("F5", KeyEvent.VK_F5);
    keyMaps.put("F6", KeyEvent.VK_F6);
    keyMaps.put("F7", KeyEvent.VK_F7);
    keyMaps.put("F8", KeyEvent.VK_F8);
    keyMaps.put("F9", KeyEvent.VK_F9);
    keyMaps.put("F10", KeyEvent.VK_F10);
    keyMaps.put("F11", KeyEvent.VK_F11);
    keyMaps.put("F12", KeyEvent.VK_F12);

    keyMaps.put("1", KeyEvent.VK_1);
    keyMaps.put("2", KeyEvent.VK_2);
    keyMaps.put("3", KeyEvent.VK_3);
    keyMaps.put("4", KeyEvent.VK_4);
    keyMaps.put("5", KeyEvent.VK_5);
    keyMaps.put("6", KeyEvent.VK_6);
    keyMaps.put("7", KeyEvent.VK_7);
    keyMaps.put("8", KeyEvent.VK_8);
    keyMaps.put("9", KeyEvent.VK_9);
    keyMaps.put("0", KeyEvent.VK_0);

    keyMaps.put("A", KeyEvent.VK_A);
    keyMaps.put("B", KeyEvent.VK_B);
    keyMaps.put("C", KeyEvent.VK_C);
    keyMaps.put("D", KeyEvent.VK_D);
    keyMaps.put("E", KeyEvent.VK_E);
    keyMaps.put("F", KeyEvent.VK_F);
    keyMaps.put("G", KeyEvent.VK_G);
    keyMaps.put("H", KeyEvent.VK_H);
    keyMaps.put("I", KeyEvent.VK_I);
    keyMaps.put("J", KeyEvent.VK_J);
    keyMaps.put("K", KeyEvent.VK_K);
    keyMaps.put("L", KeyEvent.VK_L);
    keyMaps.put("M", KeyEvent.VK_M);
    keyMaps.put("N", KeyEvent.VK_N);
    keyMaps.put("O", KeyEvent.VK_O);
    keyMaps.put("P", KeyEvent.VK_P);
    keyMaps.put("Q", KeyEvent.VK_Q);
    keyMaps.put("R", KeyEvent.VK_R);
    keyMaps.put("S", KeyEvent.VK_S);
    keyMaps.put("T", KeyEvent.VK_T);
    keyMaps.put("U", KeyEvent.VK_U);
    keyMaps.put("V", KeyEvent.VK_V);
    keyMaps.put("W", KeyEvent.VK_W);
    keyMaps.put("X", KeyEvent.VK_X);
    keyMaps.put("Y", KeyEvent.VK_Y);
    keyMaps.put("Z", KeyEvent.VK_Z);

    keyMaps.put("PRINTSCREEN", KeyEvent.VK_PRINTSCREEN);
    keyMaps.put("INSERT", KeyEvent.VK_INSERT);
    keyMaps.put("HELP", KeyEvent.VK_HELP);
    keyMaps.put("META", KeyEvent.VK_META);
    keyMaps.put("BACK_QUOTE", KeyEvent.VK_BACK_QUOTE);
    keyMaps.put("QUOTE", KeyEvent.VK_QUOTE);

    keyMaps.put("KP_UP", KeyEvent.VK_KP_UP);
    keyMaps.put("KP_DOWN", KeyEvent.VK_KP_DOWN);
    keyMaps.put("KP_LEFT", KeyEvent.VK_KP_LEFT);
    keyMaps.put("KP_RIGHT", KeyEvent.VK_KP_RIGHT);

    keyMaps.put("AMPERSAND", KeyEvent.VK_AMPERSAND);
    keyMaps.put("ASTERISK", KeyEvent.VK_ASTERISK);
    keyMaps.put("QUOTEDBL", KeyEvent.VK_QUOTEDBL);
    keyMaps.put("LESS", KeyEvent.VK_LESS);
    keyMaps.put("GREATER", KeyEvent.VK_GREATER);
    keyMaps.put("BRACELEFT", KeyEvent.VK_BRACELEFT);
    keyMaps.put("BRACERIGHT", KeyEvent.VK_BRACERIGHT);
    keyMaps.put("AT", KeyEvent.VK_AT);
    keyMaps.put("COLON", KeyEvent.VK_COLON);
    keyMaps.put("CIRCUMFLEX", KeyEvent.VK_CIRCUMFLEX);
    keyMaps.put("DOLLAR", KeyEvent.VK_DOLLAR);
    keyMaps.put("EURO_SIGN", KeyEvent.VK_EURO_SIGN);
    keyMaps.put("!", KeyEvent.VK_EXCLAMATION_MARK);
    keyMaps.put("INVERTED_EXCLAMATION_MARK", KeyEvent.VK_INVERTED_EXCLAMATION_MARK);
    keyMaps.put("LEFT_PARENTHESIS", KeyEvent.VK_LEFT_PARENTHESIS);
    keyMaps.put("NUMBER_SIGN", KeyEvent.VK_NUMBER_SIGN);
    keyMaps.put("MINUS", KeyEvent.VK_MINUS);
    keyMaps.put("PLUS", KeyEvent.VK_PLUS);
    keyMaps.put("RIGHT_PARENTHESIS", KeyEvent.VK_RIGHT_PARENTHESIS);
    keyMaps.put("UNDERSCORE", KeyEvent.VK_UNDERSCORE);

    keyMaps.put("AGAIN", KeyEvent.VK_AGAIN);
    keyMaps.put("UNDO", KeyEvent.VK_UNDO);
    keyMaps.put("COPY", KeyEvent.VK_COPY);
    keyMaps.put("PASTE", KeyEvent.VK_PASTE);
    keyMaps.put("CUT", KeyEvent.VK_CUT);
    keyMaps.put("FIND", KeyEvent.VK_FIND);
    keyMaps.put("PROPS", KeyEvent.VK_PROPS);
    keyMaps.put("STOP", KeyEvent.VK_STOP);
  }

  private ExtendedKeyEvent() {
  }

  public static HashMap<String, Integer> getModifierMap() {
    return keyMaps;
  }

  public static int getKeyCode(String key) {
    return keyMaps.get(key);
  }
}
