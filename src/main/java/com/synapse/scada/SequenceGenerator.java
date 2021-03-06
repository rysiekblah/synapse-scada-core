package com.synapse.scada;

/**
 * Created by tomek on 6/14/14.
 */
public class SequenceGenerator {

    private String prefix;
    private String suffix;
    private int initial;
    private int counter;
    private String username;

    public SequenceGenerator() {}
    public SequenceGenerator(String prefix, String suffix, int initial, String username) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.initial = initial;
        this.username = username;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public void setInitial(int initial) {
        this.initial = initial;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public synchronized String getSequence() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(initial + counter++);
        buffer.append(suffix);
        return buffer.toString();
    }

}
