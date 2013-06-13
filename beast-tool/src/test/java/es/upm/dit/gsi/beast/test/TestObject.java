/**
 * 
 */
package es.upm.dit.gsi.beast.test;

/**
 * 
 * @author a.carrera
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
