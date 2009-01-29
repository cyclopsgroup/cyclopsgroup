package wearj;

import org.cyclopsgroup.jemf.TestMethod;
import org.cyclopsgroup.jemf.TestObject;

public class ConstructorPerformance
    extends TestObject
{
    private static class A
    {
        private int n1;
    }

    private static class B
        extends A
    {
        private int n2;
    }

    private static class C
        extends B
    {
        private int n3;
    }

    private static class D
        extends C
    {
        public D( int n4 )
        {
            this.n4 = n4;
        }

        private int n4;
        private int n5;
    }

    private static class S
    {
        private int n1, n2, n3, n4;
    }

    @TestMethod
    public Object deepHierarchy()
    {
        return new D( 3 );
    }

    @TestMethod
    public Object flatHierarchy()
    {
        return new S();
    }
}
