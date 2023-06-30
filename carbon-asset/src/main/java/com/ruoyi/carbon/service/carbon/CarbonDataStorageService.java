package com.ruoyi.carbon.service.carbon;

import com.ruoyi.carbon.model.bo.CarbonDataStorageEmissionResourceIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageEmissionResourcesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageEnterpriseAssetIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageEnterpriseAssetsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageEnterpriseIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageEnterprisesMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageQualificationIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageQualificationsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageRegulatorIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageRegulatorsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageTransactionIDListInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageTransactionsMapInputBO;
import com.ruoyi.carbon.model.bo.CarbonDataStorageUserIdQueryAddressInputBO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class CarbonDataStorageService {
  public static final String ABI = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("abi/CarbonDataStorage.abi");

  public static final String BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/ecc/CarbonDataStorage.bin");

  public static final String SM_BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/sm/CarbonDataStorage.bin");

  @Value("${system.contract.carbonDataStorageAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse RegulatorID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorID", Arrays.asList());
  }

  public CallResponse EnterpriseIDList(CarbonDataStorageEnterpriseIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseIDList", input.toArgs());
  }

  public CallResponse EmissionResourcesMap(CarbonDataStorageEmissionResourcesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourcesMap", input.toArgs());
  }

  public CallResponse EmissionResourceIDList(CarbonDataStorageEmissionResourceIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceIDList", input.toArgs());
  }

  public CallResponse EnterpriseAssetsMap(CarbonDataStorageEnterpriseAssetsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetsMap", input.toArgs());
  }

  public CallResponse TOTAL_EMISSION() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TOTAL_EMISSION", Arrays.asList());
  }

  public CallResponse EnterpriseID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseID", Arrays.asList());
  }

  public CallResponse EnterpriseAssetIDList(CarbonDataStorageEnterpriseAssetIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetIDList", input.toArgs());
  }

  public CallResponse QualificationIDList(CarbonDataStorageQualificationIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationIDList", input.toArgs());
  }

  public CallResponse EmissionResourceID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EmissionResourceID", Arrays.asList());
  }

  public CallResponse EnterpriseAssetID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterpriseAssetID", Arrays.asList());
  }

  public CallResponse SIGNIN_CREDIT() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "SIGNIN_CREDIT", Arrays.asList());
  }

  public CallResponse QualificationsMap(CarbonDataStorageQualificationsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationsMap", input.toArgs());
  }

  public CallResponse RegulatorIDList(CarbonDataStorageRegulatorIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorIDList", input.toArgs());
  }

  public CallResponse TransactionIDList(CarbonDataStorageTransactionIDListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionIDList", input.toArgs());
  }

  public CallResponse TransactionID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionID", Arrays.asList());
  }

  public CallResponse QualificationID() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "QualificationID", Arrays.asList());
  }

  public CallResponse userIdQueryAddress(CarbonDataStorageUserIdQueryAddressInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "userIdQueryAddress", input.toArgs());
  }

  public CallResponse RegulatorsMap(CarbonDataStorageRegulatorsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RegulatorsMap", input.toArgs());
  }

  public CallResponse TransactionsMap(CarbonDataStorageTransactionsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "TransactionsMap", input.toArgs());
  }

  public CallResponse EnterprisesMap(CarbonDataStorageEnterprisesMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "EnterprisesMap", input.toArgs());
  }
}
