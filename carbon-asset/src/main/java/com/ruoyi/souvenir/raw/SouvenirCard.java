package com.ruoyi.souvenir.raw;

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
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple6;
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
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061191d806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c806330ce92131161005b57806330ce92131461014d57806340dd3b841461018257806390d3f9a41461019e578063a798e5ac146101ce57610088565b80630f5a0a3c1461008d57806313eb8af6146100bd5780631e41e0de146100ed57806320cb631d1461011d575b600080fd5b6100a760048036038101906100a29190611105565b6101ea565b6040516100b49190611644565b60405180910390f35b6100d760048036038101906100d29190611105565b610659565b6040516100e491906116e3565b60405180910390f35b610107600480360381019061010291906111b2565b610975565b6040516101149190611681565b60405180910390f35b61013760048036038101906101329190611206565b610a51565b6040516101449190611705565b60405180910390f35b61016760048036038101906101629190611105565b610a72565b60405161017996959493929190611720565b60405180910390f35b61019c60048036038101906101979190611146565b610d24565b005b6101b860048036038101906101b39190611105565b610df4565b6040516101c59190611666565b60405180910390f35b6101e860048036038101906101e3919061122f565b610e31565b005b6060806001836040516101fd919061162d565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b828210156102e7578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d35780601f106102a8576101008083540402835291602001916102d3565b820191906000526020600020905b8154815290600101906020018083116102b657829003601f168201915b50505050508152602001906001019061022b565b5050505090506060815167ffffffffffffffff8111801561030757600080fd5b5060405190808252806020026020018201604052801561034157816020015b61032e610f3a565b8152602001906001900390816103265790505b50905060008090505b8251811161064e57600083828151811061036057fe5b6020026020010151604051610375919061162d565b90815260200160405180910390206040518060c00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561043b5780601f106104105761010080835404028352916020019161043b565b820191906000526020600020905b81548152906001019060200180831161041e57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104dd5780601f106104b2576101008083540402835291602001916104dd565b820191906000526020600020905b8154815290600101906020018083116104c057829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561057f5780601f106105545761010080835404028352916020019161057f565b820191906000526020600020905b81548152906001019060200180831161056257829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106215780601f106105f657610100808354040283529160200191610621565b820191906000526020600020905b81548152906001019060200180831161060457829003601f168201915b50505050508152505082828151811061063657fe5b6020026020010181905250808060010191505061034a565b508092505050919050565b610661610f3a565b61066a82610df4565b6106a9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106a0906116a3565b60405180910390fd5b6000826040516106b9919061162d565b90815260200160405180910390206040518060c00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561077f5780601f106107545761010080835404028352916020019161077f565b820191906000526020600020905b81548152906001019060200180831161076257829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108215780601f106107f657610100808354040283529160200191610821565b820191906000526020600020905b81548152906001019060200180831161080457829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108c35780601f10610898576101008083540402835291602001916108c3565b820191906000526020600020905b8154815290600101906020018083116108a657829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109655780601f1061093a57610100808354040283529160200191610965565b820191906000526020600020905b81548152906001019060200180831161094857829003601f168201915b5050505050815250509050919050565b60018280516020810182018051848252602083016020850120818352809550505050505081815481106109a457fe5b90600052602060002001600091509150508054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a495780601f10610a1e57610100808354040283529160200191610a49565b820191906000526020600020905b815481529060010190602001808311610a2c57829003601f168201915b505050505081565b60028181548110610a5e57fe5b906000526020600020016000915090505481565b600081805160208101820180518482526020830160208501208183528095505050505050600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b405780601f10610b1557610100808354040283529160200191610b40565b820191906000526020600020905b815481529060010190602001808311610b2357829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bde5780601f10610bb357610100808354040283529160200191610bde565b820191906000526020600020905b815481529060010190602001808311610bc157829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c7c5780601f10610c5157610100808354040283529160200191610c7c565b820191906000526020600020905b815481529060010190602001808311610c5f57829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d1a5780601f10610cef57610100808354040283529160200191610d1a565b820191906000526020600020905b815481529060010190602001808311610cfd57829003601f168201915b5050505050905086565b610d2d81610df4565b610d6c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d63906116a3565b60405180910390fd5b600182604051610d7c919061162d565b9081526020016040518091039020600082604051610d9a919061162d565b9081526020016040518091039020600201908060018154018082558091505060019003906000526020600020016000909190919091509080546001816001161561010002031660029004610def929190610f70565b505050565b600080600083604051610e07919061162d565b90815260200160405180910390206000015414610e275760019050610e2c565b600090505b919050565b610e3a84610df4565b15610e7a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e71906116c3565b60405180910390fd5b60008085604051610e8b919061162d565b9081526020016040518091039020905086816000018190555084816002019080519060200190610ebc929190610ff7565b5083816003019080519060200190610ed5929190610ff7565b5082816004019080519060200190610eee929190610ff7565b5081816005019080519060200190610f07929190610ff7565b50600287908060018154018082558091505060019003906000526020600020016000909190919091505550505050505050565b6040518060c001604052806000815260200160008152602001606081526020016060815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610fa95780548555610fe6565b82800160010185558215610fe657600052602060002091601f016020900482015b82811115610fe55782548255916001019190600101","90610fca565b5b509050610ff39190611077565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061103857805160ff1916838001178555611066565b82800160010185558215611066579182015b8281111561106557825182559160200191906001019061104a565b5b5090506110739190611077565b5090565b61109991905b8082111561109557600081600090555060010161107d565b5090565b90565b600082601f8301126110ad57600080fd5b81356110c06110bb826117ca565b61179d565b915080825260208301602083018583830111156110dc57600080fd5b6110e783828461187d565b50505092915050565b6000813590506110ff816118d0565b92915050565b60006020828403121561111757600080fd5b600082013567ffffffffffffffff81111561113157600080fd5b61113d8482850161109c565b91505092915050565b6000806040838503121561115957600080fd5b600083013567ffffffffffffffff81111561117357600080fd5b61117f8582860161109c565b925050602083013567ffffffffffffffff81111561119c57600080fd5b6111a88582860161109c565b9150509250929050565b600080604083850312156111c557600080fd5b600083013567ffffffffffffffff8111156111df57600080fd5b6111eb8582860161109c565b92505060206111fc858286016110f0565b9150509250929050565b60006020828403121561121857600080fd5b6000611226848285016110f0565b91505092915050565b60008060008060008060c0878903121561124857600080fd5b600061125689828a016110f0565b965050602061126789828a016110f0565b955050604087013567ffffffffffffffff81111561128457600080fd5b61129089828a0161109c565b945050606087013567ffffffffffffffff8111156112ad57600080fd5b6112b989828a0161109c565b935050608087013567ffffffffffffffff8111156112d657600080fd5b6112e289828a0161109c565b92505060a087013567ffffffffffffffff8111156112ff57600080fd5b61130b89828a0161109c565b9150509295509295509295565b600061132483836114d3565b905092915050565b600061133782611806565b6113418185611829565b935083602082028501611353856117f6565b8060005b8581101561138f57848403895281516113708582611318565b945061137b8361181c565b925060208a01995050600181019050611357565b50829750879550505050505092915050565b6113aa81611867565b82525050565b60006113bb82611811565b6113c5818561183a565b93506113d581856020860161188c565b6113de816118bf565b840191505092915050565b60006113f482611811565b6113fe818561184b565b935061140e81856020860161188c565b611417816118bf565b840191505092915050565b600061142d82611811565b611437818561185c565b935061144781856020860161188c565b80840191505092915050565b6000611460601b8361184b565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e4b88de5ad98e59ca800000000006000830152602082019050919050565b60006114a0601e8361184b565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e5b7b2e7bb8fe5ad98e59ca800006000830152602082019050919050565b600060c0830160008301516114eb600086018261160f565b5060208301516114fe602086018261160f565b506040830151848203604086015261151682826113b0565b9150506060830151848203606086015261153082826113b0565b9150506080830151848203608086015261154a82826113b0565b91505060a083015184820360a086015261156482826113b0565b9150508091505092915050565b600060c083016000830151611589600086018261160f565b50602083015161159c602086018261160f565b50604083015184820360408601526115b482826113b0565b915050606083015184820360608601526115ce82826113b0565b915050608083015184820360808601526115e882826113b0565b91505060a083015184820360a086015261160282826113b0565b9150508091505092915050565b61161881611873565b82525050565b61162781611873565b82525050565b60006116398284611422565b915081905092915050565b6000602082019050818103600083015261165e818461132c565b905092915050565b600060208201905061167b60008301846113a1565b92915050565b6000602082019050818103600083015261169b81846113e9565b905092915050565b600060208201905081810360008301526116bc81611453565b9050919050565b600060208201905081810360008301526116dc81611493565b9050919050565b600060208201905081810360008301526116fd8184611571565b905092915050565b600060208201905061171a600083018461161e565b92915050565b600060c082019050611735600083018961161e565b611742602083018861161e565b818103604083015261175481876113e9565b9050818103606083015261176881866113e9565b9050818103608083015261177c81856113e9565b905081810360a083015261179081846113e9565b9050979650505050505050565b6000604051905081810181811067ffffffffffffffff821117156117c057600080fd5b8060405250919050565b600067ffffffffffffffff8211156117e157600080fd5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60008115159050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156118aa57808201518184015260208101905061188f565b838111156118b9576000848401525b50505050565b6000601f19601f8301169050919050565b6118d981611873565b81146118e457600080fd5b5056fea26469706673582212206f36553a28886a269333915161d7ee708ec9981776b105422033386d5e3f307464736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"name\":\"CardInfoMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"IsCardExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"QueryCardInfo\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"}],\"internalType\":\"struct CardInfo\",\"name\":\"\",\"type\":\"tuple\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardList\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"}],\"internalType\":\"struct CardInfo[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"}],\"name\":\"RegisterCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"UserBindCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"UserOfCardListMap\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CARDINFOLIST = "CardInfoList";

    public static final String FUNC_CARDINFOMAP = "CardInfoMap";

    public static final String FUNC_ISCARDEXIST = "IsCardExist";

    public static final String FUNC_QUERYCARDINFO = "QueryCardInfo";

    public static final String FUNC_QUERYENTERPRISECARDLIST = "QueryEnterpriseCardList";

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

    public Tuple6<BigInteger, BigInteger, String, String, String, String> CardInfoMap(String param0) throws ContractException {
        final Function function = new Function(FUNC_CARDINFOMAP,
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple6<BigInteger, BigInteger, String, String, String, String>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue());
    }

    public TransactionReceipt IsCardExist(String _cardName) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] IsCardExist(String _cardName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForIsCardExist(String _cardName) {
        final Function function = new Function(
                FUNC_ISCARDEXIST,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getIsCardExistInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ISCARDEXIST,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
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

    public TransactionReceipt QueryCardInfo(String _cardName) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryCardInfo(String _cardName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryCardInfo(String _cardName) {
        final Function function = new Function(
                FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getQueryCardInfoInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYCARDINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
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

    public TransactionReceipt RegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] RegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRegisterCard(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty) {
        final Function function = new Function(
                FUNC_REGISTERCARD,
                Arrays.<Type>asList(new Uint256(_cardId),
                new Uint256(_level),
                new Utf8String(_cardName),
                new Utf8String(_cardDesc),
                new Utf8String(_cardUrl),
                new Utf8String(_categoty)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple6<BigInteger, BigInteger, String, String, String, String> getRegisterCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTERCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple6<BigInteger, BigInteger, String, String, String, String>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue()
                );
    }

    public TransactionReceipt UserBindCard(String _enterpriseName, String _cardName) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] UserBindCard(String _enterpriseName, String _cardName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUserBindCard(String _enterpriseName, String _cardName) {
        final Function function = new Function(
                FUNC_USERBINDCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getUserBindCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_USERBINDCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue()
                );
    }

    public String UserOfCardListMap(String param0, BigInteger param1) throws ContractException {
        final Function function = new Function(FUNC_USEROFCARDLISTMAP,
                Arrays.<Type>asList(new Utf8String(param0),
                new Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
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

        public CardInfo(Uint256 cardId, Uint256 level, Utf8String cardName, Utf8String cardDesc, Utf8String cardUrl, Utf8String category) {
            super(cardId,level,cardName,cardDesc,cardUrl,category);
            this.cardId = cardId.getValue();
            this.level = level.getValue();
            this.cardName = cardName.getValue();
            this.cardDesc = cardDesc.getValue();
            this.cardUrl = cardUrl.getValue();
            this.category = category.getValue();
        }

        public CardInfo(BigInteger cardId, BigInteger level, String cardName, String cardDesc, String cardUrl, String category) {
            super(new Uint256(cardId),new Uint256(level),new Utf8String(cardName),new Utf8String(cardDesc),new Utf8String(cardUrl),new Utf8String(category));
            this.cardId = cardId;
            this.level = level;
            this.cardName = cardName;
            this.cardDesc = cardDesc;
            this.cardUrl = cardUrl;
            this.category = category;
        }
    }
}