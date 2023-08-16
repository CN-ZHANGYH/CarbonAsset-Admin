package com.ruoyi.carbon.raw;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.*;
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
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611b28806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c806330ce92131161006657806330ce92131461015857806340dd3b841461018e57806382406450146101aa57806390d3f9a4146101da578063faad477e1461020a57610093565b80630f5a0a3c1461009857806313eb8af6146100c85780631e41e0de146100f857806320cb631d14610128575b600080fd5b6100b260048036038101906100ad91906112c7565b610226565b6040516100bf9190611841565b60405180910390f35b6100e260048036038101906100dd91906112c7565b6106a0565b6040516100ef91906118e0565b60405180910390f35b610112600480360381019061010d9190611374565b6109c6565b60405161011f919061187e565b60405180910390f35b610142600480360381019061013d91906113c8565b610aa2565b60405161014f9190611902565b60405180910390f35b610172600480360381019061016d91906112c7565b610ac3565b604051610185979695949392919061191d565b60405180910390f35b6101a860048036038101906101a39190611308565b610d7b565b005b6101c460048036038101906101bf9190611308565b610e4b565b6040516101d19190611863565b60405180910390f35b6101f460048036038101906101ef91906112c7565b610fa5565b6040516102019190611863565b60405180910390f35b610224600480360381019061021f91906113f1565b610fe2565b005b606080600183604051610239919061182a565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b82821015610323578382906000526020600020018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561030f5780601f106102e45761010080835404028352916020019161030f565b820191906000526020600020905b8154815290600101906020018083116102f257829003601f168201915b505050505081526020019060010190610267565b5050505090506060815167ffffffffffffffff8111801561034357600080fd5b5060405190808252806020026020018201604052801561037d57816020015b61036a6110f5565b8152602001906001900390816103625790505b50905060008090505b825181101561069557600083828151811061039d57fe5b60200260200101516040516103b2919061182a565b90815260200160405180910390206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104785780601f1061044d57610100808354040283529160200191610478565b820191906000526020600020905b81548152906001019060200180831161045b57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561051a5780601f106104ef5761010080835404028352916020019161051a565b820191906000526020600020905b8154815290600101906020018083116104fd57829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105bc5780601f10610591576101008083540402835291602001916105bc565b820191906000526020600020905b81548152906001019060200180831161059f57829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561065e5780601f106106335761010080835404028352916020019161065e565b820191906000526020600020905b81548152906001019060200180831161064157829003601f168201915b5050505050815260200160068201548152505082828151811061067d57fe5b60200260200101819052508080600101915050610386565b508092505050919050565b6106a86110f5565b6106b182610fa5565b6106f0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106e7906118a0565b60405180910390fd5b600082604051610700919061182a565b90815260200160405180910390206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107c65780601f1061079b576101008083540402835291602001916107c6565b820191906000526020600020905b8154815290600101906020018083116107a957829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108685780601f1061083d57610100808354040283529160200191610868565b820191906000526020600020905b81548152906001019060200180831161084b57829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561090a5780601f106108df5761010080835404028352916020019161090a565b820191906000526020600020905b8154815290600101906020018083116108ed57829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ac5780601f10610981576101008083540402835291602001916109ac565b820191906000526020600020905b81548152906001019060200180831161098f57829003601f168201915b505050505081526020016006820154815250509050919050565b60018280516020810182018051848252602083016020850120818352809550505050505081815481106109f557fe5b90600052602060002001600091509150508054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a9a5780601f10610a6f57610100808354040283529160200191610a9a565b820191906000526020600020905b815481529060010190602001808311610a7d57829003601f168201915b505050505081565b60028181548110610aaf57fe5b906000526020600020016000915090505481565b600081805160208101820180518482526020830160208501208183528095505050505050600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b915780601f10610b6657610100808354040283529160200191610b91565b820191906000526020600020905b815481529060010190602001808311610b7457829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c2f5780601f10610c0457610100808354040283529160200191610c2f565b820191906000526020600020905b815481529060010190602001808311610c1257829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ccd5780601f10610ca257610100808354040283529160200191610ccd565b820191906000526020600020905b815481529060010190602001808311610cb057829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d6b5780601f10610d4057610100808354040283529160200191610d6b565b820191906000526020600020905b815481529060010190602001808311610d4e57829003601f168201915b5050505050908060060154905087565b610d8481610fa5565b610dc3576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610dba906118a0565b60405180910390fd5b600182604051610dd3919061182a565b9081526020016040518091039020600082604051610df1919061182a565b9081526020016040518091039020600201908060018154018082558091505060019003906000526020600020016000909190919091509080546001816001161561010002031660029004610e46929190611132565b505050565b60006060600184604051610e5f919061182a565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b82821015610f49578382906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f355780601f10610f0a57610100808354040283529160200191610f35565b820191906000526020600020905b815481529060010190602001808311610f1857829003601f168201915b505050505081526020019060010190610e8d565b50505050905060008090508151811015610f9c57818181518110610f6957fe5b60200260200101518051906020012084805190602001201415610f9157600192505050610f9f565b600092505050610f9f565b50505b92915050565b600080600083604051610fb8919061182a565b90815260200160405180910390206000015414610fd85760019050610fdd565b600090505b9190","50565b610feb85610fa5565b1561102b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611022906118c0565b60405180910390fd5b6000808660405161103c919061182a565b908152602001604051809103902090508781600001819055508581600201908051906020019061106d9291906111b9565b50848160030190805190602001906110869291906111b9565b508381600401908051906020019061109f9291906111b9565b50828160050190805190602001906110b89291906111b9565b5081816006018190555060028890806001815401808255809150506001900390600052602060002001600090919091909150555050505050505050565b6040518060e00160405280600081526020016000815260200160608152602001606081526020016060815260200160608152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061116b57805485556111a8565b828001600101855582156111a857600052602060002091601f016020900482015b828111156111a757825482559160010191906001019061118c565b5b5090506111b59190611239565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111fa57805160ff1916838001178555611228565b82800160010185558215611228579182015b8281111561122757825182559160200191906001019061120c565b5b5090506112359190611239565b5090565b61125b91905b8082111561125757600081600090555060010161123f565b5090565b90565b600082601f83011261126f57600080fd5b813561128261127d826119d5565b6119a8565b9150808252602083016020830185838301111561129e57600080fd5b6112a9838284611a88565b50505092915050565b6000813590506112c181611adb565b92915050565b6000602082840312156112d957600080fd5b600082013567ffffffffffffffff8111156112f357600080fd5b6112ff8482850161125e565b91505092915050565b6000806040838503121561131b57600080fd5b600083013567ffffffffffffffff81111561133557600080fd5b6113418582860161125e565b925050602083013567ffffffffffffffff81111561135e57600080fd5b61136a8582860161125e565b9150509250929050565b6000806040838503121561138757600080fd5b600083013567ffffffffffffffff8111156113a157600080fd5b6113ad8582860161125e565b92505060206113be858286016112b2565b9150509250929050565b6000602082840312156113da57600080fd5b60006113e8848285016112b2565b91505092915050565b600080600080600080600060e0888a03121561140c57600080fd5b600061141a8a828b016112b2565b975050602061142b8a828b016112b2565b965050604088013567ffffffffffffffff81111561144857600080fd5b6114548a828b0161125e565b955050606088013567ffffffffffffffff81111561147157600080fd5b61147d8a828b0161125e565b945050608088013567ffffffffffffffff81111561149a57600080fd5b6114a68a828b0161125e565b93505060a088013567ffffffffffffffff8111156114c357600080fd5b6114cf8a828b0161125e565b92505060c06114e08a828b016112b2565b91505092959891949750929550565b60006114fb83836116aa565b905092915050565b600061150e82611a11565b6115188185611a34565b93508360208202850161152a85611a01565b8060005b85811015611566578484038952815161154785826114ef565b945061155283611a27565b925060208a0199505060018101905061152e565b50829750879550505050505092915050565b61158181611a72565b82525050565b600061159282611a1c565b61159c8185611a45565b93506115ac818560208601611a97565b6115b581611aca565b840191505092915050565b60006115cb82611a1c565b6115d58185611a56565b93506115e5818560208601611a97565b6115ee81611aca565b840191505092915050565b600061160482611a1c565b61160e8185611a67565b935061161e818560208601611a97565b80840191505092915050565b6000611637601b83611a56565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e4b88de5ad98e59ca800000000006000830152602082019050919050565b6000611677601e83611a56565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e5b7b2e7bb8fe5ad98e59ca800006000830152602082019050919050565b600060e0830160008301516116c2600086018261180c565b5060208301516116d5602086018261180c565b50604083015184820360408601526116ed8282611587565b915050606083015184820360608601526117078282611587565b915050608083015184820360808601526117218282611587565b91505060a083015184820360a086015261173b8282611587565b91505060c083015161175060c086018261180c565b508091505092915050565b600060e083016000830151611773600086018261180c565b506020830151611786602086018261180c565b506040830151848203604086015261179e8282611587565b915050606083015184820360608601526117b88282611587565b915050608083015184820360808601526117d28282611587565b91505060a083015184820360a08601526117ec8282611587565b91505060c083015161180160c086018261180c565b508091505092915050565b61181581611a7e565b82525050565b61182481611a7e565b82525050565b600061183682846115f9565b915081905092915050565b6000602082019050818103600083015261185b8184611503565b905092915050565b60006020820190506118786000830184611578565b92915050565b6000602082019050818103600083015261189881846115c0565b905092915050565b600060208201905081810360008301526118b98161162a565b9050919050565b600060208201905081810360008301526118d98161166a565b9050919050565b600060208201905081810360008301526118fa818461175b565b905092915050565b6000602082019050611917600083018461181b565b92915050565b600060e082019050611932600083018a61181b565b61193f602083018961181b565b818103604083015261195181886115c0565b9050818103606083015261196581876115c0565b9050818103608083015261197981866115c0565b905081810360a083015261198d81856115c0565b905061199c60c083018461181b565b98975050505050505050565b6000604051905081810181811067ffffffffffffffff821117156119cb57600080fd5b8060405250919050565b600067ffffffffffffffff8211156119ec57600080fd5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60008115159050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015611ab5578082015181840152602081019050611a9a565b83811115611ac4576000848401525b50505050565b6000601f19601f8301169050919050565b611ae481611a7e565b8114611aef57600080fd5b5056fea2646970667358221220296ba23f2c7a8ddb3c0c21485c66a323e0a2c1ce6594de2d1b7f993deb1ad86864736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"name\":\"CardInfoMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"IsCardExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"QueryCardInfo\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo\",\"name\":\"\",\"type\":\"tuple\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardList\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseIsHasCard\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_credit\",\"type\":\"uint256\"}],\"name\":\"RegisterCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"UserBindCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"UserOfCardListMap\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

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

    public Tuple7<BigInteger, BigInteger, String, String, String, String, BigInteger> CardInfoMap(String param0) throws ContractException {
        final Function function = new Function(FUNC_CARDINFOMAP,
                Arrays.<Type>asList(new Utf8String(param0)),
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

    public TransactionReceipt QueryEnterpriseIsHasCard(String _enterpriseName, String _cardName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] QueryEnterpriseIsHasCard(String _enterpriseName, String _cardName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQueryEnterpriseIsHasCard(String _enterpriseName, String _cardName) {
        final Function function = new Function(
                FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(new Utf8String(_enterpriseName),
                new Utf8String(_cardName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getQueryEnterpriseIsHasCardInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERYENTERPRISEISHASCARD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue()
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
