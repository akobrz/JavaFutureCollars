import java.util.Scanner;

public class Task1 {
    Double loan;
    Double percent;
    Double rate;

    Double[] inflation = {1.592824484, -0.453509101, 2.324671717, 1.261254407, 1.782526286, 2.329384541,
            1.502229842, 1.782526286, 2.328848994, 0.616921348, 2.352295886, 0.337779545,
            1.577035247, -0.292781443, 2.48619659, 0.267110318, 1.417952672, 1.054243267,
            1.480520104, 1.577035247, -0.07742069, 1.165733399, -0.404186718, 1.499708521};

    String information = "Twoja pozostała kwota kredytu to %.6f, to %.6f mniej niż w poprzednim miesiącu.";

    public void readData() throws ErrorHandler {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj początkową wysokość kredytu:");
        if (sc.hasNext("\\d+\\.?\\d+")){
            this.loan = sc.nextDouble();
        } else {
            throw new ErrorHandler();
        }
        System.out.println("Podaj oprocentowanie kredytu:");
        if (sc.hasNext("\\d+\\.?\\d*")){
            this.percent = sc.nextDouble();
        } else {
            throw new ErrorHandler();
        }
        System.out.println("Podaj kwotę raty miesięcznej:");
        if (sc.hasNext("\\d+\\.?\\d+")){
            this.rate = sc.nextDouble();
        } else {
            throw new ErrorHandler();
        }
    }

    public void printLoanSaldo(){
        for (Double d: inflation){
            Double last_saldo = this.loan;
            loan = (1+((d+percent)/1200))*loan - rate;
            if (loan > 0){
                System.out.printf((information) + "%n", loan, last_saldo-loan);
            } else {
                System.out.printf((information) + "%n", 0.0, last_saldo);
                break;
            }
        }
    }

    public static class ErrorHandler extends Throwable{
        public ErrorHandler() {
            System.out.println("Brak danych lub niepoprawne dane");
        }
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        try{
            task.readData();
        } catch (ErrorHandler ow) {
            System.out.println("Uruchom ponownie i wprowadź poprawne dane");
        }
        task.printLoanSaldo();
    }

}
