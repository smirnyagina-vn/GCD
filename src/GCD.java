import java.math.BigInteger;

public class GCD {

    private static BigInteger zero = BigInteger.valueOf(0);
    private static BigInteger one = BigInteger.valueOf(1);
    private static BigInteger two = BigInteger.valueOf(2);

    private BigInteger firstTestValue = BigInteger.valueOf(698431);
    private BigInteger secondTestValue = BigInteger.valueOf(453899);

    private BigInteger firstValueTaskA = new BigInteger("64814597339777067547");
    private BigInteger secondValueTaskA = new BigInteger("76442259788450777597");

    private BigInteger firstValueTaskB = new BigInteger("400423184283114835311078811328107752059");
    private BigInteger secondValueTaskB = new BigInteger("450849263766887986909084788831495688171");

    private BigInteger firstValueTaskC = new BigInteger("17249605866167588117154017410346274048929722894429379974520585074625118717153691");
    private BigInteger secondValueTaskC = new BigInteger("98252841129072496076297827015726065290107810588019604821969773310133966635047903");


    public GCD()
    {
        //gcdExtendedOrCut(firstTestValue, secondTestValue, true);
        //gcdExtendedOrCut(firstTestValue, secondTestValue, false);
        //gcdBinary(firstTestValue, secondTestValue);
        //gcdBinary(BigInteger.valueOf(1092), BigInteger.valueOf(988));

        //gcdExtendedOrCut(firstValueTaskA, secondValueTaskA, true);
        //gcdExtendedOrCut(firstValueTaskA, secondValueTaskA, false);
        //gcdBinary(firstValueTaskA, secondValueTaskA);

        //gcdExtendedOrCut(firstValueTaskB, secondValueTaskB, true);
        //gcdExtendedOrCut(firstValueTaskB, secondValueTaskB, false);
        //gcdBinary(firstValueTaskB, secondValueTaskB);

        //gcdExtendedOrCut(firstValueTaskC, secondValueTaskC, true);
        //gcdExtendedOrCut(firstValueTaskC, secondValueTaskC, false);
        //gcdBinary(firstValueTaskC, secondValueTaskC);

    }


    public static void main(String[] args) {
        GCD gcd = new GCD();
    }


    public void gcdExtendedOrCut(BigInteger A, BigInteger B, boolean extended)
    {
        String tmp = " i |  ri  |  xi  |  yi  |  qi  |";
        String line = "--------------------------------";

        if (extended) System.out.println("         GCD Extended");
            else System.out.println("           GCD Cut");
        System.out.print(tmp + "\n" + line + "\n");

        BigInteger a = A, b = B;
        BigInteger r_i = BigInteger.valueOf(0);
        BigInteger x_2 = BigInteger.valueOf(1);
        BigInteger x_1 = BigInteger.valueOf(0);
        BigInteger x = BigInteger.valueOf(0);
        BigInteger y_2 = BigInteger.valueOf(0);
        BigInteger y_1 = BigInteger.valueOf(1);
        BigInteger y = BigInteger.valueOf(0);
        BigInteger count = BigInteger.valueOf(2);
        BigInteger q_i = BigInteger.valueOf(0);

        System.out.print( " 0 | " + a + " | " + "1 | 0 | \n");
        System.out.print( " 1 | " + b + " | 0 | 1 | ");

        while (b.compareTo(zero) == 1)
        {
            q_i = a.abs().divide(b.abs());

            System.out.print(q_i + "\n");

            r_i = a.subtract(q_i.multiply(b));
            x = x_2.subtract(q_i.multiply(x_1));
            y = y_2.subtract(q_i.multiply(y_1));

            if (!extended && r_i.compareTo(b.divide(BigInteger.valueOf(2))) == 1)
            {
                r_i = b.subtract(r_i);
                x = x_1.subtract(x);
                y = y_1.subtract(y);
            }

            if (r_i.compareTo(zero) == 1)
                System.out.print(" " + count + " | " + r_i + " | "
                        + x + " | " + y + " | ");
            else System.out.print(line + "\n" + "   | 0" + "\n");

            a = b; b = r_i;
            x_2 = x_1; x_1 = x;
            y_2 = y_1; y_1 = y;
            count = count.add(one);
        }

        System.out.println( "Linear view = NOD = " + a
                + " = " + A + "*" + x_2
                + " + " + B + "*" + y_2
                + " = " + A.multiply(x_2).add(B.multiply(y_2)) + "\n");
    }

    public void gcdBinary(BigInteger aSource, BigInteger bSource)
    {
        String tmp = "  i |  u  |  v  |  A  |  B  |  C  |  D  |";
        String line = "----------------------------------------";

        System.out.println("               GCD Binary");
        System.out.println(tmp + "\n" + line);

        BigInteger a = aSource.abs(), b = bSource.abs();
        BigInteger g = one;
        BigInteger d = zero;
        BigInteger x = zero;
        BigInteger y = zero;
        BigInteger u, v;
        BigInteger A, B, C, D;
        BigInteger count = zero;

        //2
        while (a.mod(two) == zero && b.mod(two) == zero)
        {
            a = a.shiftRight(1);
            b = b.shiftRight(1);
            g = g.multiply(two);
        }

        //3
        u = a; v = b;
        A = one; B = zero; C = zero; D = one;
        System.out.print( "3.0 | " + u + " | " + v
                + " | " + A + " | " + B + " | "
                + C + " | "+ D + "\n");
        count = count.add(one);

        //4
        while (u.compareTo(zero) != 0)
        {
            //4.1
            while(u.mod(two) == zero)
            {
                //4.1.1
                u = u.shiftRight(1);
                //4.1.2
                if (A.mod(two) == zero && B.mod(two) == zero)
                {
                    A = A.shiftRight(1);
                    B = B.shiftRight(1);
                }
                else
                {
                    A = A.add(b).divide(two);
                    B = B.subtract(a).divide(two);
                }
                System.out.print( "4.1 | "  + u + " | " + v
                        + " | " + A + " | " + B + " | "
                        + C + " | "+ D + "\n");
                count = count.add(one);
            }

            //4.2
            while(v.mod(two) == zero)
            {
                //4.2.1
                v = v.shiftRight(1);
                //4.2.2
                if (C.mod(two) == zero && D.mod(two) == zero)
                {
                    C = C.shiftRight(1);
                    D = D.shiftRight(1);
                }
                else
                {
                    C = C.add(b).divide(two);
                    D = D.subtract(a).divide(two);
                }
                System.out.print("4.2 | "  + u + " | " + v
                        + " | " + A + " | " + B + " | "
                        + C + " | " + D + "\n");
                count = count.add(one);
            }

            //4.3
            if (u.compareTo(v) >= 0)
            {
                u = u.subtract(v);
                A = A.subtract(C);
                B = B.subtract(D);
            }
            else
            {
                v = v.subtract(u);
                C = C.subtract(A);
                D = D.subtract(B);
            }
            System.out.print( "4.3 | " + u + " | " + v
                    + " | " + A + " | " + B + " | "
                    + C + " | " + D + "\n");
            count = count.add(one);
        }

        //5
        d = g.multiply(v); x = C; y = D;

        System.out.println( "\nLinear view = NOD = " + d + " = "
                + aSource + "*" +  x + " + " + bSource + "*" + y + " = "
                + aSource.multiply(x).add(bSource.multiply(y)));
        System.out.println("Number of iterations: " + count);
    }

}
