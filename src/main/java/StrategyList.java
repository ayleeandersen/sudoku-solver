import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public class StrategyList {
    // TODO: finish writing strategies
    private static ArrayList<Strategy> strategies = new ArrayList<>(List.of(new Strategy("A"), new Strategy("B")));

    public static void addNewStrategy(Strategy strategy) {
        if (!strategies.contains(strategy)) {
            strategies.add(strategy);
        }
    }

    public static ArrayList<Strategy> getStrategies() {
        // Shallow copy since they have different addresses...I checked
        ArrayList<Strategy> strategiesCopy = new ArrayList<>(strategies);
        return strategiesCopy;
    }

    public static void removeStrategy(String name) {
        for (int i = 0; i < strategies.size(); i++) {
            if (strategies.get(i).getName().equals(name)) {
                strategies.remove(i);
            }
        }
    }
}