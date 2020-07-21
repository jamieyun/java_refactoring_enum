import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TransactionType {
    BUY{
        @Override
        public void doTransactionOperation(Account account, BigDecimal cashValue) {
            BigDecimal taxPercentageValue = calculateTax(BUY_TAX, cashValue);
            cashValue = cashValue.add(taxPercentageValue);
            account.removeMoney(cashValue);
        }
    },
    SELL{
        @Override
        void doTransactionOperation(Account account, BigDecimal cashValue) {
            BigDecimal taxPercentageValue = calculateTax(SELL_TAX, cashValue);
            cashValue = cashValue.add(taxPercentageValue.negate());
            account.addMoney(cashValue);
        }
    },
    DEPOSIT{
        @Override
        void doTransactionOperation(Account account, BigDecimal cashValue) {
            BigDecimal taxPercentageValue = calculateTax(BigDecimal.valueOf(0.05), cashValue);
            cashValue = cashValue.add(taxPercentageValue.negate());
            account.addMoney(cashValue);
        }
    },
    WITHDRAWAL{
        @Override
        void doTransactionOperation(Account account, BigDecimal cashValue) {
            BigDecimal taxPercentageValue = calculateTax(BigDecimal.valueOf(0.20), cashValue);
            cashValue = cashValue.add(taxPercentageValue);
            account.removeMoney(cashValue);
        }
    };

    BigDecimal BUY_TAX = BigDecimal.valueOf(0.15);
    BigDecimal SELL_TAX = BigDecimal.valueOf(0.1);

    abstract void doTransactionOperation(Account account, BigDecimal cashValue);

    BigDecimal calculateTax(BigDecimal percentage, BigDecimal cashValue){
        return cashValue
                .multiply(percentage)
                .divide(new BigDecimal(100), RoundingMode.HALF_DOWN);
    }
}
