package br.com.tsg_innovations.transactions_microservice.transaction;

import br.com.tsg_innovations.transactions_microservice.authorizer.AuthorizerService;
import br.com.tsg_innovations.transactions_microservice.exceptions.InvalidTransactionException;
import br.com.tsg_innovations.transactions_microservice.notification.NotificationService;
import br.com.tsg_innovations.transactions_microservice.wallet.WalletRepository;
import br.com.tsg_innovations.transactions_microservice.wallet.WalletType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TransactionService {
    private static Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(
            TransactionRepository transactionRepository,
            WalletRepository walletRepository,
            AuthorizerService authorizerService,
            NotificationService notificationService
    ){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.notificationService = notificationService;
        this.authorizerService = authorizerService;
    }

    @Transactional
    public Transaction create(Transaction transaction){
        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizerService.authorize(transaction);
        notificationService.notify(newTransaction);

        return newTransaction;
    }

    /*
    * A transaction is only valid when:
    * - the payer is a common wallet and has enough balance
    * - the payer is not the payee
    * */
    private void validate(Transaction transaction) {
        LOGGER.info("Validating transaction: ", transaction);

        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(
                                payer -> payer.type() == WalletType.COMUM.getValue() &&
                                payer.balance().compareTo(transaction.value()) >= 0 &&
                                !payer.id().equals(transaction.payee()) ? true : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid transaction: " + transaction)
                        ))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid Transaction: " + transaction));
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
