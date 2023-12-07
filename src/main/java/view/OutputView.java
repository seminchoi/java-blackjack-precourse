package view;

public class OutputView {
    public void printError(Exception e) {
        System.out.printf("[ERROR] %s\n", e.getMessage());
    }
}
