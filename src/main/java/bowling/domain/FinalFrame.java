package bowling.domain;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_MAX_LENGTH = 3;

    public FinalFrame() {
        this.states = new States();
    }

    @Override
    public void bowl(Pin pin) {
        State state = State.finalBowl(this.states.getLastPin().getFallenPin(), pin.getFallenPin(), this.states.getLastState());
        setStates(state);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getSize() > FINAL_FRAME_MAX_LENGTH;
    }

    @Override
    public boolean isEndGame() {
        if (states.getSize() == NormalFrame.NORMAL_FRAME_MAX_LENGTH && states.getStatesPinSum() < Pin.MAX_PIN) {
            return true;
        }
        return this.states.getSize() == FINAL_FRAME_MAX_LENGTH;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    int calculateAdditionalScore(Score score) {
        score = states.calculateScore(score);

        if (score.canCalculateScore()) {
            return score.getScore();
        }

        return WAITING_CALCULATION;
    }

    @Override
    public int getScore() {
        if (!isEndGame()) {
            return WAITING_CALCULATION;
        }

        return states.getPins()
                .stream()
                .mapToInt(Pin::getFallenPin)
                .sum();
    }

    private void setStates(State state) {
        this.states.add(state);
    }
}
