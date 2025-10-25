package br.com.agency.interaction;

import br.com.agency.entities.Sale;
import br.com.agency.entities.Transport;
import br.com.agency.entities.Accommodation;
import br.com.agency.entities.TravelPackage;

import java.math.BigDecimal;
import java.util.Scanner;

import static java.util.Locale.US;

public class InteractionUser {

    private final Scanner scanner = new Scanner(System.in).useLocale(US);

    public void startSaleProcess() {

        System.out.println("--- Cadastro de Pacote de Viagem ---");
        // --- 1. OBTÉM DADOS DO TRANSPORTE ---
        System.out.println("\n[1] Dados do Transporte:");

        String transportType;
        // Validador para entrado de apenas os tipo de transporte
        do {
            transportType = readValidString("Informe o tipo de transporte (aéreo, rodoviário, marítimo): ");

            if (!transportType.equalsIgnoreCase("aéreo") &&
                    !transportType.equalsIgnoreCase("rodoviário") &&
                    !transportType.equalsIgnoreCase("marítimo")) {

                System.out.println("""
                        "Por favor, escolha 'aéreo', 'rodoviário' ou 'marítimo':\s""");
            }

        } while (!transportType.equalsIgnoreCase("aéreo") &&
                !"rodoviário".equalsIgnoreCase(transportType) &&
                !transportType.equalsIgnoreCase("marítimo"));

        BigDecimal transportValue = readValidBigDecimal(
                "Informe o valor do transporte (Dólar, ex: 500.00): US$ "
        );

        // Cria o objeto Transporte
        Transport transport = new Transport(transportType, transportValue);

        // --- 2. OBTENÇÃO DA HOSPEDAGEM ---
        System.out.println("\n[2] Dados para Hospedagem:");
        String description = readValidString(""" 
                Informe a descrição da hospedagem: \s
                (Ex: Nome do hotel, tipo de quarto): \s""");

        BigDecimal priceDay = readValidBigDecimal(
                "Informe o valor de diária (Dólar, ex: 50.00): US$ "
        );

        // Cria o objeto Hospedagem
        Accommodation accommodation = new Accommodation(description, priceDay);

        // --- 3. OBTENÇÃO DO PACOTE DE VIAGEM ---
        System.out.println("\n[3] Dados para o pacote de viagem");
        String destiny = readValidString("Informe o destino desejado: ");

        int totalDays = readValidPositiveInt("Informe a quantidades de dias: ");

        // Cria o objeto pacote de viagem
        TravelPackage travelPackage = new TravelPackage(
                transport,
                accommodation,
                destiny,
                totalDays);


        // --- 4. OBTENÇÃO PARA VENDA ---
        System.out.println("\n[4] Dados para venda");
        String name = readValidString("Informe o seu nome: ");

        String methodPay;

        do {
            methodPay =  readValidString("Informe o método de pagamento (Cartão | Pix | Dinheiro): ");;

            if (!methodPay.equalsIgnoreCase("Cartão") &&
                    !methodPay.equalsIgnoreCase("Pix") &&
                    !methodPay.equalsIgnoreCase("Dinheiro")) {

                System.out.println("""
                        "Por favor, escolha 'Cartão', 'Pix' ou 'Dinheiro': \s""");
            }

        } while (!methodPay.equalsIgnoreCase("Cartão") &&
                !"Pix".equalsIgnoreCase(methodPay) &&
                !methodPay.equalsIgnoreCase("Dinheiro"));

        // Cria o objeto pacote de viagem
        Sale sale = new Sale(name, methodPay, travelPackage);

        // --- 4. OBTENÇÃO PARA LUCRO, TAXAS, COTAÇÃO ---
        System.out.println("\n[5] Dados Lucro, Taxas, Cotação");
        BigDecimal margin =  readValidBigDecimal(""" 
                        Informe a margem de lucro a ser aplicada \s
                        (Porcentagem, ex: 10.00): \s""");

        BigDecimal fees = readValidBigDecimal("""
                        Informe o valor das taxas adicionais \s
                        (Dólar, ex: 15.00): US$ \s""");;

        BigDecimal dollarQuote = readValidBigDecimal("""
                        Informe a cotação do dólar no dia \s
                        (ex: 5.20): R$ \s""");

        System.out.println("=========================================");
        System.out.printf("Usuário: %s\n", sale.getNameClient());

        // mostrando na tela todas as informações da venda
        sale.showSaleTotals(margin, fees, dollarQuote);
    }



    /**
     * ESTE MÉTODO AGORA É UM LEITOR ROBUSTO QUE FORÇA A ENTRADA VÁLIDA
     */
    private BigDecimal readValidBigDecimal(String promptMessage) {
        BigDecimal value;

        while (true) {

            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();

            try {
                value = new BigDecimal(input);

                // Validação: Checa se o valor é negativo
                if (value.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("❌ ERRO: O valor não pode ser negativo. Tente novamente.");

                } else {
                    // Se o valor é um número válido e não negativo
                    return value;
                }

            } catch (NumberFormatException e) {
                // Validação: Checa se a entrada é um número (trata letras/caracteres)
                System.out.println("""
                    ❌ ERRO: Entrada inválida.
                    Por favor, digite apenas números (use ponto '.' como separador decimal).""");
            }
        }
    }

    /**
     * método para ler e validar números inteiros positivos (Ex: quantidade de dias)
     */
    private int readValidPositiveInt(String promptMessage) {
        int value;

        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim(); // Lemos como String

            try {
                // Tenta converter a String para Int
                value = Integer.parseInt(input);

                // Validação: Checa se o número é positivo (> 0)
                if (value <= 0) {
                    System.out.println("❌ ERRO: A quantidade deve ser um número inteiro positivo e maior que zero. Tente novamente.");
                } else {
                    // Se chegou aqui, o valor é um número válido e positivo
                    return value; // SAI DO LOOP
                }

            } catch (NumberFormatException e) {
                // 2. Validação: Checa se a entrada é um número (trata letras/caracteres)
                System.out.println("❌ ERRO: Entrada inválida. Por favor, digite apenas números inteiros.");
            }
        }
    }

    /**
     * Método para ler uma String, garantindo que ela não seja vazia após remover espaços
     */
    private String readValidString(String promptMessage) {
        String input;

        while (true) {
            System.out.print(promptMessage);

            //  Lemos a linha e removemos os espaços em branco no início e fim
            input = scanner.nextLine().trim();

            //  Checa se, após remover os espaços, a string está vazia
            if (input.isEmpty()) {
                System.out.println("""
                        ❌ ERRO: A entrada não pode ser vazia. Por favor, \s
                        digite uma informação válida.\s""");
                // O loop continua e o prompt é reimpresso
            } else {
                // a string é válida (não está vazia)
                return input; // SUCESSO: SAI DO LOOP
            }
        }
    }
}