package pattern.creational.builder;

public class BigObject {
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;

    private BigObject(Builder builder) {
        this.param1 = builder.param1;
        this.param2 = builder.param2;
        this.param3 = builder.param3;
        this.param4 = builder.param4;
        this.param5 = builder.param5;
    }

    @Override
    public String toString() {
        return "BigObject{" + "param1='"
                + param1 + '\'' + ", param2='"
                + param2 + '\'' + ", param3='"
                + param3 + '\'' + ", param4='"
                + param4 + '\'' + ", param5='"
                + param5 + '\'' + '}';
    }

    public static class Builder {
        private String param1;
        private String param2;
        private String param3;
        private String param4;
        private String param5;

        Builder(String param1) {
            if (param1 == null) throw new IllegalArgumentException("param1 cannot be null");

            this.param1 = param1;
        }

        Builder withParam2(String param2) {
            this.param2 = param2;
            return this;
        }

        Builder withParam3(String param3) {
            if (param3.length() < 10) throw new IllegalArgumentException("length of param3 must be >= 10");

            this.param3 = param3;
            return this;
        }

        public Builder withParam4(String param4) {
            this.param4 = param4;
            return this;
        }

        Builder withParam5(String param5) {
            this.param5 = param5;
            return this;
        }

        BigObject build() {
            return new BigObject(this);
        }
    }
}