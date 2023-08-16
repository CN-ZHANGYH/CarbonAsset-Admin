package com.ruoyi.carbon.service.carbon;

import com.ruoyi.carbon.model.bo.CarbonAssetServiceBuyEmissionLimitInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceByCreditsExchangedEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEmissionResourceIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEmissionResourcesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterpriseAssetIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterpriseAssetsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterpriseIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceEnterprisesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceInitEmissionLimitInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceInitPointsRewardsInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceQualificationIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceQualificationsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceQueryEmissionResourceByPageInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceQueryEnterpriseAssetByPageInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceQueryTransactionsByPageInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegisterEnterpriseInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegisterRegulatorInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegulatorIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceRegulatorsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectEmissionResourceInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectEnterpriseInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectQualificationInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectSellerAssetInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectTransactionInfoInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSelectUserAddressInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSellEmissionLimitInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSignInInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceSubEnterpriseCreditInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceTransactionIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceTransactionsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUpdateBalanceInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUpdateEmissionAssetInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUpdateEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUpdateEnterpriseInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUploadEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUploadQualificationInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceUserIdQueryAddressInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceVerifyEnterpriseEmissionInputBO;
import com.ruoyi.carbon.model.bo.CarbonAssetServiceVerifyQualificationInputBO;
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
public class CarbonAssetServiceService {
  public static final String ABI = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("abi/CarbonAssetService.abi");

  public static final String BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/ecc/CarbonAssetService.bin");

  public static final String SM_BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/sm/CarbonAssetService.bin");

  @Value("${system.contract.carbonAssetServiceAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse verifyEnterpriseEmission(CarbonAssetServiceVerifyEnterpriseEmissionInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "verifyEnterpriseEmission", input.toArgs());
  }

  public TransactionResponse updateEmissionAsset(CarbonAssetServiceUpdateEmissionAssetInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateEmissionAsset", input.toArgs());
  }

