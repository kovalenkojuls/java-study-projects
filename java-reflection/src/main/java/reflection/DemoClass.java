package reflection;

public class DemoClass {
    public int publicField;

    private String privateField = "initValue";

    public DemoClass() {
    }

    public DemoClass(String valuePrivate) {
        this.privateField = valuePrivate;
    }

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    private void privateMethod() {
        System.out.println("privateMethod executed");
    }

    @SimpleAnnotation
    public String toString() {
        return "DemoClass{" + "publicField=" + publicField + ", value='" + privateField + '\'' + '}';
    }
}