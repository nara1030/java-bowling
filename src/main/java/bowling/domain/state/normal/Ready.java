package bowling.domain.state.normal;

import bowling.domain.Frame;
import bowling.domain.state.ThrowingState;

public class Ready implements ThrowingState {
    private Frame frame; // = new Frame(); // causes recursion issue

    public Ready(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        if (pins == 10) {
            return new Strike(frame);
        }
        if (pins == 0) {
            return new Gutter(frame);
        }
        return new FirstBowl(frame);
    }

    @Override
    public String symbol() {
        return "";
    }
}
