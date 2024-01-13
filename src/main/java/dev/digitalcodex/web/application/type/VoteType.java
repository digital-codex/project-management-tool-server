package dev.digitalcodex.web.application.type;

public enum VoteType {
    UP_VOTE(1),
    DOWN_VOTE(-1);

    private final int value;

    VoteType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
