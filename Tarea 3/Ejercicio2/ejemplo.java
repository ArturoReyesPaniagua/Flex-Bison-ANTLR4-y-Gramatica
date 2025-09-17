public class ejemplo {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        System.out.println((x + y));
        if ((x > y)) {
            System.out.println(x);
            int z = (x * 2);
            System.out.println(z);
        }
        int i = 0;
        while ((i < 3)) {
            System.out.println(i);
            i = (i + 1);
        }
        int factorial = 1;
        int n = 5;
        int contador = 1;
        while ((contador <= n)) {
            factorial = (factorial * contador);
            contador = (contador + 1);
        }
        System.out.println(factorial);
    }
}
