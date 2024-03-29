package com.ruoyi.carbon.raw;

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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class SouvenirCard extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611993806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c806330ce92131161005b57806330ce92131461014d57806340dd3b841461018357806390d3f9a41461019f578063faad477e146101cf57610088565b80630f5a0a3c1461008d57806313eb8af6146100bd5780631e41e0de146100ed57806320cb631d1461011d575b600080fd5b6100a760048036038101906100a29190611132565b6101eb565b6040516100b491906116ac565b60405180910390f35b6100d760048036038101906100d29190611132565b610665565b6040516100e4919061174b565b60405180910390f35b610107600480360381019061010291906111df565b61098b565b60405161011491906116e9565b60405180910390f35b61013760048036038101906101329190611233565b610a67565b604051610144919061176d565b60405180910390f35b61016760048036038101906101629190611132565b610a88565b60405161017a9796959493929190611788565b60405180910390f35b61019d60048036038101906101989190611173565b610d40565b005b6101b960048036038101906101b49190611132565b610e10565b6040516101c691906116ce565b60405180910390f35b6101e960048036038101906101e4919061125c565b610e4d565b005b6060806001836040516101fe9190611695565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b828210156102e8578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102d45780601f106102a9576101008083540402835291602001916102d4565b820191906000526020600020905b8154815290600101906020018083116102b757829003601f168201915b50505050508152602001906001019061022c565b5050505090506060815167ffffffffffffffff8111801561030857600080fd5b5060405190808252806020026020018201604052801561034257816020015b61032f610f60565b8152602001906001900390816103275790505b50905060008090505b825181101561065a57600083828151811061036257fe5b60200260200101516040516103779190611695565b90815260200160405180910390206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561043d5780601f106104125761010080835404028352916020019161043d565b820191906000526020600020905b81548152906001019060200180831161042057829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104df5780601f106104b4576101008083540402835291602001916104df565b820191906000526020600020905b8154815290600101906020018083116104c257829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105815780601f1061055657610100808354040283529160200191610581565b820191906000526020600020905b81548152906001019060200180831161056457829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106235780601f106105f857610100808354040283529160200191610623565b820191906000526020600020905b81548152906001019060200180831161060657829003601f168201915b5050505050815260200160068201548152505082828151811061064257fe5b6020026020010181905250808060010191505061034b565b508092505050919050565b61066d610f60565b61067682610e10565b6106b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106ac9061170b565b60405180910390fd5b6000826040516106c59190611695565b90815260200160405180910390206040518060e00160405290816000820154815260200160018201548152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561078b5780601f106107605761010080835404028352916020019161078b565b820191906000526020600020905b81548152906001019060200180831161076e57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561082d5780601f106108025761010080835404028352916020019161082d565b820191906000526020600020905b81548152906001019060200180831161081057829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108cf5780601f106108a4576101008083540402835291602001916108cf565b820191906000526020600020905b8154815290600101906020018083116108b257829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109715780601f1061094657610100808354040283529160200191610971565b820191906000526020600020905b81548152906001019060200180831161095457829003601f168201915b505050505081526020016006820154815250509050919050565b60018280516020810182018051848252602083016020850120818352809550505050505081815481106109ba57fe5b90600052602060002001600091509150508054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a5f5780601f10610a3457610100808354040283529160200191610a5f565b820191906000526020600020905b815481529060010190602001808311610a4257829003601f168201915b505050505081565b60028181548110610a7457fe5b906000526020600020016000915090505481565b600081805160208101820180518482526020830160208501208183528095505050505050600091509050806000015490806001015490806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b565780601f10610b2b57610100808354040283529160200191610b56565b820191906000526020600020905b815481529060010190602001808311610b3957829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bf45780601f10610bc957610100808354040283529160200191610bf4565b820191906000526020600020905b815481529060010190602001808311610bd757829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c925780601f10610c6757610100808354040283529160200191610c92565b820191906000526020600020905b815481529060010190602001808311610c7557829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d305780601f10610d0557610100808354040283529160200191610d30565b820191906000526020600020905b815481529060010190602001808311610d1357829003601f168201915b5050505050908060060154905087565b610d4981610e10565b610d88576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d7f9061170b565b60405180910390fd5b600182604051610d989190611695565b9081526020016040518091039020600082604051610db69190611695565b9081526020016040518091039020600201908060018154018082558091505060019003906000526020600020016000909190919091509080546001816001161561010002031660029004610e0b929190610f9d565b505050565b600080600083604051610e239190611695565b90815260200160405180910390206000015414610e435760019050610e48565b600090505b919050565b610e5685610e10565b15610e96576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e8d9061172b565b60405180910390fd5b60008086604051610ea79190611695565b9081526020016040518091039020905087816000018190555085816002019080519060200190610ed8929190611024565b5084816003019080519060200190610ef1929190611024565b5083816004019080519060200190610f0a929190611024565b5082816005019080519060200190610f23929190611024565b5081816006018190555060028890806001815401808255809150506001900390600052602060002001600090919091909150555050505050505050565b6040518060e00160405280600081526020016000815260200160608152602001606081526020016060815260200160608152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610fd65780548555611013565b828001600101855582","1561101357600052602060002091601f016020900482015b82811115611012578254825591600101919060010190610ff7565b5b50905061102091906110a4565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061106557805160ff1916838001178555611093565b82800160010185558215611093579182015b82811115611092578251825591602001919060010190611077565b5b5090506110a091906110a4565b5090565b6110c691905b808211156110c25760008160009055506001016110aa565b5090565b90565b600082601f8301126110da57600080fd5b81356110ed6110e882611840565b611813565b9150808252602083016020830185838301111561110957600080fd5b6111148382846118f3565b50505092915050565b60008135905061112c81611946565b92915050565b60006020828403121561114457600080fd5b600082013567ffffffffffffffff81111561115e57600080fd5b61116a848285016110c9565b91505092915050565b6000806040838503121561118657600080fd5b600083013567ffffffffffffffff8111156111a057600080fd5b6111ac858286016110c9565b925050602083013567ffffffffffffffff8111156111c957600080fd5b6111d5858286016110c9565b9150509250929050565b600080604083850312156111f257600080fd5b600083013567ffffffffffffffff81111561120c57600080fd5b611218858286016110c9565b92505060206112298582860161111d565b9150509250929050565b60006020828403121561124557600080fd5b60006112538482850161111d565b91505092915050565b600080600080600080600060e0888a03121561127757600080fd5b60006112858a828b0161111d565b97505060206112968a828b0161111d565b965050604088013567ffffffffffffffff8111156112b357600080fd5b6112bf8a828b016110c9565b955050606088013567ffffffffffffffff8111156112dc57600080fd5b6112e88a828b016110c9565b945050608088013567ffffffffffffffff81111561130557600080fd5b6113118a828b016110c9565b93505060a088013567ffffffffffffffff81111561132e57600080fd5b61133a8a828b016110c9565b92505060c061134b8a828b0161111d565b91505092959891949750929550565b60006113668383611515565b905092915050565b60006113798261187c565b611383818561189f565b9350836020820285016113958561186c565b8060005b858110156113d157848403895281516113b2858261135a565b94506113bd83611892565b925060208a01995050600181019050611399565b50829750879550505050505092915050565b6113ec816118dd565b82525050565b60006113fd82611887565b61140781856118b0565b9350611417818560208601611902565b61142081611935565b840191505092915050565b600061143682611887565b61144081856118c1565b9350611450818560208601611902565b61145981611935565b840191505092915050565b600061146f82611887565b61147981856118d2565b9350611489818560208601611902565b80840191505092915050565b60006114a2601b836118c1565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e4b88de5ad98e59ca800000000006000830152602082019050919050565b60006114e2601e836118c1565b91507fe5bd93e5898de79a84e7baaae5bfb5e58da1e5b7b2e7bb8fe5ad98e59ca800006000830152602082019050919050565b600060e08301600083015161152d6000860182611677565b5060208301516115406020860182611677565b506040830151848203604086015261155882826113f2565b9150506060830151848203606086015261157282826113f2565b9150506080830151848203608086015261158c82826113f2565b91505060a083015184820360a08601526115a682826113f2565b91505060c08301516115bb60c0860182611677565b508091505092915050565b600060e0830160008301516115de6000860182611677565b5060208301516115f16020860182611677565b506040830151848203604086015261160982826113f2565b9150506060830151848203606086015261162382826113f2565b9150506080830151848203608086015261163d82826113f2565b91505060a083015184820360a086015261165782826113f2565b91505060c083015161166c60c0860182611677565b508091505092915050565b611680816118e9565b82525050565b61168f816118e9565b82525050565b60006116a18284611464565b915081905092915050565b600060208201905081810360008301526116c6818461136e565b905092915050565b60006020820190506116e360008301846113e3565b92915050565b60006020820190508181036000830152611703818461142b565b905092915050565b6000602082019050818103600083015261172481611495565b9050919050565b60006020820190508181036000830152611744816114d5565b9050919050565b6000602082019050818103600083015261176581846115c6565b905092915050565b60006020820190506117826000830184611686565b92915050565b600060e08201905061179d600083018a611686565b6117aa6020830189611686565b81810360408301526117bc818861142b565b905081810360608301526117d0818761142b565b905081810360808301526117e4818661142b565b905081810360a08301526117f8818561142b565b905061180760c0830184611686565b98975050505050505050565b6000604051905081810181811067ffffffffffffffff8211171561183657600080fd5b8060405250919050565b600067ffffffffffffffff82111561185757600080fd5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60008115159050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015611920578082015181840152602081019050611905565b8381111561192f576000848401525b50505050565b6000601f19601f8301169050919050565b61194f816118e9565b811461195a57600080fd5b5056fea26469706673582212203082d50c8849bdacf44d11121b56eda0f0d780c7898aec40bdcdd3862bc7a6d864736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"CardInfoList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"name\":\"CardInfoMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"IsCardExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"QueryCardInfo\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo\",\"name\":\"\",\"type\":\"tuple\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"}],\"name\":\"QueryEnterpriseCardList\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"category\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"credit\",\"type\":\"uint256\"}],\"internalType\":\"struct CardInfo[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_cardId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_level\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardDesc\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardUrl\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_categoty\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"_credit\",\"type\":\"uint256\"}],\"name\":\"RegisterCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_enterpriseName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_cardName\",\"type\":\"string\"}],\"name\":\"UserBindCard\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"UserOfCardListMap\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

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
