package exemple_matrix;

public class Matrix
{
    interface Operation
    {
        boolean fn(boolean one, boolean two);
    }

    boolean matrix[][];

    private Matrix(int n, boolean init)
    {
        matrix = new boolean[n][n];

        if (init)
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix.length; j++)
                    matrix[i][j] = Math.random() > 0.5;
    }

    public Matrix(int n)
    {
        this(n, true);
    }

    private Matrix operation(Matrix m, Operation op)
    {
        if (m.matrix.length != matrix.length)
            return null;

        Matrix result = new Matrix(matrix.length, false);

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                result.matrix[i][j] = op.fn(m.matrix[i][j], matrix[i][j]);

        return result;
    }

    public Matrix or(Matrix m) {
        return operation(m, new Operation() {
            public boolean fn(boolean one, boolean two) { return one | two; }
        });
    }

    public Matrix and(Matrix m) {
        return operation(m, new Operation() {
            public boolean fn(boolean one, boolean two) { return one & two; }
        });
    }

    public Matrix xor(Matrix m) {
        return operation(m, new Operation() {
            public boolean fn(boolean one, boolean two) { return one ^ two; }
        });
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
                s += matrix[i][j] ? "1 " : "0 ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args)
    {
        Matrix one = new Matrix(4), two = new Matrix(4);
        System.out.println("one:\n" + one + "\ntwo:\n" + two + "\none or two:\n" +
                one.or(two) + "\none and two:\n" + one.and(two) +
                "\none xor two:\n" + one.xor(two));
    }
}