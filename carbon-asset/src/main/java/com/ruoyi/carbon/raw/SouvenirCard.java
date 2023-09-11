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
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611930806100206000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80638cf412fc116100715780638cf412fc1461019e578063978c5c2a146101ce578063a6acd96914610204578063bbb30ea714610234578063c2bf697814610250578063faad477e1461026c576100a9565b806304925a8d146100ae5780630f5a0a3c146100de5780631e41e0de1461010e57806320cb631d1461013e5780636a9e47621461016e575b600080fd5b6100c860048036038101906100c3919061115d565b610288565b6040516100d5919061170a565b60405180910390f35b6100f860048036038101906100f3919061115d565b6102b3565b604051610105919061166b565b60405180910390f35b6101286004803603810190610123919061119e565b610690565b604051610135919061170a565b60405180910390f35b610158600480360381019061015391906111f2565b6106d4565b604051610165919061170a565b60405180910390f35b610188600480360381019061018391906111f2565b6106f5565b60405161019591906116e8565b60405180910390f35b6101b860048036038101906101b3919061119e565b610a0f565b6040516101c5919061168d565b60405180910390f35b6101e860048036038101906101e391906111f2565b610ad1565b6040516101fb9796959493929190611725565b60405180910390f35b61021e600480360381019061021991906111f2565b610d73565b60405161022b919061168d565b60405180910390f35b61024e6004803603810190610249919061121b565b610da4565b005b61026a6004803603810190610265919061119e565b610e79565b005b6102866004803603810190610281919061121b565b610f0a565b005b600060018260405161029a9190611654565b9081526020016040518091039020805490509050919050565b6060806001836040516102c69190611654565b908152602001604051809103902080548060200260200160405190810160405280929190818152602001828054801561031e57602002820191906000526020600020905b81548152602001906001019080831161030a575b505050505090506060815167ffffffffffffffff8111801561033f57600080fd5b5060405190808252806020026020018201604052801561037957816020015b610366611012565b81526020019060019003908161035e5790505b50905060008090505b82518110156106855760008084838151811061039a57fe5b602002602001015181526020019081526020016000206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104685780601f1061043d57610100808354040283529160200191610468565b820191906000526020600020905b81548152906001019060200180831161044b57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561050a5780601f106104df5761010080835404028352916020019161050a565b820191906000526020600020905b8154815290600101906020018083116104ed57829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105ac5780601f10610581576101008083540402835291602001916105ac565b820191906000526020600020905b81548152906001019060200180831161058f57829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561064e5780601f106106235761010080835404028352916020019161064e565b820191906000526020600020905b81548152906001019060200180831161063157829003601f168201915b5050505050815260200160068201548152505082828151811061066d57fe5b60200260200101819052508080600101915050610382565b508092505050919050565b60018280516020810182018051848252602083016020850120818352809550505050505081815481106106bf57fe5b90600052602060002001600091509150505481565b600281815481106106e157fe5b906000526020600020016000915090505481565b6106fd611012565b61070682610d73565b610745576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161073c906116a8565b60405180910390fd5b6000808381526020019081526020016000206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561080f5780601f106107e45761010080835404028352916020019161080f565b820191906000526020600020905b8154815290600101906020018083116107f257829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108b15780601f10610886576101008083540402835291602001916108b1565b820191906000526020600020905b81548152906001019060200180831161089457829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109535780601f1061092857610100808354040283529160200191610953565b820191906000526020600020905b81548152906001019060200180831161093657829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109f55780601f106109ca576101008083540402835291602001916109f5565b820191906000526020600020905b8154815290600101906020018083116109d857829003601f168201915b505050505081526020016006820154815250509050919050565b60006060600184604051610a239190611654565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020018280548015610a7b57602002820191906000526020600020905b815481526020019060010190808311610a67575b5050505050905060008090505b8151811015610ac45783828281518110610a9e57fe5b60200260200101511415610ab757600192505050610acb565b8080600101915050610a88565b5060009150505b92915050565b6000602052806000526040600020600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b895780601f10610b5e57610100808354040283529160200191610b89565b820191906000526020600020905b815481529060010190602001808311610b6c57829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c275780601f10610bfc57610100808354040283529160200191610c27565b820191906000526020600020905b815481529060010190602001808311610c0a57829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cc55780601f10610c9a57610100808354040283529160200191610cc5565b820191906000526020600020905b815481529060010190602001808311610ca857829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d635780601f10610d3857610100808354040283529160200191610d63565b820191906000526020600020905b815481529060010190602001808311610d4657829003601f168201915b5050505050908060060154905087565b6000806000808481526020019081526020016000206000015414610d9a5760019050610d9f565b600090505b919050565b610dad87610d73565b610dec576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610de3906116a8565b60405180910390fd5b6000806000898152602001908152602001600020905085816002019080519060200190610e1a92919061104f565b5084816003019080519060200190610e3392919061104f565b5083816004019080519060200190610e4c92919061104f565b5082816005019080519060200190610e6592919061104f565b508181600601819055505050505050505050565b610e8281610d73565b610ec1576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610eb8906116a8565b60405180910390fd5b600182604051610ed19190611654565b90815260200160405180910390208190806001815401808255809150506001900390600052602060002001600090919091909150555050565b610f1387610d73565b15610f53576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f4a906116c8565b60405180910390fd5b6000806000898152602001908152602001600020905087816000018190555085816002019080519060200190610f8a92919061104f565b5084816003019080519060200190610fa392919061104f565b5083816004019080519060200190610fbc92919061104f565b5082816005019080519060200190610fd592919061104f565b50818160060181905550","60028890806001815401808255809150506001900390600052602060002001600090919091909150555050505050505050565b6040518060e00160405280600081526020016000815260200160608152602001606081526020016060815260200160608152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061109057805160ff19168380011785556110be565b828001600101855582156110be579182015b828111156110bd5782518255916020019190600101906110a2565b5b5090506110cb91906110cf565b5090565b6110f191905b808211156110ed5760008160009055506001016110d5565b5090565b90565b600082601f83011261110557600080fd5b8135611118611113826117dd565b6117b0565b9150808252602083016020830185838301111561113457600080fd5b61113f838284611890565b50505092915050565b600081359050611157816118e3565b92915050565b60006020828403121561116f57600080fd5b600082013567ffffffffffffffff81111561118957600080fd5b611195848285016110f4565b91505092915050565b600080604083850312156111b157600080fd5b600083013567ffffffffffffffff8111156111cb57600080fd5b6111d7858286016110f4565b92505060206111e885828601611148565b9150509250929050565b60006020828403121561120457600080fd5b600061121284828501611148565b91505092915050565b600080600080600080600060e0888a03121561123657600080fd5b60006112448a828b01611148565b97505060206112558a828b01611148565b965050604088013567ffffffffffffffff81111561127257600080fd5b61127e8a828b016110f4565b955050606088013567ffffffffffffffff81111561129b57600080fd5b6112a78a828b016110f4565b945050608088013567ffffffffffffffff8111156112c457600080fd5b6112d08a828b016110f4565b93505060a088013567ffffffffffffffff8111156112ed57600080fd5b6112f98a828b016110f4565b92505060c061130a8a828b01611148565b91505092959891949750929550565b600061132583836114d4565b905092915050565b600061133882611819565b611342818561183c565b93508360208202850161135485611809565b8060005b8581101561139057848403895281516113718582611319565b945061137c8361182f565b925060208a01995050600181019050611358565b50829750879550505050505092915050565b6113ab8161187a565b82525050565b60006113bc82611824565b6113c6818561184d565b93506113d681856020860161189f565b6113df816118d2565b840191505092915050565b60006113f582611824565b6113ff818561185e565b935061140f81856020860161189f565b611418816118d2565b840191505092915050565b600061142e82611824565b611438818561186f565b935061144881856020860161189f565b80840191505092915050565b6000611461601b8361185e565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e4b88de5ad98e59ca800000000006000830152602082019050919050565b60006114a1601e8361185e565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e5b7b2e7bb8fe5ad98e59ca800006000830152602082019050919050565b600060e0830160008301516114ec6000860182611636565b5060208301516114ff6020860182611636565b506040830151848203604086015261151782826113b1565b9150506060830151848203606086015261153182826113b1565b9150506080830151848203608086015261154b82826113b1565b91505060a083015184820360a086015261156582826113b1565b91505060c083015161157a60c0860182611636565b508091505092915050565b600060e08301600083015161159d6000860182611636565b5060208301516115b06020860182611636565b50604083015184820360408601526115c882826113b1565b915050606083015184820360608601526115e282826113b1565b915050608083015184820360808601526115fc82826113b1565b91505060a083015184820360a086015261161682826113b1565b91505060c083015161162b60c0860182611636565b508091505092915050565b61163f81611886565b82525050565b61164e81611886565b82525050565b60006116608284611423565b915081905092915050565b60006020820190508181036000830152611685818461132d565b905092915050565b60006020820190506116a260008301846113a2565b92915050565b600060208201905081810360008301526116c181611454565b9050919050565b600060208201905081810360008301526116e181611494565b9050919050565b600060208201905081810360008301526117028184611585565b905092915050565b600060208201905061171f6000830184611645565b92915050565b600060e08201905061173a600083018a611645565b6117476020830189611645565b818103604083015261175981886113ea565b9050818103606083015261176d81876113ea565b9050818103608083015261178181866113ea565b905081810360a083015261179581856113ea565b90506117a460c0830184611645565b98975050505050505050565b6000604051905081810181811067ffffffffffffffff821117156117d357600080fd5b8060405250919050565b600067ffffffffffffffff8211156117f457600080fd5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60008115159050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156118bd5780820151818401526020810190506118a2565b838111156118cc576000848401525b50505050565b6000601f19601f8301169050919050565b6118ec81611886565b81146118f757600080fd5b5056fea2646970667358221220a4f2372b7ebe419076f803257885df124f17e1011a9f09215599c4768d7b1fa064736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"IsCardExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"QueryCardInfo\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo\",\"name\":\"\",\"type\":\"tuple\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardList\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardNumber\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"QueryEnterpriseIsHasCard\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_credit\",\"type\":\"uint256\"}],\"name\":\"RegisterCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_credit\",\"type\":\"uint256\"}],\"name\":\"UpdateCardInfo\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"}],\"name\":\"UserBindCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"UserOfCardListMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CARDINFOLIST = "CardInfoList";

    public static final String FUNC_CARDINFOMAP = "CardInfoMap";

    public static final String FUNC_ISCARDEXIST = "IsCardExist";

    public static final String FUNC_QUERYCARDINFO = "QueryCardInfo";

    public static final String FUNC_QUERYENTERPRISECARDLIST = "QueryEnterpriseCardList";

    public static final String FUNC_QUERYENTERPRISECARDNUMBER = "QueryEnterpriseCardNumber";

    public static final String FUNC_QUERYENTERPRISEISHASCARD = "QueryEnterpriseIsHasCard";

    public static final String FUNC_REGISTERCARD = "RegisterCard";

    public static final String FUNC_UPDATECARDINFO = "UpdateCardInfo";

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

    public TransactionReceipt QueryEnterpriseCardNumber(String enterpriseName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDNUMBER,
                Arrays.<Type>asList(new Utf8String(enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryEnterpriseCardNumber(String enterpriseName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDNUMBER,
                Arrays.<Type>asList(new Utf8String(enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryEnterpriseCardNumber(String enterpriseName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISECARDNUMBER,
                Arrays.<Type>asList(new Utf8String(enterpriseName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getQueryEnterpriseCardNumberInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYENTERPRISECARDNUMBER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<BigInteger> getQueryEnterpriseCardNumberOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_QUERYENTERPRISECARDNUMBER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
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

    public TransactionReceipt UpdateCardInfo(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit) {
        final Function function = new Function(
                FUNC_UPDATECARDINFO,
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

    public byte[] UpdateCardInfo(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPDATECARDINFO,
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

    public String getSignedTransactionForUpdateCardInfo(BigInteger _cardId, BigInteger _level, String _cardName, String _cardDesc, String _cardUrl, String _categoty, BigInteger _credit) {
        final Function function = new Function(
                FUNC_UPDATECARDINFO,
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

    public Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> getUpdateCardInfoInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATECARDINFO,
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
