package com.ruoyi.carbon.service.carbon;

import com.ruoyi.carbon.model.bo.CarbonUserServiceEmissionResourceIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceEmissionResourcesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceEnterpriseAssetIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceEnterpriseAssetsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceEnterpriseIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceEnterprisesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceQualificationIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceQualificationsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceRegisterEnterpriseInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceRegisterRegulatorInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceRegulatorIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceRegulatorsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceSelectEnterpriseInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceSelectQualificationInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceSelectUserAddressInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceSignInInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceTransactionIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceTransactionsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceUpdateBalanceInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceUpdateEnterpriseInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceUploadQualificationInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceUserIdQueryAddressInputBO;
import com.ruoyi.carbon.model.bo.CarbonUserServiceVerifyQualificationInputBO;
import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class CarbonUserServiceService {
  public static final String ABI = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("abi/CarbonUserService.abi");

  public static final String BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/ecc/CarbonUserService.bin");

  public static final String SM_BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/sm/CarbonUserService.bin");

  @Value("${system.contract.carbonUserServiceAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse registerRegulator(CarbonUserServiceRegisterRegulatorInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "registerRegulator", input.toArgs());
  }

  public CallResponse selectUserAddress(CarbonUserServiceSelectUserAddressInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectUserAddress", input.toArgs());
  }

  public CallResponse EnterpriseAssetsMap(CarbonUserServiceEnterpriseAssetsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetsMap", input.toArgs());
  }

  public CallResponse TOTAL_EMISSION() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TOTAL_EMISSION", Arrays.asList());
  }

  public CallResponse EnterpriseID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseID", Arrays.asList());
  }

  public CallResponse QualificationIDList(CarbonUserServiceQualificationIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationIDList", input.toArgs());
  }

  public TransactionResponse signIn(CarbonUserServiceSignInInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "signIn", input.toArgs());
  }

  public CallResponse SIGNIN_CREDIT() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "SIGNIN_CREDIT", Arrays.asList());
  }

  public CallResponse QualificationsMap(CarbonUserServiceQualificationsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationsMap", input.toArgs());
  }

  public TransactionResponse selectQualificationInfo(CarbonUserServiceSelectQualificationInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "selectQualificationInfo", input.toArgs());
  }

  public CallResponse RegulatorIDList(CarbonUserServiceRegulatorIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorIDList", input.toArgs());
  }

  public CallResponse TransactionID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionID", Arrays.asList());
  }

  public CallResponse QualificationID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationID", Arrays.asList());
  }

  public CallResponse TransactionsMap(CarbonUserServiceTransactionsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionsMap", input.toArgs());
  }

  public CallResponse userIdQueryAddress(CarbonUserServiceUserIdQueryAddressInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "userIdQueryAddress", input.toArgs());
  }

  public CallResponse EnterprisesMap(CarbonUserServiceEnterprisesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterprisesMap", input.toArgs());
  }

  public TransactionResponse uploadQualification(CarbonUserServiceUploadQualificationInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "uploadQualification", input.toArgs());
  }

  public TransactionResponse registerEnterprise(CarbonUserServiceRegisterEnterpriseInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "registerEnterprise", input.toArgs());
  }

  public TransactionResponse verifyQualification(CarbonUserServiceVerifyQualificationInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "verifyQualification", input.toArgs());
  }

  public TransactionResponse updateBalance(CarbonUserServiceUpdateBalanceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateBalance", input.toArgs());
  }

  public CallResponse RegulatorID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorID", Arrays.asList());
  }

  public CallResponse EnterpriseIDList(CarbonUserServiceEnterpriseIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseIDList", input.toArgs());
  }

  public CallResponse EmissionResourcesMap(CarbonUserServiceEmissionResourcesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourcesMap", input.toArgs());
  }

  public TransactionResponse updateEnterprise(CarbonUserServiceUpdateEnterpriseInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateEnterprise", input.toArgs());
  }

  public CallResponse EmissionResourceIDList(CarbonUserServiceEmissionResourceIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceIDList", input.toArgs());
  }

  public CallResponse selectEnterpriseInfo(CarbonUserServiceSelectEnterpriseInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectEnterpriseInfo", input.toArgs());
  }

  public CallResponse EnterpriseAssetIDList(CarbonUserServiceEnterpriseAssetIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetIDList", input.toArgs());
  }

  public CallResponse EmissionResourceID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceID", Arrays.asList());
  }

  public CallResponse EnterpriseAssetID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetID", Arrays.asList());
  }

  public CallResponse TransactionIDList(CarbonUserServiceTransactionIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionIDList", input.toArgs());
  }

  public CallResponse RegulatorsMap(CarbonUserServiceRegulatorsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorsMap", input.toArgs());
  }
}
