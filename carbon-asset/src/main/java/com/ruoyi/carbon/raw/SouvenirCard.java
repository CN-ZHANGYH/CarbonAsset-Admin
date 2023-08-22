package com.ruoyi.carbon.raw;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple7;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class SouvenirCard extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506117ce806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c80638cf412fc116100665780638cf412fc14610158578063978c5c2a14610188578063a6acd969146101be578063c2bf6978146101ee578063faad477e1461020a57610093565b80630f5a0a3c146100985780631e41e0de146100c857806320cb631d146100f85780636a9e476214610128575b600080fd5b6100b260048036038101906100ad9190610ffb565b610226565b6040516100bf9190611509565b60405180910390f35b6100e260048036038101906100dd919061103c565b610603565b6040516100ef91906115a8565b60405180910390f35b610112600480360381019061010d9190611090565b610647565b60405161011f91906115a8565b60405180910390f35b610142600480360381019061013d9190611090565b610668565b60405161014f9190611586565b60405180910390f35b610172600480360381019061016d919061103c565b610982565b60405161017f919061152b565b60405180910390f35b6101a2600480360381019061019d9190611090565b610a44565b6040516101b597969594939291906115c3565b60405180910390f35b6101d860048036038101906101d39190611090565b610ce6565b6040516101e5919061152b565b60405180910390f35b6102086004803603810190610203919061103c565b610d17565b005b610224600480360381019061021f91906110b9565b610da8565b005b60608060018360405161023991906114f2565b908152602001604051809103902080548060200260200160405190810160405280929190818152602001828054801561029157602002820191906000526020600020905b81548152602001906001019080831161027d575b505050505090506060815167ffffffffffffffff811180156102b257600080fd5b506040519080825280602002602001820160405280156102ec57816020015b6102d9610eb0565b8152602001906001900390816102d15790505b50905060008090505b82518110156105f85760008084838151811061030d57fe5b602002602001015181526020019081526020016000206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103db5780601f106103b0576101008083540402835291602001916103db565b820191906000526020600020905b8154815290600101906020018083116103be57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561047d5780601f106104525761010080835404028352916020019161047d565b820191906000526020600020905b81548152906001019060200180831161046057829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561051f5780601f106104f45761010080835404028352916020019161051f565b820191906000526020600020905b81548152906001019060200180831161050257829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105c15780601f10610596576101008083540402835291602001916105c1565b820191906000526020600020905b8154815290600101906020018083116105a457829003601f168201915b505050505081526020016006820154815250508282815181106105e057fe5b602002602001018190525080806001019150506102f5565b508092505050919050565b600182805160208101820180518482526020830160208501208183528095505050505050818154811061063257fe5b90600052602060002001600091509150505481565b6002818154811061065457fe5b906000526020600020016000915090505481565b610670610eb0565b61067982610ce6565b6106b8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106af90611546565b60405180910390fd5b6000808381526020019081526020016000206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107825780601f1061075757610100808354040283529160200191610782565b820191906000526020600020905b81548152906001019060200180831161076557829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108245780601f106107f957610100808354040283529160200191610824565b820191906000526020600020905b81548152906001019060200180831161080757829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108c65780601f1061089b576101008083540402835291602001916108c6565b820191906000526020600020905b8154815290600101906020018083116108a957829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109685780601f1061093d57610100808354040283529160200191610968565b820191906000526020600020905b81548152906001019060200180831161094b57829003601f168201915b505050505081526020016006820154815250509050919050565b6000606060018460405161099691906114f2565b90815260200160405180910390208054806020026020016040519081016040528092919081815260200182805480156109ee57602002820191906000526020600020905b8154815260200190600101908083116109da575b5050505050905060008090505b8151811015610a375783828281518110610a1157fe5b60200260200101511415610a2a57600192505050610a3e565b80806001019150506109fb565b5060009150505b92915050565b6000602052806000526040600020600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610afc5780601f10610ad157610100808354040283529160200191610afc565b820191906000526020600020905b815481529060010190602001808311610adf57829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b9a5780601f10610b6f57610100808354040283529160200191610b9a565b820191906000526020600020905b815481529060010190602001808311610b7d57829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c385780601f10610c0d57610100808354040283529160200191610c38565b820191906000526020600020905b815481529060010190602001808311610c1b57829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cd65780601f10610cab57610100808354040283529160200191610cd6565b820191906000526020600020905b815481529060010190602001808311610cb957829003601f168201915b5050505050908060060154905087565b6000806000808481526020019081526020016000206000015414610d0d5760019050610d12565b600090505b919050565b610d2081610ce6565b610d5f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d5690611546565b60405180910390fd5b600182604051610d6f91906114f2565b90815260200160405180910390208190806001815401808255809150506001900390600052602060002001600090919091909150555050565b610db187610ce6565b15610df1576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610de890611566565b60405180910390fd5b6000806000898152602001908152602001600020905087816000018190555085816002019080519060200190610e28929190610eed565b5084816003019080519060200190610e41929190610eed565b5083816004019080519060200190610e5a929190610eed565b5082816005019080519060200190610e73929190610eed565b5081816006018190555060028890806001815401808255809150506001900390600052602060002001600090919091909150555050505050505050565b6040518060e00160405280600081526020016000815260200160608152602001606081526020016060815260200160608152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f2e57805160ff1916838001178555610f5c565b82800160010185558215610f5c579182015b82811115610f5b578251825591602001919060010190610f40565b5b509050610f699190610f6d565b5090565b610f8f91905b80821115610f8b576000816000905550600101610f73565b5090565b90565b600082601f830112610fa357600080fd5b8135610fb6610fb18261167b565b61164e565b91508082526020830160208301858383011115610fd257600080fd5b610fdd83828461172e565b5050","5092915050565b600081359050610ff581611781565b92915050565b60006020828403121561100d57600080fd5b600082013567ffffffffffffffff81111561102757600080fd5b61103384828501610f92565b91505092915050565b6000806040838503121561104f57600080fd5b600083013567ffffffffffffffff81111561106957600080fd5b61107585828601610f92565b925050602061108685828601610fe6565b9150509250929050565b6000602082840312156110a257600080fd5b60006110b084828501610fe6565b91505092915050565b600080600080600080600060e0888a0312156110d457600080fd5b60006110e28a828b01610fe6565b97505060206110f38a828b01610fe6565b965050604088013567ffffffffffffffff81111561111057600080fd5b61111c8a828b01610f92565b955050606088013567ffffffffffffffff81111561113957600080fd5b6111458a828b01610f92565b945050608088013567ffffffffffffffff81111561116257600080fd5b61116e8a828b01610f92565b93505060a088013567ffffffffffffffff81111561118b57600080fd5b6111978a828b01610f92565b92505060c06111a88a828b01610fe6565b91505092959891949750929550565b60006111c38383611372565b905092915050565b60006111d6826116b7565b6111e081856116da565b9350836020820285016111f2856116a7565b8060005b8581101561122e578484038952815161120f85826111b7565b945061121a836116cd565b925060208a019950506001810190506111f6565b50829750879550505050505092915050565b61124981611718565b82525050565b600061125a826116c2565b61126481856116eb565b935061127481856020860161173d565b61127d81611770565b840191505092915050565b6000611293826116c2565b61129d81856116fc565b93506112ad81856020860161173d565b6112b681611770565b840191505092915050565b60006112cc826116c2565b6112d6818561170d565b93506112e681856020860161173d565b80840191505092915050565b60006112ff601b836116fc565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e4b88de5ad98e59ca800000000006000830152602082019050919050565b600061133f601e836116fc565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e5b7b2e7bb8fe5ad98e59ca800006000830152602082019050919050565b600060e08301600083015161138a60008601826114d4565b50602083015161139d60208601826114d4565b50604083015184820360408601526113b5828261124f565b915050606083015184820360608601526113cf828261124f565b915050608083015184820360808601526113e9828261124f565b91505060a083015184820360a0860152611403828261124f565b91505060c083015161141860c08601826114d4565b508091505092915050565b600060e08301600083015161143b60008601826114d4565b50602083015161144e60208601826114d4565b5060408301518482036040860152611466828261124f565b91505060608301518482036060860152611480828261124f565b9150506080830151848203608086015261149a828261124f565b91505060a083015184820360a08601526114b4828261124f565b91505060c08301516114c960c08601826114d4565b508091505092915050565b6114dd81611724565b82525050565b6114ec81611724565b82525050565b60006114fe82846112c1565b915081905092915050565b6000602082019050818103600083015261152381846111cb565b905092915050565b60006020820190506115406000830184611240565b92915050565b6000602082019050818103600083015261155f816112f2565b9050919050565b6000602082019050818103600083015261157f81611332565b9050919050565b600060208201905081810360008301526115a08184611423565b905092915050565b60006020820190506115bd60008301846114e3565b92915050565b600060e0820190506115d8600083018a6114e3565b6115e560208301896114e3565b81810360408301526115f78188611288565b9050818103606083015261160b8187611288565b9050818103608083015261161f8186611288565b905081810360a08301526116338185611288565b905061164260c08301846114e3565b98975050505050505050565b6000604051905081810181811067ffffffffffffffff8211171561167157600080fd5b8060405250919050565b600067ffffffffffffffff82111561169257600080fd5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60008115159050919050565b6000819050919050565b82818337600083830152505050565b60005b8381101561175b578082015181840152602081019050611740565b8381111561176a576000848401525b50505050565b6000601f19601f8301169050919050565b61178a81611724565b811461179557600080fd5b5056fea2646970667358221220ec720d9ea0777ace3151e4e0599d1ec8bd89e90c3b53d773ac4a53b3130e508264736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"IsCardExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"QueryCardInfo\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo\",\"name\":\"\",\"type\":\"tuple\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardList\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"QueryEnterpriseIsHasCard\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_credit\",\"type\":\"uint256\"}],\"name\":\"RegisterCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"UserBindCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"UserOfCardListMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CARDINFOLIST = "CardInfoList";

    public static final String FUNC_CARDINFOMAP = "CardInfoMap";

    public static final String FUNC_ISCARDEXIST = "IsCardExist";

    public static final String FUNC_QUERYCARDINFO = "QueryCardInfo";

    public static final String FUNC_QUERYENTERPRISECARDLIST = "QueryEnterpriseCardList";

    public static final String FUNC_QUERYENTERPRISEISHASCARD = "QueryEnterpriseIsHasCard";

    public static final String FUNC_REGISTERCARD = "RegisterCard";

    public static final String FUNC_USERBINDCARD = "UserBindCard";

    public static final String FUNC_USEROFCARDLISTMAP = "UserOfCardListMap";

    protected SouvenirCard(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public BigInteger CardInfoList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_CARDINFOLIST,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> CardInfoMap(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_CARDINFOMAP,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (BigInteger) results.get(6).getValue());
    }

    public TransactionReceipt IsCardExist(BigInteger _cardId) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] IsCardExist(BigInteger _cardId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForIsCardExist(BigInteger _cardId) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getIsCardExistInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ISCARDEXIST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getIsCardExistOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ISCARDEXIST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public TransactionReceipt QueryCardInfo(BigInteger _cardId) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryCardInfo(BigInteger _cardId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryCardInfo(BigInteger _cardId) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getQueryCardInfoInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<CardInfo> getQueryCardInfoOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<CardInfo>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<CardInfo>(

                (CardInfo) results.get(0)
                );
    }

    public TransactionReceipt QueryEnterpriseCardList(String _enterpriseName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDLIST,
                Arrays.<Type>asList(new Utf8String(_enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryEnterpriseCardList(String _enterpriseName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDLIST,
                Arrays.<Type>asList(new Utf8String(_enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryEnterpriseCardList(String _enterpriseName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDLIST,
                Arrays.<Type>asList(new Utf8String(_enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getQueryEnterpriseCardListInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYENTERPRISECARDLIST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<DynamicArray<CardInfo>> getQueryEnterpriseCardListOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_QUERYENTERPRISECARDLIST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<CardInfo>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<DynamicArray<CardInfo>>(

                (DynamicArray<CardInfo>) results.get(0)
                );
    }

    public TransactionReceipt QueryEnterpriseIsHasCard(String _enterpriseName, BigInteger _cardId) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryEnterpriseIsHasCard(String _enterpriseName, BigInteger _cardId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryEnterpriseIsHasCard(String _enterpriseName, BigInteger _cardId) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getQueryEnterpriseIsHasCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getQueryEnterpriseIsHasCardOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public TransactionReceipt RegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty),
                new Uint256(_credit)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] RegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty),
                new Uint256(_credit)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty),
                new Uint256(_credit)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> getRegisterCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTERCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (BigInteger) results.get(6).getValue()
                );
    }

    public TransactionReceipt UserBindCard(String _enterpriseName, BigInteger _cardId) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] UserBindCard(String _enterpriseName, BigInteger _cardId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUserBindCard(String _enterpriseName, BigInteger _cardId) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Uint256(_cardId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getUserBindCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_USERBINDCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue()
                );
    }

    public BigInteger UserOfCardListMap(String param0, BigInteger param1) throws ContractException {
        final Function function = new Function(FUNC_USEROFCARDLISTMAP,
                Arrays.<Type>asList(new Utf8String(param0),
                new Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public static SouvenirCard load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new SouvenirCard(contractAddress, client, credential);
    }

    public static SouvenirCard deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(SouvenirCard.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class CardInfo extends DynamicStruct {
        public BigInteger cardId;

        public BigInteger level;

        public String cardName;

        public String cardDesc;

        public String cardUrl;

        public String category;

        public BigInteger credit;

        public CardInfo(Uint256 cardId, Uint256 level, Utf8String cardName, Utf8String cardDesc, Utf8String cardUrl, Utf8String category, Uint256 credit) {
            super(cardId,level,cardName,cardDesc,cardUrl,category,credit);
            this.cardId = cardId.getValue();
            this.level = level.getValue();
            this.cardName = cardName.getValue();
            this.cardDesc = cardDesc.getValue();
            this.cardUrl = cardUrl.getValue();
            this.category = category.getValue();
            this.credit = credit.getValue();
        }

        public CardInfo(BigInteger cardId, BigInteger level, String cardName, String cardDesc, String cardUrl, String category, BigInteger credit) {
            super(new Uint256(cardId),new Uint256(level),new Utf8String(cardName),new Utf8String(cardDesc),new Utf8String(cardUrl),new Utf8String(category),new Uint256(credit));
            this.cardId = cardId;
            this.level = level;
            this.cardName = cardName;
            this.cardDesc = cardDesc;
            this.cardUrl = cardUrl;
            this.category = category;
            this.credit = credit;
        }
    }
}
