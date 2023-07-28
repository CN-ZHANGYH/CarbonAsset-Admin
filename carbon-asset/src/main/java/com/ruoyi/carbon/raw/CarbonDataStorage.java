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
    public static final String[] BINARY_ARRAY = {"6080604052600160005560018055600160025560016003556001600455600160055560326006556103e860075534801561003857600080fd5b50611657806100486000396000f3fe608060405234801561001057600080fd5b50600436106101375760003560e01c806392a1a735116100b8578063de159b361161007c578063de159b3614610949578063e3b5181c1461098b578063ec9e6723146109a9578063f20b2718146109c7578063f2b0707214610a09578063f3c50c3e14610b0d57610137565b806392a1a7351461088f578063954114a4146108ad578063a0dd97b7146108cb578063a1e69c4c146108e9578063b97748611461090757610137565b80635d2cb505116100ff5780635d2cb505146105f157806370307a01146106335780637c3b328b146106d157806386ebf5a7146108035780638f97b2a01461082157610137565b806324729b321461013c5780632e2b75a2146103e257806333a8c7f51461040057806335ec24d01461056d57806347e704f4146105af575b600080fd5b6101686004803603602081101561015257600080fd5b8101908080359060200190929190505050610c3d565b604051808b815260200180602001806020018060200180602001806020018a81526020018981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186810386528f818151815260200191508051906020019080838360005b838110156102025780820151818401526020810190506101e7565b50505050905090810190601f16801561022f5780820380516001836020036101000a031916815260200191505b5086810385528e818151815260200191508051906020019080838360005b8381101561026857808201518184015260208101905061024d565b50505050905090810190601f1680156102955780820380516001836020036101000a031916815260200191505b5086810384528d818151815260200191508051906020019080838360005b838110156102ce5780820151818401526020810190506102b3565b50505050905090810190601f1680156102fb5780820380516001836020036101000a031916815260200191505b5086810383528c818151815260200191508051906020019080838360005b83811015610334578082015181840152602081019050610319565b50505050905090810190601f1680156103615780820380516001836020036101000a031916815260200191505b5086810382528b818151815260200191508051906020019080838360005b8381101561039a57808201518184015260208101905061037f565b50505050905090810190601f1680156103c75780820380516001836020036101000a031916815260200191505b509f5050505050505050505050505050505060405180910390f35b6103ea610fa9565b6040518082815260200191505060405180910390f35b61042c6004803603602081101561041657600080fd5b8101908080359060200190929190505050610faf565b604051808981526020018881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001868152602001806020018060200185151515158152602001848152602001838103835287818151815260200191508051906020019080838360005b838110156104c45780820151818401526020810190506104a9565b50505050905090810190601f1680156104f15780820380516001836020036101000a031916815260200191505b50838103825286818151815260200191508051906020019080838360005b8381101561052a57808201518184015260208101905061050f565b50505050905090810190601f1680156105575780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b6105996004803603602081101561058357600080fd5b8101908080359060200190929190505050611154565b6040518082815260200191505060405180910390f35b6105db600480360360208110156105c557600080fd5b8101908080359060200190929190505050611175565b6040518082815260200191505060405180910390f35b61061d6004803603602081101561060757600080fd5b8101908080359060200190929190505050611196565b6040518082815260200191505060405180910390f35b61065f6004803603602081101561064957600080fd5b81019080803590602001909291905050506111b7565b604051808881526020018781526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018481526020018381526020018260ff1660ff16815260200197505050505050505060405180910390f35b610713600480360360208110156106e757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611226565b604051808b81526020018a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001898152602001888152602001878152602001868152602001851515151581526020018460ff1660ff16815260200183815260200182810382528a818151815260200191508051906020019080838360005b838110156107bf5780820151818401526020810190506107a4565b50505050905090810190601f1680156107ec5780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b61080b61134c565b6040518082815260200191505060405180910390f35b61084d6004803603602081101561083757600080fd5b8101908080359060200190929190505050611352565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610897611385565b6040518082815260200191505060405180910390f35b6108b561138b565b6040518082815260200191505060405180910390f35b6108d3611391565b6040518082815260200191505060405180910390f35b6108f1611397565b6040518082815260200191505060405180910390f35b6109336004803603602081101561091d57600080fd5b810190808035906020019092919050505061139d565b6040518082815260200191505060405180910390f35b6109756004803603602081101561095f57600080fd5b81019080803590602001909291905050506113be565b6040518082815260200191505060405180910390f35b6109936113df565b6040518082815260200191505060405180910390f35b6109b16113e5565b6040518082815260200191505060405180910390f35b6109f3600480360360208110156109dd57600080fd5b81019080803590602001909291905050506113eb565b6040518082815260200191505060405180910390f35b610a4b60048036036020811015610a1f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061140c565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018360ff1660ff168152602001828103825284818151815260200191508051906020019080838360005b83811015610acf578082015181840152602081019050610ab4565b50505050905090810190601f168015610afc5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b610b3960048036036020811015610b2357600080fd5b8101908080359060200190929190505050611501565b60405180898152602001888152602001878152602001806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001828103825287818151815260200191508051906020019080838360005b83811015610bfb578082015181840152602081019050610be0565b50505050905090810190601f168015610c285780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b6010602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cef5780601f10610cc457610100808354040283529160200191610cef565b820191906000526020600020905b815481529060010190602001808311610cd257829003601f168201915b505050505090806002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d8d5780601f10610d6257610100808354040283529160200191610d8d565b820191906000526020600020905b815481529060010190602001808311610d7057829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e2b5780601f10610e0057610100808354040283529160200191610e2b565b820191906000526020600020905b815481529060010190602001808311610e0e57829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ec95780601f10610e9e57610100808354040283529160200191610ec9565b820191906000526020600020905b815481529060010190602001808311610eac57829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f675780601f10610f3c57610100808354040283529160200191610f67565b820191906000526020600020905b815481529060010190602001808311610f4a57829003601f168201915b5050505050908060060154908060070154908060080160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806009015490508a565b60025481565b6013602052806000","5260406000206000915090508060000154908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806003015490806004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110935780601f1061106857610100808354040283529160200191611093565b820191906000526020600020905b81548152906001019060200180831161107657829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111315780601f1061110657610100808354040283529160200191611131565b820191906000526020600020905b81548152906001019060200180831161111457829003601f168201915b5050505050908060060160009054906101000a900460ff16908060070154905088565b600c818154811061116157fe5b906000526020600020016000915090505481565b6009818154811061118257fe5b906000526020600020016000915090505481565b600a81815481106111a357fe5b906000526020600020016000915090505481565b60146020528060005260406000206000915090508060000154908060010154908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040154908060050154908060060160009054906101000a900460ff16905087565b600f6020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112fe5780601f106112d3576101008083540402835291602001916112fe565b820191906000526020600020905b8154815290600101906020018083116112e157829003601f168201915b5050505050908060030154908060040154908060050154908060060154908060070160009054906101000a900460ff16908060070160019054906101000a900460ff1690806008015490508a565b60005481565b600e6020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60045481565b60035481565b60065481565b60075481565b600881815481106113aa57fe5b906000526020600020016000915090505481565b600d81815481106113cb57fe5b906000526020600020016000915090505481565b60015481565b60055481565b600b81815481106113f857fe5b906000526020600020016000915090505481565b60116020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114e45780601f106114b9576101008083540402835291602001916114e4565b820191906000526020600020905b8154815290600101906020018083116114c757829003601f168201915b5050505050908060030160009054906101000a900460ff16905084565b6012602052806000526040600020600091509050806000015490806001015490806002015490806003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115bf5780601f10611594576101008083540402835291602001916115bf565b820191906000526020600020905b8154815290600101906020018083116115a257829003601f168201915b5050505050908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806006015490806007015490508856fea264697066735822122091baed8c3bf5c3df0e29349e0b67577a951838d5606fba1e0859801ff1bb9f4964736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_emissionEmission\",\"type\":\"uint256\"}],\"name\":\"EnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_emissionLimitCount\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"SellEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"TransferEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"UpdateBalnce\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_emissionLimit\",\"type\":\"uint256\"}],\"name\":\"UpdateEmissionLimit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_totalEmissions\",\"type\":\"uint256\"}],\"name\":\"UpdateEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_emissionEmission\",\"type\":\"uint256\"}],\"name\":\"UploadEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_acount\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"string\",\"name\":\"_name\",\"type\":\"string\"},{\"indexed\":true,\"internalType\":\"string\",\"name\":\"_content\",\"type\":\"string\"}],\"name\":\"UploadQualification\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_form\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_to\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"bool\",\"name\":\"_isAppore\",\"type\":\"bool\"}],\"name\":\"VerifyEnterpriseEmission\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_enterpriseAddr\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"_emissionLimit\",\"type\":\"uint256\"}],\"name\":\"VerifyQualification\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"EmissionResourceID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EmissionResourceIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EmissionResourcesMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"emissionId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"emissions\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"description\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"emissionWay\",\"type\":\"string\"},{\"internalType\":\"bool\",\"name\":\"isApprove\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"emissionTime\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"EnterpriseAssetID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseAssetIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseAssetsMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"assetId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"assetQuantity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"assetAmount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"time\",\"type\":\"uint256\"},{\"internalType\":\"uint8\",\"name\":\"status\",\"type\":\"uint8\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"EnterpriseID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"EnterpriseIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"EnterprisesMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"enterpriseId\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"enterpriseAddress\",\"type\":\"address\"},{\"internalType\":\"string\",\"name\":\"enterpriseName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseBalance\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseTotalEmission\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseOverEmission\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"enterpriseCarbonCredits\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"enterpriseVerified\",\"type\":\"bool\"},{\"internalType\":\"uint8\",\"name\":\"userType\",\"type\":\"uint8\"},{\"internalType\":\"uint256\",\"name\":\"qualificationId\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"QualificationID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"QualificationIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"QualificationsMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"qualificationId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"qualificationName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"qualificationContent\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"qualificationLeader\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"qualificationIndustry\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"qualificationUserName\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"qualificationUploadTime\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"qualificationAuditTime\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"qualificationVerifiedRegulator\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"qualificationEmissionLimit\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"RegulatorID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"RegulatorIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"RegulatorsMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"regulatorId\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"regulatorAddress\",\"type\":\"address\"},{\"internalType\":\"string\",\"name\":\"regulatorName\",\"type\":\"string\"},{\"internalType\":\"uint8\",\"name\":\"userType\",\"type\":\"uint8\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"SIGNIN_CREDIT\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"TOTAL_EMISSION\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"TransactionID\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inpu","ts\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"TransactionIDList\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"TransactionsMap\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"transactionId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"buyerId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"sellerId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"transactionOrderName\",\"type\":\"string\"},{\"internalType\":\"address\",\"name\":\"transactionBuyAddress\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"transactionSellAddress\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"transactionTime\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"transactionQuantity\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"userIdQueryAddress\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_EMISSIONRESOURCEID = "EmissionResourceID";

    public static final String FUNC_EMISSIONRESOURCEIDLIST = "EmissionResourceIDList";

    public static final String FUNC_EMISSIONRESOURCESMAP = "EmissionResourcesMap";

    public static final String FUNC_ENTERPRISEASSETID = "EnterpriseAssetID";

    public static final String FUNC_ENTERPRISEASSETIDLIST = "EnterpriseAssetIDList";

    public static final String FUNC_ENTERPRISEASSETSMAP = "EnterpriseAssetsMap";

    public static final String FUNC_ENTERPRISEID = "EnterpriseID";

    public static final String FUNC_ENTERPRISEIDLIST = "EnterpriseIDList";

    public static final String FUNC_ENTERPRISESMAP = "EnterprisesMap";

    public static final String FUNC_QUALIFICATIONID = "QualificationID";

    public static final String FUNC_QUALIFICATIONIDLIST = "QualificationIDList";

    public static final String FUNC_QUALIFICATIONSMAP = "QualificationsMap";

    public static final String FUNC_REGULATORID = "RegulatorID";

    public static final String FUNC_REGULATORIDLIST = "RegulatorIDList";

    public static final String FUNC_REGULATORSMAP = "RegulatorsMap";

    public static final String FUNC_SIGNIN_CREDIT = "SIGNIN_CREDIT";

    public static final String FUNC_TOTAL_EMISSION = "TOTAL_EMISSION";

    public static final String FUNC_TRANSACTIONID = "TransactionID";

    public static final String FUNC_TRANSACTIONIDLIST = "TransactionIDList";

    public static final String FUNC_TRANSACTIONSMAP = "TransactionsMap";

    public static final String FUNC_USERIDQUERYADDRESS = "userIdQueryAddress";

    public static final Event ENTERPRISEEMISSION_EVENT = new Event("EnterpriseEmission",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event SELLEMISSIONLIMIT_EVENT = new Event("SellEmissionLimit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFEREMISSIONLIMIT_EVENT = new Event("TransferEmissionLimit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEBALNCE_EVENT = new Event("UpdateBalnce",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEEMISSIONLIMIT_EVENT = new Event("UpdateEmissionLimit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPDATEENTERPRISEEMISSION_EVENT = new Event("UpdateEnterpriseEmission",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPLOADENTERPRISEEMISSION_EVENT = new Event("UploadEnterpriseEmission",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UPLOADQUALIFICATION_EVENT = new Event("UploadQualification",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event VERIFYENTERPRISEEMISSION_EVENT = new Event("VerifyEnterpriseEmission",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>(true) {}));
    ;

    public static final Event VERIFYQUALIFICATION_EVENT = new Event("VerifyQualification",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    protected CarbonDataStorage(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
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

    public BigInteger EmissionResourceID() throws ContractException {
        final Function function = new Function(FUNC_EMISSIONRESOURCEID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger EmissionResourceIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_EMISSIONRESOURCEIDLIST,
                Arrays.<Type>asList(new Uint256(param0)),
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

    public BigInteger EnterpriseAssetID() throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEASSETID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger EnterpriseAssetIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEASSETIDLIST,
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

    public BigInteger EnterpriseID() throws ContractException {
        final Function function = new Function(FUNC_ENTERPRISEID,
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

    public BigInteger QualificationID() throws ContractException {
        final Function function = new Function(FUNC_QUALIFICATIONID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger QualificationIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_QUALIFICATIONIDLIST,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
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

    public BigInteger RegulatorIDList(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_REGULATORIDLIST,
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

    public BigInteger TransactionID() throws ContractException {
        final Function function = new Function(FUNC_TRANSACTIONID,
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

    public String userIdQueryAddress(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_USERIDQUERYADDRESS,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public static CarbonDataStorage load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new CarbonDataStorage(contractAddress, client, credential);
    }

    public static CarbonDataStorage deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(CarbonDataStorage.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class EnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionEmission;
    }

    public static class SellEmissionLimitEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger _emissionLimitCount;

        public BigInteger _amount;
    }

    public static class TransferEmissionLimitEventResponse {
        public TransactionReceipt.Logs log;

        public String _from;

        public String _to;

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

    public static class UpdateEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _totalEmissions;
    }

    public static class UploadEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionEmission;
    }

    public static class UploadQualificationEventResponse {
        public TransactionReceipt.Logs log;

        public String _acount;

        public byte[] _name;

        public byte[] _content;
    }

    public static class VerifyEnterpriseEmissionEventResponse {
        public TransactionReceipt.Logs log;

        public String _form;

        public String _to;

        public Boolean _isAppore;
    }

    public static class VerifyQualificationEventResponse {
        public TransactionReceipt.Logs log;

        public String _enterpriseAddr;

        public BigInteger _emissionLimit;
    }
}
