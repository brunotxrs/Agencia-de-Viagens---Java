package br.com.agency.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

public class TravelPackage {
    Transport transport;
    Accommodation accommodation;
    private String destination;
    private int numberDays;

    public TravelPackage(Transport transport, Accommodation accommodation, String destination, int numberDays) {
        this.transport = transport;
        this.accommodation = accommodation;
        this.destination = destination;
        this.numberDays = numberDays;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    // Methods for application

    // calcular o total de hospedagem a partir do número de dias e o valor da diária.
    public BigDecimal calculateTheTotalAccommodation(){
        BigDecimal days = new BigDecimal(this.numberDays);

        return accommodation.getDailyRate().multiply(days);
    }

    // Calcular valor de lucro a partir de uma margem informada (porcentagem) e valor informado.
    // O resultado retornado deve ser o valor + margem aplicada ao valor.
    public BigDecimal calculateProfitValue(BigDecimal baseValue, BigDecimal marginPercentage) {

        // 1. Converter a porcentagem (se vier como 10) para decimal (0.10).
        BigDecimal HUNDRED = new BigDecimal("100");

        // Calcula a taxa decimal: marginPercentage / 100
        BigDecimal marginRate = marginPercentage.divide(HUNDRED, 2, HALF_UP);

        // 2. Multiplica o valor base pela taxa de lucro: Lucro = baseValue * marginRate
        BigDecimal profitAmount = baseValue.multiply(marginRate);

        // 3. O resultado retornado deve ser o valor + margem aplicada ao valor
        BigDecimal finalValue = baseValue.add(profitAmount);

        // Arredonda para duas casas decimais
        return finalValue.setScale(2, HALF_UP);
    }

    // calcular e retornar o total do pacote, somando o transporte, o total da hospedagem
    // e os valores adicionais informados – margem de lucro (porcentagem) e taxas adicionais (valor monetário).
    public BigDecimal calculateThePackageTotal(BigDecimal marginPercentage, BigDecimal additionalFees){

        // Custo Base = Valor do Transporte + Total da Hospedagem
        BigDecimal baseCost = transport.getValue()
                .add(calculateTheTotalAccommodation());

        //  CALCULA O VALOR COM LUCRO
        BigDecimal valueWithProfit = calculateProfitValue(baseCost, marginPercentage);

        //  ADICIONA AS TAXAS ADICIONAIS
        // Total Final = (Custo + Lucro) + Taxas Adicionais
        return valueWithProfit.add(additionalFees);
    }

}