  public TransactionResponse registerRegulator(CarbonAssetServiceRegisterRegulatorInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "registerRegulator", input.toArgs());
  }

  public TransactionResponse enterpriseEmission(CarbonAssetServiceEnterpriseEmissionInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "enterpriseEmission", input.toArgs());
  }

  public TransactionResponse buyEmissionLimit(CarbonAssetServiceBuyEmissionLimitInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "buyEmissionLimit", input.toArgs());
  }

  public TransactionResponse uploadEnterpriseEmission(CarbonAssetServiceUploadEnterpriseEmissionInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "uploadEnterpriseEmission", input.toArgs());
  }

  public CallResponse selectUserAddress(CarbonAssetServiceSelectUserAddressInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectUserAddress", input.toArgs());
  }

  public CallResponse EnterpriseAssetsMap(CarbonAssetServiceEnterpriseAssetsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetsMap", input.toArgs());
  }

  public CallResponse TOTAL_EMISSION() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TOTAL_EMISSION", Arrays.asList());
  }

  public CallResponse EnterpriseID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseID", Arrays.asList());
  }

  public CallResponse QualificationIDList(CarbonAssetServiceQualificationIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationIDList", input.toArgs());
  }

  public TransactionResponse signIn(CarbonAssetServiceSignInInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "signIn", input.toArgs());
  }

  public CallResponse SIGNIN_CREDIT() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "SIGNIN_CREDIT", Arrays.asList());
  }

  public CallResponse QualificationsMap(CarbonAssetServiceQualificationsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationsMap", input.toArgs());
  }

  public TransactionResponse selectQualificationInfo(CarbonAssetServiceSelectQualificationInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "selectQualificationInfo", input.toArgs());
  }

  public CallResponse RegulatorIDList(CarbonAssetServiceRegulatorIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorIDList", input.toArgs());
  }

  public TransactionResponse sellEmissionLimit(CarbonAssetServiceSellEmissionLimitInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "sellEmissionLimit", input.toArgs());
  }

  public CallResponse TransactionID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionID", Arrays.asList());
  }

  public TransactionResponse updateEnterpriseEmission(CarbonAssetServiceUpdateEnterpriseEmissionInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateEnterpriseEmission", input.toArgs());
  }

  public CallResponse QualificationID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationID", Arrays.asList());
  }

  public CallResponse TransactionsMap(CarbonAssetServiceTransactionsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionsMap", input.toArgs());
  }

  public CallResponse userIdQueryAddress(CarbonAssetServiceUserIdQueryAddressInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "userIdQueryAddress", input.toArgs());
  }

  public CallResponse EnterprisesMap(CarbonAssetServiceEnterprisesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterprisesMap", input.toArgs());
  }

  public TransactionResponse uploadQualification(CarbonAssetServiceUploadQualificationInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "uploadQualification", input.toArgs());
  }

  public TransactionResponse queryEmissionResourceByPage(CarbonAssetServiceQueryEmissionResourceByPageInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "queryEmissionResourceByPage", input.toArgs());
  }

  public TransactionResponse registerEnterprise(CarbonAssetServiceRegisterEnterpriseInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "registerEnterprise", input.toArgs());
  }

  public TransactionResponse queryEnterpriseAssetByPage(CarbonAssetServiceQueryEnterpriseAssetByPageInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "queryEnterpriseAssetByPage", input.toArgs());
  }

  public TransactionResponse verifyQualification(CarbonAssetServiceVerifyQualificationInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "verifyQualification", input.toArgs());
  }

  public TransactionResponse updateBalance(CarbonAssetServiceUpdateBalanceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateBalance", input.toArgs());
  }

  public CallResponse RegulatorID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorID", Arrays.asList());
  }

  public CallResponse EnterpriseIDList(CarbonAssetServiceEnterpriseIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseIDList", input.toArgs());
  }

  public CallResponse EmissionResourcesMap(CarbonAssetServiceEmissionResourcesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourcesMap", input.toArgs());
  }

  public TransactionResponse initEmissionLimit(CarbonAssetServiceInitEmissionLimitInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "initEmissionLimit", input.toArgs());
  }

  public TransactionResponse updateEnterprise(CarbonAssetServiceUpdateEnterpriseInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateEnterprise", input.toArgs());
  }

  public CallResponse EmissionResourceIDList(CarbonAssetServiceEmissionResourceIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceIDList", input.toArgs());
  }

  public CallResponse selectEnterpriseInfo(CarbonAssetServiceSelectEnterpriseInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectEnterpriseInfo", input.toArgs());
  }

  public CallResponse EnterpriseAssetIDList(CarbonAssetServiceEnterpriseAssetIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetIDList", input.toArgs());
  }

  public TransactionResponse subEnterpriseCredit(CarbonAssetServiceSubEnterpriseCreditInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "subEnterpriseCredit", input.toArgs());
  }

  public CallResponse selectEmissionResourceInfo(CarbonAssetServiceSelectEmissionResourceInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectEmissionResourceInfo", input.toArgs());
  }

  public CallResponse EmissionResourceID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceID", Arrays.asList());
  }

  public CallResponse EnterpriseAssetID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetID", Arrays.asList());
  }

  public CallResponse selectTransactionInfo(CarbonAssetServiceSelectTransactionInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectTransactionInfo", input.toArgs());
  }

  public TransactionResponse initPointsRewards(CarbonAssetServiceInitPointsRewardsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "initPointsRewards", input.toArgs());
  }

  public TransactionResponse byCreditsExchangedEmission(CarbonAssetServiceByCreditsExchangedEmissionInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "byCreditsExchangedEmission", input.toArgs());
  }

  public CallResponse TransactionIDList(CarbonAssetServiceTransactionIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionIDList", input.toArgs());
  }

  public CallResponse selectSellerAssetInfo(CarbonAssetServiceSelectSellerAssetInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectSellerAssetInfo", input.toArgs());
  }

  public TransactionResponse queryTransactionsByPage(CarbonAssetServiceQueryTransactionsByPageInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "queryTransactionsByPage", input.toArgs());
  }

  public CallResponse RegulatorsMap(CarbonAssetServiceRegulatorsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorsMap", input.toArgs());
  }
}
