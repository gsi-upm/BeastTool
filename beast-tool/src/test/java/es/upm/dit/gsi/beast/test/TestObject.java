/**
 * 
 */
package es.upm.dit.gsi.beast.test;

/**
 * Project: beast File: es.upm.dit.gsi.beast.test.TestObject.java
 * 
 * Grupo de Sistemas Inteligentes Departamento de Ingeniería de Sistemas
 * Telemáticos Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @date 21/06/2013
 * @version 0.1
 * 
 */
public class TestObject {

    /**
     * 
     */
    private String stringTest;

    /**
     * @return
     */
    public String getStringTest() {
        return stringTest;
    }

    /**
     * @param stringTest
     */
    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }

    /**
     * @return
     */
    public double getDoubleTest() {
        return doubleTest;
    }

    /**
     * @param doubleTest
     */
    public void setDoubleTest(double doubleTest) {
        this.doubleTest = doubleTest;
    }

    /**
     * @return
     */
    public boolean isBooleanTest() {
        return booleanTest;
    }

    /**
     * @param booleanTest
     */
    public void setBooleanTest(boolean booleanTest) {
        this.booleanTest = booleanTest;
    }

    /**
     * 
     */
    private double doubleTest;
    /**
     * 
     */
    private boolean booleanTest;

    /**
     * @param stringTest
     * @param doubleTest
     * @param booleanTest
     */
    public TestObject(String stringTest, double doubleTest, boolean booleanTest) {
        this.stringTest = stringTest;
        this.doubleTest = doubleTest;
        this.booleanTest = booleanTest;
    }

    /**
     * @param x
     */
    public void multiplyDouble(double x) {
        this.doubleTest *= x;
    }

    /**
     * 
     */
    public void negateBoolean() {
        this.booleanTest = !this.booleanTest;
    }

    /**
     * @param s
     */
    public void concactString(String s) {
        this.stringTest += s;
    }

}
