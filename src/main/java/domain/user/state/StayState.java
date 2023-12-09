package domain.user.state;

public class StayState implements State {
    @Override
    public boolean isFinished() {
        return true;
    }
}
