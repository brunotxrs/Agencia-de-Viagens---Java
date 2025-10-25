package br.com.agency.entities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static java.math.RoundingMode.HALF_UP;

public class Sale {
    private String nameUser;
    private String paymentMethod;
    private final TravelPackage packageTrip;

    public Sale(String nameUser, String paymentMethod, TravelPackage travelPackage) {
        this.nameUser = nameUser;
        this.paymentMethod = paymentMethod;
        this.packageTrip = travelPackage;
    }

    public String getNameClient() {
        return nameUser;
    }
    public void setNameClient(String nameUser) {
        this.nameUser = nameUser;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // converter um valor em reais a partir de um valor informado em dólar e da cotação
    public static BigDecimal convertToReal(BigDecimal valueInDollar, BigDecimal dollarQuote){
        BigDecimal valueInReal = valueInDollar.multiply(dollarQuote);
        return valueInReal.setScale(2, HALF_UP);
    }

    // acesso para obter o total do pacote em Dólar
    public BigDecimal getPackageTotalInDollar(BigDecimal margin, BigDecimal fees) {
        return this.packageTrip.calculateThePackageTotal(margin, fees);
    }

    // mostrar na tela o total do pacote de viagem em dólar e em reais.
    public void showSaleTotals(BigDecimal margin, BigDecimal fees, BigDecimal dollarQuote) {

        Locale localeBR = new Locale("pt", "BR");

        NumberFormat brFormat = NumberFormat.getCurrencyInstance(localeBR); // Formata como R$ 1.234,56

        // 2. Cria o formatador americano (para exibir o dólar de forma clara)
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US); // Formata como US$ 1,234.56

        // 3. Cria um formatador simples para a Cotação (sem símbolo monetário)
        NumberFormat quoteFormat = NumberFormat.getNumberInstance(localeBR);

        quoteFormat.setMinimumFractionDigits(4); // Garante 4 casas para pre

        // Obtém o valor total do pacote (que está em Dólar)
        BigDecimal totalDollar = this.getPackageTotalInDollar(margin, fees);

        // Converte o valor para Real usando o method estático
        BigDecimal totalReal = convertToReal(totalDollar, dollarQuote);

        // Mostrar na tela
        System.out.println("------------ Totais da Venda ------------");
        System.out.println("Valor Total do Pacote (Dólar): " + usFormat.format(totalDollar));
        System.out.println("Cotação do Dólar utilizada: " + quoteFormat.format(dollarQuote));
        System.out.println("Valor Total do Pacote (Reais): " + brFormat.format(totalReal));
        System.out.println("-----------------------------------------");
    }

}