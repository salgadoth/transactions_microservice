package br.com.tsg_innovations.transactions_microservice.wallet;

public enum WalletType {
    COMUM(1), LOJUSTA(2);

    private int value;

    private WalletType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
