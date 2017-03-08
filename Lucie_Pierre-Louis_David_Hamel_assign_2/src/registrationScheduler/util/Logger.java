
package registrationScheduler.util;

public class Logger{


    //public static enum DebugLevel { CONSTRUCTOR, RUN,  };

  //  private static DebugLevel debugLevel;
	private static int debugLevel;

    public static void setDebugValue (int levelIn) {
		debugLevel = levelIn;
    }

    public static int getDebugValue () {
		return debugLevel;
    }

    //@return None
    public static void writeMessage (String message, int levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
		}

    public String toString() {
		return "Debug Level is " + debugLevel;
    }
}
