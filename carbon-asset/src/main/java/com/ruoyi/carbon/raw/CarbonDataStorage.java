package com.ruoyi.carbon.raw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple10;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple7;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple8;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class CarbonDataStorage extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052600160005560018055600160025560016003556001600455600160055560326006556103e860075534801561003857600080fd5b5061169a806100486000396000f30060806040526004361061011d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806324729b32146101225780632e2b75a2146103c757806333a8c7f5146103f257806335ec24d01461055e57806347e704f41461059f5780635d2cb505146105e057806370307a01146106215780637c3b328b146106be57806386ebf5a7146107ef5780638f97b2a01461081a57806392a1a73514610887578063954114a4146108b2578063a0dd97b7146108dd578063a1e69c4c14610908578063b977486114610933578063de159b3614610974578063e3b5181c146109b5578063ec9e6723146109e0578063f20b271814610a0b578063f2b0707214610a4c578063f3c50c3e14610b4f575b600080fd5b34801561012e57600080fd5b5061014d60048036038101908080359060200190929190505050610c7e565b604051808b815260200180602001806020018060200180602001806020018a81526020018981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186810386528f818151815260200191508051906020019080838360005b838110156101e75780820151818401526020810190506101cc565b50505050905090810190601f1680156102145780820380516001836020036101000a031916815260200191505b5086810385528e818151815260200191508051906020019080838360005b8381101561024d578082015181840152602081019050610232565b50505050905090810190601f16801561027a5780820380516001836020036101000a031916815260200191505b5086810384528d818151815260200191508051906020019080838360005b838110156102b3578082015181840152602081019050610298565b50505050905090810190601f1680156102e05780820380516001836020036101000a031916815260200191505b5086810383528c818151815260200191508051906020019080838360005b838110156103195780820151818401526020810190506102fe565b50505050905090810190601f1680156103465780820380516001836020036101000a031916815260200191505b5086810382528b818151815260200191508051906020019080838360005b8381101561037f578082015181840152602081019050610364565b50505050905090810190601f1680156103ac5780820380516001836020036101000a031916815260200191505b509f5050505050505050505050505050505060405180910390f35b3480156103d357600080fd5b506103dc610fea565b6040518082815260200191505060405180910390f35b3480156103fe57600080fd5b5061041d60048036038101908080359060200190929190505050610ff0565b604051808981526020018881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001868152602001806020018060200185151515158152602001848152602001838103835287818151815260200191508051906020019080838360005b838110156104b557808201518184015260208101905061049a565b50505050905090810190601f1680156104e25780820380516001836020036101000a031916815260200191505b50838103825286818151815260200191508051906020019080838360005b8381101561051b578082015181840152602081019050610500565b50505050905090810190601f1680156105485780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561056a57600080fd5b5061058960048036038101908080359060200190929190505050611195565b6040518082815260200191505060405180910390f35b3480156105ab57600080fd5b506105ca600480360381019080803590602001909291905050506111b8565b6040518082815260200191505060405180910390f35b3480156105ec57600080fd5b5061060b600480360381019080803590602001909291905050506111db565b6040518082815260200191505060405180910390f35b34801561062d57600080fd5b5061064c600480360381019080803590602001909291905050506111fe565b604051808881526020018781526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018481526020018381526020018260ff1660ff16815260200197505050505050505060405180910390f35b3480156106ca57600080fd5b506106ff600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061126d565b604051808b81526020018a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001898152602001888152602001878152602001868152602001851515151581526020018460ff1660ff16815260200183815260200182810382528a818151815260200191508051906020019080838360005b838110156107ab578082015181840152602081019050610790565b50505050905090810190601f1680156107d85780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b3480156107fb57600080fd5b50610804611393565b6040518082815260200191505060405180910390f35b34801561082657600080fd5b5061084560048036038101908080359060200190929190505050611399565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561089357600080fd5b5061089c6113cc565b6040518082815260200191505060405180910390f35b3480156108be57600080fd5b506108c76113d2565b6040518082815260200191505060405180910390f35b3480156108e957600080fd5b506108f26113d8565b6040518082815260200191505060405180910390f35b34801561091457600080fd5b5061091d6113de565b6040518082815260200191505060405180910390f35b34801561093f57600080fd5b5061095e600480360381019080803590602001909291905050506113e4565b6040518082815260200191505060405180910390f35b34801561098057600080fd5b5061099f60048036038101908080359060200190929190505050611407565b6040518082815260200191505060405180910390f35b3480156109c157600080fd5b506109ca61142a565b6040518082815260200191505060405180910390f35b3480156109ec57600080fd5b506109f5611430565b6040518082815260200191505060405180910390f35b348015610a1757600080fd5b50610a3660048036038101908080359060200190929190505050611436565b6040518082815260200191505060405180910390f35b348015610a5857600080fd5b50610a8d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611459565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018360ff1660ff168152602001828103825284818151815260200191508051906020019080838360005b83811015610b11578082015181840152602081019050610af6565b50505050905090810190601f168015610b3e5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b348015610b5b57600080fd5b50610b7a6004803603810190808035906020019092919050505061154e565b60405180898152602001888152602001878152602001806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828103825287818151815260200191508051906020019080838360005b83811015610c3c578082015181840152602081019050610c21565b50505050905090810190601f168015610c695780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b6010602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d305780601f10610d0557610100808354040283529160200191610d30565b820191906000526020600020905b815481529060010190602001808311610d1357829003601f168201915b505050505090806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dce5780601f10610da357610100808354040283529160200191610dce565b820191906000526020600020905b815481529060010190602001808311610db157829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e6c5780601f10610e4157610100808354040283529160200191610e6c565b820191906000526020600020905b815481529060010190602001808311610e4f57829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f0a5780601f10610edf57610100808354040283529160200191610f0a565b820191906000526020600020905b815481529060010190602001808311610eed57829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610fa85780601f10610f7d57610100808354040283529160200191610fa8565b820191906000526020600020905b815481529060010190602001808311610f8b57829003601f168201915b505050505090806006015490806007","0154908060080160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806009015490508a565b60025481565b60136020528060005260406000206000915090508060000154908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806003015490806004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110d45780601f106110a9576101008083540402835291602001916110d4565b820191906000526020600020905b8154815290600101906020018083116110b757829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111725780601f1061114757610100808354040283529160200191611172565b820191906000526020600020905b81548152906001019060200180831161115557829003601f168201915b5050505050908060060160009054906101000a900460ff16908060070154905088565b600c818154811015156111a457fe5b906000526020600020016000915090505481565b6009818154811015156111c757fe5b906000526020600020016000915090505481565b600a818154811015156111ea57fe5b906000526020600020016000915090505481565b60146020528060005260406000206000915090508060000154908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040154908060050154908060060160009054906101000a900460ff16905087565b600f6020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113455780601f1061131a57610100808354040283529160200191611345565b820191906000526020600020905b81548152906001019060200180831161132857829003601f168201915b5050505050908060030154908060040154908060050154908060060154908060070160009054906101000a900460ff16908060070160019054906101000a900460ff1690806008015490508a565b60005481565b600e6020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60045481565b60035481565b60065481565b60075481565b6008818154811015156113f357fe5b906000526020600020016000915090505481565b600d8181548110151561141657fe5b906000526020600020016000915090505481565b60015481565b60055481565b600b8181548110151561144557fe5b906000526020600020016000915090505481565b60116020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115315780601f1061150657610100808354040283529160200191611531565b820191906000526020600020905b81548152906001019060200180831161151457829003601f168201915b5050505050908060030160009054906101000a900460ff16905084565b6012602052806000526040600020600091509050806000015490806001015490806002015490806003018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561160c5780601f106115e15761010080835404028352916020019161160c565b820191906000526020600020905b8154815290600101906020018083116115ef57829003601f168201915b5050505050908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169080600601549080600701549050885600a165627a7a723058209ce025c8a76ff4f0c93e3c06242512283fb165666855d6784d7fe72311ad6e450029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"QualificationsMap\",\"outputs\":[{\"name\":\"qualificationId\",\"type\":\"uint256\"},{\"name\":\"qualificationName\",\"type\":\"string\"},{\"name\":\"qualificationContent\",\"type\":\"string\"},{\"name\":\"qualificationLeader\",\"type\":\"string\"},{\"name\":\"qualificationIndustry\",\"type\":\"string\"},{\"name\":\"qualificationUserName\",\"type\":\"string\"},{\"name\":\"qualificationUploadTime\",\"type\":\"uint256\"},{\"name\":\"qualificationAuditTime\",\"type\":\"uint256\"},{\"name\":\"qualificationVerifiedRegulator\",\"type\":\"address\"},{\"name\":\"qualificationEmissionLimit\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"RegulatorID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EmissionResourcesMap\",\"outputs\":[{\"name\":\"emissionId\",\"type\":\"uint256\"},{\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"name\":\"emissions\",\"type\":\"uint256\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"emissionWay\",\"type\":\"string\"},{\"name\":\"isApprove\",\"type\":\"bool\"},{\"name\":\"emissionTime\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EmissionResourceIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"RegulatorIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"QualificationIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseAssetsMap\",\"outputs\":[{\"name\":\"assetId\",\"type\":\"uint256\"},{\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"name\":\"assetQuantity\",\"type\":\"uint256\"},{\"name\":\"assetAmount\",\"type\":\"uint256\"},{\"name\":\"time\",\"type\":\"uint256\"},{\"name\":\"status\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"EnterprisesMap\",\"outputs\":[{\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"name\":\"enterpriseName\",\"type\":\"string\"},{\"name\":\"enterpriseBalance\",\"type\":\"uint256\"},{\"name\":\"enterpriseTotalEmission\",\"type\":\"uint256\"},{\"name\":\"enterpriseOverEmission\",\"type\":\"uint256\"},{\"name\":\"enterpriseCarbonCredits\",\"type\":\"uint256\"},{\"name\":\"enterpriseVerified\",\"type\":\"bool\"},{\"name\":\"userType\",\"type\":\"uint8\"},{\"name\":\"qualificationId\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"EnterpriseID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"userIdQueryAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"EmissionResourceID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"TransactionID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"SIGNIN_CREDIT\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"TOTAL_EMISSION\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseAssetIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"QualificationID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"EnterpriseAssetID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"TransactionIDList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"RegulatorsMap\",\"outputs\":[{\"name\":\"regulatorId\",\"type\":\"uint256\"},{\"name\":\"regulatorAddress\",\"type\":\"address\"},{\"name\":\"regulatorName\",\"type\":\"string\"},{\"name\":\"userType\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"TransactionsMap\",\"outputs\":[{\"name\":\"transactionId\",\"type\":\"uint256\"},{\"name\":\"buyerId\",\"type\":\"uint256\"},{\"name\":\"sellerId\",\"type\":\"uint256\"},{\"name\":\"transactionOrderName\",\"type\":\"string\"},{\"name\":\"transactionBuyAddress\",\"type\":\"address\"},{\"name\":\"transactionSellAddress\",\"type\":\"address\"},{\"name\":\"transactionTime\",\"type\":\"uint256\"},{\"name\":\"transactionQuantity\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_acount\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_name\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"_content\",\"type\":\"string\"}],\"name\":\"UploadQualification\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_emissionLimit\",\"type\":\"uint256\"}],\"name\":\"VerifyQualification\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"TransferEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_emissionLimitCount\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"SellEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"UpdateBalnce\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_emissionLimit\",\"type\":\"uint256\"}],\"name\":\"UpdateEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_emissionEmission\",\"type\":\"uint256\"}],\"name\":\"UploadEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_form\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_isAppore\",\"type\":\"bool\"}],\"name\":\"VerifyEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_totalEmissions\",\"type\":\"uint256\"}],\"name\":\"UpdateEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_emissionEmission\",\"type\":\"uint256\"}],\"name\":\"EnterpriseEmission\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_QUALIFICATIONSMAP = "QualificationsMap";

    public static final String FUNC_REGULATORID = "RegulatorID";

    public static final String FUNC_EMISSIONRESOURCESMAP = "EmissionResourcesMap";

    public static final String FUNC_EMISSIONRESOURCEIDLIST = "EmissionResourceIDList";

    public static final String FUNC_REGULATORIDLIST = "RegulatorIDList";

    public static final String FUNC_QUALIFICATIONIDLIST = "QualificationIDList";

    public static final String FUNC_ENTERPRISEASSETSMAP = "EnterpriseAssetsMap";

    public static final String FUNC_ENTERPRISESMAP = "EnterprisesMap";

    public static final String FUNC_ENTERPRISEID = "EnterpriseID";

    public static final String FUNC_USERIDQUERYADDRESS = "userIdQueryAddress";

    public static final String FUNC_EMISSIONRESOURCEID = "EmissionResourceID";

    public static final String FUNC_TRANSACTIONID = "TransactionID";

    public static final String FUNC_SIGNIN_CREDIT = "SIGNIN_CREDIT";

    public static final String FUNC_TOTAL_EMISSION = "TOTAL_EMISSION";

    public static final String FUNC_ENTERPRISEIDLIST = "EnterpriseIDList";

    public static final String FUNC_ENTERPRISEASSETIDLIST = "EnterpriseAssetIDList";

    public static final String FUNC_QUALIFICATIONID = "QualificationID";

    public static final String FUNC_ENTERPRISEASSETID = "EnterpriseAssetID";

    public static final String FUNC_TRANSACTIONIDLIST = "TransactionIDList";

    public static final String FUNC_REGULATORSMAP = "RegulatorsMap";

    public static final String FUNC_TRANSACTIONSMAP = "TransactionsMap";

    public static final Event UPLOADQUALIFICATION_EVENT = new Event("UploadQualification", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event VERIFYQUALIFICATION_EVENT = new Event("VerifyQualification", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFEREMISSIONLIMIT_EVENT = new Event("TransferEmissionLimit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event SELLEMISSIONLIMIT_EVENT = new Event("SellEmissionLimit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEBALNCE_EVENT = new Event("UpdateBalnce", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEEMISSIONLIMIT_EVENT = new Event("UpdateEmissionLimit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPLOADENTERPRISEEMISSION_EVENT = new Event("UploadEnterpriseEmission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event VERIFYENTERPRISEEMISSION_EVENT = new Event("VerifyEnterpriseEmission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>(true) {}));
    ;

    public static final Event UPDATEENTERPRISEEMISSION_EVENT = new Event("UpdateEnterpriseEmission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event ENTERPRISEEMISSION_EVENT = new Event("EnterpriseEmission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    protected CarbonDataStorage(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, String, BigInteger> QualificationsMap(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_QUALIFICATIONSMAP, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, String, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue(), 
                (String) results.get(8).getValue(), 
                (BigInteger) results.get(9).getValue());
    }

    public BigInteger RegulatorID() throws ContractException {
        final Function function = new Function(FUNC_REGULATORID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple8<BigInteger, BigInteger, String, BigInteger, String, String, Boolean, BigInteger> EmissionResourcesMap(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_EMISSIONRESOURCESMAP, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple8<BigInteger, BigInteger, String, BigInteger, String, String, Boolean, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (Boolean) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue());
    }

    public BigInteger EmissionResourceIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_EMISSIONRESOURCEIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger RegulatorIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_REGULATORIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger QualificationIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_QUALIFICATIONIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple7<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger> EnterpriseAssetsMap(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEASSETSMAP, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple7<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue());
    }

    public Tuple10<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, BigInteger, BigInteger> EnterprisesMap(String param0) throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISESMAP, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple10<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (Boolean) results.get(7).getValue(), 
                (BigInteger) results.get(8).getValue(), 
                (BigInteger) results.get(9).getValue());
    }

    public BigInteger EnterpriseID() throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public String userIdQueryAddress(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_USERIDQUERYADDRESS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public BigInteger EmissionResourceID() throws ContractException {
        final Function function = new Function(FUNC_EMISSIONRESOURCEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger TransactionID() throws ContractException {
        final Function function = new Function(FUNC_TRANSACTIONID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger SIGNIN_CREDIT() throws ContractException {
        final Function function = new Function(FUNC_SIGNIN_CREDIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger TOTAL_EMISSION() throws ContractException {
        final Function function = new Function(FUNC_TOTAL_EMISSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger EnterpriseIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger EnterpriseAssetIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEASSETIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger QualificationID() throws ContractException {
        final Function function = new Function(FUNC_QUALIFICATIONID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger EnterpriseAssetID() throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEASSETID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger TransactionIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_TRANSACTIONIDLIST, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple4<BigInteger, String, String, BigInteger> RegulatorsMap(String param0) throws ContractException {
        final Function function = new Function(FUNC_REGULATORSMAP, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<BigInteger, String, String, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue());
    }

    public Tuple8<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger> TransactionsMap(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_TRANSACTIONSMAP, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple8<BigInteger, BigInteger, BigInteger, String, String, String, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue());
    }

    public List<UploadQualificationEventResponse> getUploadQualificationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPLOADQUALIFICATION_EVENT, transactionReceipt);
        ArrayList<UploadQualificationEventResponse> responses = new ArrayList<UploadQualificationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UploadQualificationEventResponse typedResponse = new UploadQualificationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._acount = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._name = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._content = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUploadQualificationEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(UPLOADQUALIFICATION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUploadQualificationEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(UPLOADQUALIFICATION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<VerifyQualificationEventResponse> getVerifyQualificationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VERIFYQUALIFICATION_EVENT, transactionReceipt);
        ArrayList<VerifyQualificationEventResponse> responses = new ArrayList<VerifyQualificationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VerifyQualificationEventResponse typedResponse = new VerifyQualificationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._emissionLimit = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVerifyQualificationEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VERIFYQUALIFICATION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVerifyQualificationEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VERIFYQUALIFICATION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<TransferEmissionLimitEventResponse> getTransferEmissionLimitEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREMISSIONLIMIT_EVENT, transactionReceipt);
        ArrayList<TransferEmissionLimitEventResponse> responses = new ArrayList<TransferEmissionLimitEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEmissionLimitEventResponse typedResponse = new TransferEmissionLimitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeTransferEmissionLimitEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(TRANSFEREMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeTransferEmissionLimitEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(TRANSFEREMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<SellEmissionLimitEventResponse> getSellEmissionLimitEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SELLEMISSIONLIMIT_EVENT, transactionReceipt);
        ArrayList<SellEmissionLimitEventResponse> responses = new ArrayList<SellEmissionLimitEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SellEmissionLimitEventResponse typedResponse = new SellEmissionLimitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._emissionLimitCount = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeSellEmissionLimitEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(SELLEMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeSellEmissionLimitEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(SELLEMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<UpdateBalnceEventResponse> getUpdateBalnceEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEBALNCE_EVENT, transactionReceipt);
        ArrayList<UpdateBalnceEventResponse> responses = new ArrayList<UpdateBalnceEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UpdateBalnceEventResponse typedResponse = new UpdateBalnceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUpdateBalnceEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEBALNCE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUpdateBalnceEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEBALNCE_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<UpdateEmissionLimitEventResponse> getUpdateEmissionLimitEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEEMISSIONLIMIT_EVENT, transactionReceipt);
        ArrayList<UpdateEmissionLimitEventResponse> responses = new ArrayList<UpdateEmissionLimitEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UpdateEmissionLimitEventResponse typedResponse = new UpdateEmissionLimitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._emissionLimit = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUpdateEmissionLimitEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEEMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUpdateEmissionLimitEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEEMISSIONLIMIT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<UploadEnterpriseEmissionEventResponse> getUploadEnterpriseEmissionEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPLOADENTERPRISEEMISSION_EVENT, transactionReceipt);
        ArrayList<UploadEnterpriseEmissionEventResponse> responses = new ArrayList<UploadEnterpriseEmissionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UploadEnterpriseEmissionEventResponse typedResponse = new UploadEnterpriseEmissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._emissionEmission = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUploadEnterpriseEmissionEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(UPLOADENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUploadEnterpriseEmissionEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(UPLOADENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<VerifyEnterpriseEmissionEventResponse> getVerifyEnterpriseEmissionEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VERIFYENTERPRISEEMISSION_EVENT, transactionReceipt);
        ArrayList<VerifyEnterpriseEmissionEventResponse> responses = new ArrayList<VerifyEnterpriseEmissionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VerifyEnterpriseEmissionEventResponse typedResponse = new VerifyEnterpriseEmissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._form = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._isAppore = (Boolean) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVerifyEnterpriseEmissionEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VERIFYENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVerifyEnterpriseEmissionEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VERIFYENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<UpdateEnterpriseEmissionEventResponse> getUpdateEnterpriseEmissionEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEENTERPRISEEMISSION_EVENT, transactionReceipt);
        ArrayList<UpdateEnterpriseEmissionEventResponse> responses = new ArrayList<UpdateEnterpriseEmissionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UpdateEnterpriseEmissionEventResponse typedResponse = new UpdateEnterpriseEmissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._totalEmissions = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUpdateEnterpriseEmissionEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUpdateEnterpriseEmissionEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(UPDATEENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<EnterpriseEmissionEventResponse> getEnterpriseEmissionEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ENTERPRISEEMISSION_EVENT, transactionReceipt);
        ArrayList<EnterpriseEmissionEventResponse> responses = new ArrayList<EnterpriseEmissionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EnterpriseEmissionEventResponse typedResponse = new EnterpriseEmissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._enterpriseAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._emissionEmission = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeEnterpriseEmissionEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(ENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeEnterpriseEmissionEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(ENTERPRISEEMISSION_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static CarbonDataStorage load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new CarbonDataStorage(contractAddress, client, credential);
    }

    public static CarbonDataStorage deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(CarbonDataStorage.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class UploadQualificationEventResponse {
        public TransactionReceipt.Logs log;

        public String _acount;

        public byte[] _name;

        public byte[] _content;
    }

    public static class VerifyQualificationEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionLimit;
    }

    public static class TransferEmissionLimitEventResponse {
        public TransactionReceipt.Logs log;

        public String _from;

        public String _to;

        public BigInteger _amount;
    }

    public static class SellEmissionLimitEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger _emissionLimitCount;

        public BigInteger _amount;
    }

    public static class UpdateBalnceEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _amount;
    }

    public static class UpdateEmissionLimitEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionLimit;
    }

    public static class UploadEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionEmission;
    }

    public static class VerifyEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _form;

        public String _to;

        public Boolean _isAppore;
    }

    public static class UpdateEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _totalEmissions;
    }

    public static class EnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionEmission;
    }
}
