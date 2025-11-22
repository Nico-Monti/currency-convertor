import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var teclado = new Scanner(System.in);
        int opci;
        double razon;
        double tasaDeCambio;
        double dinero;
        double resultado;

        do{
            menu();
            opci = teclado.nextInt();

            if(opci == 0){
                System.out.println("Finalizando...");

            }else if (opci>0 && opci<9){
                System.out.println("Cantidad de dinero a convertir:");
                dinero = Math.abs(teclado.nextDouble());

                if(opci<3){
                    razon = Tasa.consultarTasa("USD","ARS");
                } else if (opci<5){
                    razon = Tasa.consultarTasa("USD","BRL");
                } else if (opci<7){
                    razon = Tasa.consultarTasa("USD", "COP");
                } else razon = Tasa.consultarTasa("ARS","BRL");

                tasaDeCambio = opci%2 == 0? (1 / razon) : razon;
                resultado = dinero * tasaDeCambio;

                switch (opci){
                    case 1:
                        System.out.println("$"+dinero+" [USD] equivalen a $ "+resultado+" [ARS].");
                        break;
                    case 2:
                        System.out.println("$"+dinero+" [ARS] equivalen a $"+resultado+" [USD].");
                        break;

                    case 3:
                        System.out.println("$"+dinero+" [USD] equivalen a $"+resultado+" [BRL].");
                        break;
                    case 4:
                        System.out.println("$"+dinero+" [BRL] equivalen a $"+resultado+" [USD].");

                        break;

                    case 5:
                        System.out.println("$"+dinero+" [USD] equivalen a $"+resultado+" [COP].");
                        break;
                    case 6:
                        System.out.println("$"+dinero+" [COP] equivalen a $"+resultado+" [USD].");
                        break;

                    case 7:
                        System.out.println("$"+dinero+" [ARS] equivalen a $"+resultado+" [BRL].");
                        break;
                    case 8:
                        System.out.println("$"+dinero+" [BRL] equivalen a $"+resultado+" [ARS].");
                        break;
                }
            }else System.out.println("Ingrese una opción válida!");
            System.out.println();

        }while (opci!=0);
    }

    public static void menu(){
        System.out.print("""
                ****************************************
                ¡Bienvenido al conversor de Monedas!
                0) Salir
                
                1) USD ==> ARS (Dólar estadounidense a peso argentino)
                2) ARS ==> USD
                
                3) USD ==> BRL (Dólar estadounidense a real brasileño)
                4) BRL ==> USD
               
                5) USD ==> COP (Dólar estadounidense a peso colombiano)
                6) COP ==> USD
               
                7) ARS ==> BRL (Peso argentino a real brasileño)
                8) BRL ==> ARS
                ****************************************
                Opción:""");
    }
}
