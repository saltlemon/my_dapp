package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Asset extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b50610df2806100206000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630d8912f3146100a957806327e235e3146101005780633084db571461015757806345f003fb146101e45780635224372c14610265578063867904b4146102d2578063b3d761881461031f578063eccb5dbd1461038c578063f05e6a1a146103e3578063f8b2cb4f14610450575b600080fd5b3480156100b557600080fd5b506100ea600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104a7565b6040518082815260200191505060405180910390f35b34801561010c57600080fd5b50610141600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104f0565b6040518082815260200191505060405180910390f35b34801561016357600080fd5b506101e2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610508565b005b3480156101f057600080fd5b5061024f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610746565b6040518082815260200191505060405180910390f35b34801561027157600080fd5b506102d0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506108b2565b005b3480156102de57600080fd5b5061031d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610a72565b005b34801561032b57600080fd5b5061038a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610ac2565b005b34801561039857600080fd5b506103cd600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c7d565b6040518082815260200191505060405180910390f35b3480156103ef57600080fd5b5061044e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610c95565b005b34801561045c57600080fd5b50610491600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d7e565b6040518082815260200191505060405180910390f35b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60006020528060005260406000206000915090505481565b80600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101561059157610740565b80600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555080600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555080600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555080600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b50505050565b600081600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020540110156107d4576108ab565b816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555081600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b9392505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410156108fd57610a6d565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540392505081905550806000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555080600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b505050565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505050565b806000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015610b5557806000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825403925050819055505b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555080600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540392505081905550505050565b60026020528060005260406000206000915090505481565b806000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015610ce057610d79565b806000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540392505081905550806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490509190505600a165627a7a723058209e1d6957ca2ad1ae641bcb65294d07b973aefdd96451e51421a5eff3cd7d07650029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"_address\",\"type\":\"address\"}],\"name\":\"getBond\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"balances\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from_address\",\"type\":\"address\"},{\"name\":\"to_address\",\"type\":\"address\"},{\"name\":\"borrower_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"send_bond\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"bank_address\",\"type\":\"address\"},{\"name\":\"name_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"financ\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"borrower_address\",\"type\":\"address\"},{\"name\":\"lander_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"borrow\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"issue\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"borrower_address\",\"type\":\"address\"},{\"name\":\"lander_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"pay\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"ownBonds\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from_address\",\"type\":\"address\"},{\"name\":\"to_address\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"send_money\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_address\",\"type\":\"address\"}],\"name\":\"getBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":true,\"name\":\"account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"asset_value\",\"type\":\"uint256\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":true,\"name\":\"from_account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"to_account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"TransferEvent\",\"type\":\"event\"}]";

    public static final String FUNC_GETBOND = "getBond";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_SEND_BOND = "send_bond";

    public static final String FUNC_FINANC = "financ";

    public static final String FUNC_BORROW = "borrow";

    public static final String FUNC_ISSUE = "issue";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_OWNBONDS = "ownBonds";

    public static final String FUNC_SEND_MONEY = "send_money";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFEREVENT_EVENT = new Event("TransferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> getBond(String _address) {
        final Function function = new Function(
                FUNC_GETBOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getBond(String _address, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETBOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getBondSeq(String _address) {
        final Function function = new Function(
                FUNC_GETBOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> send_bond(String from_address, String to_address, String borrower_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND_BOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void send_bond(String from_address, String to_address, String borrower_address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SEND_BOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String send_bondSeq(String from_address, String to_address, String borrower_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND_BOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> financ(String bank_address, String name_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANC, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(name_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void financ(String bank_address, String name_address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_FINANC, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(name_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String financSeq(String bank_address, String name_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANC, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(name_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> borrow(String borrower_address, String lander_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_BORROW, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void borrow(String borrower_address, String lander_address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BORROW, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String borrowSeq(String borrower_address, String lander_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_BORROW, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> issue(String _address, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void issue(String _address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String issueSeq(String _address, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> pay(String borrower_address, String lander_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void pay(String borrower_address, String lander_address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String paySeq(String borrower_address, String lander_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(borrower_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(lander_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> ownBonds(String param0) {
        final Function function = new Function(FUNC_OWNBONDS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> send_money(String from_address, String to_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND_MONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void send_money(String from_address, String to_address, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SEND_MONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String send_moneySeq(String from_address, String to_address, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND_MONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from_address), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to_address), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> getBalance(String _address) {
        final Function function = new Function(
                FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getBalance(String _address, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getBalanceSeq(String _address) {
        final Function function = new Function(
                FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_address)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.asset_value = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRegisterEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRegisterEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from_account = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to_account = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public byte[] account;

        public BigInteger asset_value;

        public BigInteger ret;
    }

    public static class TransferEventEventResponse {
        public Log log;

        public byte[] from_account;

        public byte[] to_account;

        public BigInteger amount;

        public BigInteger ret;
    }
}
