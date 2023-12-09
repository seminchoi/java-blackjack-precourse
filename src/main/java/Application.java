import controller.BlackjackMachine;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final BlackjackMachine blackjackMachine = new BlackjackMachine(new InputView(), new OutputView());
        blackjackMachine.run();
    }
}
